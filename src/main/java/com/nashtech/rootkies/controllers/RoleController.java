package com.nashtech.rootkies.controllers;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/roles")
@AllArgsConstructor
public class RoleController {

    @Autowired
    private final RoleService service;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAllRoles() {
        ResponseDTO responseDTO = new ResponseDTO();
        try {
            responseDTO.setData(service.getAllRole());
            responseDTO.setSuccessCode(SuccessCode.GET_ALL_ROLE_SUCCESS);
            return ResponseEntity.ok().body(responseDTO);
        } catch (Exception ex) {
            responseDTO.setErrorCode(ErrorCode.ERR_GET_ALL_ROLE);
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }
}
