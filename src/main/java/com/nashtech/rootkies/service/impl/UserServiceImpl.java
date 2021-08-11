package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.user.request.ChangePasswordRequest;
import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.auth.LoginRequest;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;
import com.nashtech.rootkies.exception.custom.ApiRequestException;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.AuthService;
import com.nashtech.rootkies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private AuthService authService;

    @Override
    public String changePasswordFirstLogin(PasswordRequest passwordRequest) {
        String id = passwordRequest.getStaffCode();
        String newPassword = passwordRequest.getNewPassword();

        User user = userRepository.findById(id).orElseThrow(
                () -> new ApiRequestException(ErrorCode.USER_NOT_FOUND)
        );

        if(user.getIsDeleted()){
            throw new ApiRequestException(ErrorCode.USER_IS_DISABLED);
        }

        if(newPassword == null || newPassword.trim().length() == 0){
            throw new ApiRequestException(ErrorCode.PASSWORD_IS_EMPTY);
        }

        try{
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return "Success to change password.";
        }
        catch(Exception e){
            throw new ApiRequestException(ErrorCode.ERR_CHANGE_PASSWORD);
        }
    }

    public Boolean checkOldPassword(String username, String password){
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        catch(Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String changePassword(String username, ChangePasswordRequest changePasswordRequest) {
        String oldPassword = changePasswordRequest.getOldPassword();
        String newPassword = changePasswordRequest.getNewPassword();

        Optional<User> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent() == false) {
            throw new ApiRequestException(ErrorCode.USER_NOT_FOUND);
        }

        if(checkOldPassword(username, oldPassword) == false) {
            throw new ApiRequestException(ErrorCode.PASSWORD_NOT_CORRECT);
        }

        if(newPassword == null || newPassword.trim().length() == 0){
            throw new ApiRequestException(ErrorCode.PASSWORD_IS_EMPTY);
        }

        if(newPassword.equals(oldPassword)){
            throw new ApiRequestException(ErrorCode.SAME_PASSWORD);
        }

        try{
            User user = optionalUser.get();
            user.setPassword(encoder.encode(newPassword));
            userRepository.save(user);
            return "Success to change password.";
        }
        catch(Exception e){
            throw new ApiRequestException(ErrorCode.ERR_CHANGE_PASSWORD);
        }
    }

    /*@Override
    public User getUser(Long id) throws UserNotFoundException {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(ErrorCode.ERR_USER_NOT_FOUND);
        }
        return userRepository.findById(id).get();
    }*/

    @Override
    public boolean createUser(User user) throws CreateDataFailException {
        try{
            //validation
            /*if (user.getDateOfBirth().until(LocalDateTime.now(), ChronoUnit.YEARS) < 18)
                throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_DOB);
            if (user.getDateOfBirth().isAfter(user.getJoinedDate()))
                throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_JD_DOB);
            DayOfWeek day = user.getJoinedDate().getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
                throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_JD);*/
            user.setIsDeleted(false);
            user.setFirstLogin(false);
            //auto-generated username
            String username = user.getFirstName().toLowerCase(Locale.ROOT);
            String[] words = user.getLastName().split(" ");
            for (String word : words)
                username += word.toLowerCase(Locale.ROOT).charAt(0);
            user.setUsername(username);
            int i = 1;
            while (userRepository.existsByUsername(user.getUsername())) {
                user.setUsername(username + i);
                i++;
            }
            //auto-generated password
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            String password = user.getUsername() + '@' + user.getDateOfBirth().format(formatter);
            user.setPassword(encoder.encode(password));
            //save
            userRepository.save(user);
            return true;
        } catch(Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_FAIL);
        }
    }
  
}
