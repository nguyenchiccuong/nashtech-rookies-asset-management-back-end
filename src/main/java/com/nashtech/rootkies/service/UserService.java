package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.user.request.PasswordRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    public JwtResponse changePasswordFirstLogin(PasswordRequest passwordRequest);

}
