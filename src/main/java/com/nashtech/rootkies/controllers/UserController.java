package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.UserConverter;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.exception.ConvertEntityDTOException;
import com.nashtech.rootkies.exception.CreateDataFailException;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
@Api( tags = "User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserConverter userConverter;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> createNewUser(@Valid @RequestBody CreateUserDTO createUserDTO) throws ConvertEntityDTOException, CreateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userConverter.convertCreateUserDTOtoEntity(createUserDTO);
        Boolean check = userService.createUser(user);
        responseDTO.setData(check);
        responseDTO.setSuccessCode(SuccessCode.USER_CREATED_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }
}
