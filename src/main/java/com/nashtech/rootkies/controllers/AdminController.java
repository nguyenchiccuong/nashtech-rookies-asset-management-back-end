package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.*;
import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.category.request.CreateCategoryDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.user.request.ChangePasswordRequest;
import com.nashtech.rootkies.dto.user.request.CreateUserDTO;
import com.nashtech.rootkies.dto.user.request.EditUserDTO;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;
import com.nashtech.rootkies.exception.*;
import com.nashtech.rootkies.model.Category;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.service.*;
import io.swagger.annotations.Api;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/admin")
// @Api( tags = "Admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @GetMapping("/home")
    @PreAuthorize("hasRole('ADMIN')")
    public String getHome() {
        return "<h1>ADMIN Home Page</h1>";
    }

    @PutMapping("/password/first")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> changePasswordFirstLogin(@RequestBody PasswordRequest passwordRequest) {
        JwtResponse response = userService.changePasswordFirstLogin(passwordRequest);
        ResponseDTO dto = new ResponseDTO();
        dto.setData(response);
        dto.setSuccessCode(SuccessCode.CHANGE_PASSWORD_SUCCESS);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/user/save")
    public ResponseEntity<ResponseDTO> createNewUser(@Valid @RequestBody CreateUserDTO createUserDTO) throws ConvertEntityDTOException, CreateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        User user = userConverter.convertCreateUserDTOtoEntity(createUserDTO);
        Boolean check = userService.createUser(user);
        responseDTO.setData(check);
        responseDTO.setSuccessCode(SuccessCode.USER_CREATED_SUCCESS);
        return ResponseEntity.ok().body(responseDTO);
    }

    @PutMapping("/user/update/{staffcode}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable(value = "staffcode") String staffcode,
                                                  @Valid @RequestBody EditUserDTO editUserDTO) throws UpdateDataFailException {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            User user = userConverter.convertEditUserDTOtoEntity(editUserDTO);
            User updateUser = userService.updateUser(staffcode, user);
            responseDTO.setData(userConverter.convertToDto(updateUser));
            responseDTO.setSuccessCode(SuccessCode.USER_UPDATED_SUCCESS);
        } catch (Exception e){
            throw new UpdateDataFailException(ErrorCode.ERR_UPDATE_USER_FAIL);
        }
        return ResponseEntity.ok(responseDTO);
    }

}
