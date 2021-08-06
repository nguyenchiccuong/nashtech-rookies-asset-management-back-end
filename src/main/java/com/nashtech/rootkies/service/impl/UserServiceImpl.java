package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;


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
            /*if (LocalDateTime.now().until(user.getDateOfBirth(), ChronoUnit.YEARS) < 18)
                throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_DOB);
            if (user.getDateOfBirth().isAfter(user.getJoinedDate()))
                throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_JD_DOB);
            DayOfWeek day = user.getJoinedDate().getDayOfWeek();
            if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY)
                throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_JD);*/
            //auto-generated username
            String username = user.getFirstName().toLowerCase(Locale.ROOT);
            String[] words = user.getLastName().split(" ");
            for (String word : words)
                username += word.toLowerCase(Locale.ROOT).charAt(0);
            user.setUsername(username);
            int i = 1;
            while (userRepository.existsByUsername(username)) {
                user.setUsername(username + i);
                i++;
            }
            //auto-generated password
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            String password = username + '@' + user.getDateOfBirth().format(formatter);
            user.setPassword(encoder.encode(password));
            //save
            userRepository.save(user);
            return true;
        } catch(Exception ex){
            throw new CreateDataFailException(ErrorCode.ERR_CREATE_USER_FAIL);
        }

    }
}
