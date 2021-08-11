package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.ResourceNotFoundException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public JwtResponse changePasswordFirstLogin(PasswordRequest passwordRequest);

    //User getUser(Long id) throws UserNotFoundException;
    boolean createUser(User user) throws CreateDataFailException;
    Boolean deleteUser(String userId) throws UserNotFoundException;
    User updateUser(String userId, User user) throws UserNotFoundException, ResourceNotFoundException;
    //Optional<User> getUser(String staffCode) throws UserNotFoundException;
    //List<User> retrieveUsers() throws UserNotFoundException;

}
