package com.nashtech.rootkies.service;

import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;

public interface UserService {

    //User getUser(Long id) throws UserNotFoundException;
    boolean createUser(User user) throws CreateDataFailException;
}
