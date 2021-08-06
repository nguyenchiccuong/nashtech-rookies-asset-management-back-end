package com.nashtech.rootkies.service.impl;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.converter.UserConverter;
import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.auth.LoginDTO;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.exception.*;
import com.nashtech.rootkies.enums.ERole;
import com.nashtech.rootkies.model.Role;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.repository.RoleRepository;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.security.jwt.JwtUtils;
import com.nashtech.rootkies.security.services.UserDetailsImpl;
import com.nashtech.rootkies.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserConverter userConverter;

    @Autowired
    AuthenticationManager authenticationManager;

    /*public boolean signup(SignupDTO signupDto) throws UserSignupException {
        try{
            if (userRepository.existsByUsername(signupDto.getUsername())) {
                throw new UserExistedException(ErrorCode.ERR_USER_EXISTED);
            }
            User user = userConverter.convertToEntity(signupDto);
            Role userRole = roleRepository.findByRoleName(ERole.ROLE_USER).get();
            user.setRole(userRole);
            userRepository.save(user);
            return true;
        }catch(Exception ex){
            throw new UserSignupException(ErrorCode.ERR_USER_SIGNUP_FAIL);
        }

    }*/

    /*@Override
    public JwtResponse signin(LoginDTO loginDto) throws UserAuthenticationException {

       try{
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

           SecurityContextHolder.getContext().setAuthentication(authentication);
           String jwt = jwtUtils.generateJwtToken(authentication);

           UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
           List<String> roles = userDetails.getAuthorities().stream()
                   .map(item -> item.getAuthority())
                   .collect(Collectors.toList());

           JwtResponse jwtRes = new JwtResponse(jwt,
                   userDetails.getId(),
                   userDetails.getUsername(),
                   userDetails.getEmail(),
                   roles);
           return jwtRes;
       }catch(Exception ex){
            throw new UserAuthenticationException(ErrorCode.ERR_USER_LOGIN_FAIL);
       }
    }*/

}
