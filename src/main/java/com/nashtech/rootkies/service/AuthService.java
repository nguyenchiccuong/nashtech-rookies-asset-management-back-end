package com.nashtech.rootkies.service;

import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.auth.LoginDTO;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.exception.UserAuthenticationException;
import com.nashtech.rootkies.exception.UserSignupException;

public interface AuthService {

    public boolean signup(SignupDTO dto) throws UserSignupException;

    public JwtResponse signin(LoginDTO loginDto) throws UserAuthenticationException;

}
