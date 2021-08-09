package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.UserConverter;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.dto.user.request.ChangePasswordRequest;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;
import com.nashtech.rootkies.service.UserService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
// @Api( tags = "User")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/home")
    @PreAuthorize("hasRole('USER')")
    public String getHome() {
        return "<h1>USER Home Page</h1>";
    }

    @PutMapping("/password/first")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ResponseDTO> changePasswordFirstLogin(@RequestBody PasswordRequest passwordRequest){
        String message = userService.changePasswordFirstLogin(passwordRequest);
        ResponseDTO dto = new ResponseDTO();
        dto.setData(message);
        dto.setSuccessCode(SuccessCode.CHANGE_PASSWORD_SUCCESS);
        return ResponseEntity.ok(dto);
    }
    //changepssword
    @PutMapping("/password/{username}")
    public ResponseEntity<ResponseDTO> changePassword(@PathVariable("username") String username,
                                                      @RequestBody ChangePasswordRequest request)
    {
        String message = userService.changePassword(username, request);
        ResponseDTO response = new ResponseDTO();
        response.setData(message);
        response.setSuccessCode(SuccessCode.CHANGE_PASSWORD_SUCCESS);
        return ResponseEntity.ok(response);
    }
}
