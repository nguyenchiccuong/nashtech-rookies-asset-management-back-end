package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.user.request.PasswordRequest;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {

    public String changePasswordFirstLogin(PasswordRequest passwordRequest);

}
