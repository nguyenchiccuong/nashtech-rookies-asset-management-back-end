package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.PageDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.dto.user.request.ChangePasswordRequest;
import com.nashtech.rootkies.exception.*;
import com.nashtech.rootkies.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {

    public JwtResponse changePasswordFirstLogin(PasswordRequest passwordRequest);
    //User getUser(Long id) throws UserNotFoundException;
    //boolean createUser(User user) throws UpdateDataFailException;
    PageDTO findAllUser(Pageable pageable, Specification specification) throws DataNotFoundException;

    public String changePassword(String username, ChangePasswordRequest changePasswordRequest);
    boolean createUser(User user) throws CreateDataFailException;

}
