package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.PageDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.user.UserDTO;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.exception.UserNotFoundException;
import com.nashtech.rootkies.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    //User getUser(Long id) throws UserNotFoundException;
    //boolean createUser(User user) throws UpdateDataFailException;
    PageDTO findAllUser(Pageable pageable, Specification specification) ;

    List<User> findAll();
}
