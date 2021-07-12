package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;
import org.springframework.http.ResponseEntity;

public interface UserService {

    User getUser(Long id) throws UserNotFoundException;
    boolean createUser(User user);
}
