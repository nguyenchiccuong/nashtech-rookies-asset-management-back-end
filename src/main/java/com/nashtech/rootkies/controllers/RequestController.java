package com.nashtech.rootkies.controllers;

import javax.servlet.http.HttpServletRequest;

import com.nashtech.rootkies.converter.LocationConverter;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.DataNotFoundException;
import com.nashtech.rootkies.exception.InvalidRequestDataException;
import com.nashtech.rootkies.exception.UpdateDataFailException;
import com.nashtech.rootkies.security.jwt.JwtUtils;
import com.nashtech.rootkies.service.RequestService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/request")
@Tag(name = "REQUEST", description = "REQUEST API")
public class RequestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RequestController.class);

    private final RequestService requestService;

    private final LocationConverter locationConverter;

    private final JwtUtils jwtUtils;

    @Autowired
    public RequestController(RequestService requestService, LocationConverter locationConverter, JwtUtils jwtUtils) {
        this.requestService = requestService;
        this.locationConverter = locationConverter;
        this.jwtUtils = jwtUtils;
    }

    @PutMapping("/complete/{requestId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> completeRequest(HttpServletRequest req,
            @PathVariable("requestId") Long requestId)
            throws DataNotFoundException, InvalidRequestDataException, UpdateDataFailException {
        String jwt = req.getHeader("Authorization").substring(7, req.getHeader("Authorization").length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        Long locationId = locationConverter.getLocationIdFromUsername(username);
        return ResponseEntity.ok(requestService.completeRequest(locationId, requestId, username));
    }

    @PutMapping("/cancel/{requestId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ResponseDTO> cancelRequest(HttpServletRequest req, @PathVariable("requestId") Long requestId)
            throws DataNotFoundException, InvalidRequestDataException, UpdateDataFailException {
        String jwt = req.getHeader("Authorization").substring(7, req.getHeader("Authorization").length());
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        Long locationId = locationConverter.getLocationIdFromUsername(username);
        return ResponseEntity.ok(requestService.cancelRequest(locationId, requestId, username));
    }

}
