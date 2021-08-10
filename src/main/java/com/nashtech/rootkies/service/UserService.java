package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    public JwtResponse changePasswordFirstLogin(PasswordRequest passwordRequest);

    //User getUser(Long id) throws UserNotFoundException;
    boolean createUser(User user) throws CreateDataFailException;

}
