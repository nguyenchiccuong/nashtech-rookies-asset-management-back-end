package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.converter.UserConverter;
import com.nashtech.rootkies.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@Api( tags = "User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

}
