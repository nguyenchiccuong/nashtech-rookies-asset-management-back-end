package com.nashtech.rootkies.service.impl;

import java.util.Optional;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.dto.user.request.ChangePasswordRequest;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;
import com.nashtech.rootkies.exception.custom.ApiRequestException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    AuthenticationManager authenticationManager;



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

}
