package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    /*@Autowired
    UserRepository userRepository;

    @Override
    public User getUser(Long id) throws UserNotFoundException {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException(ErrorCode.ERR_USER_NOT_FOUND);
        }
        return userRepository.findById(id).get();
    }

    @Override
    public boolean createUser(User user) throws UpdateDataFailException {
        try{
            userRepository.save(user);
            return true;
        }catch(Exception ex){
            throw new UpdateDataFailException(ErrorCode.ERR_CREATE_USER_FAIL);
        }

    }*/
}
