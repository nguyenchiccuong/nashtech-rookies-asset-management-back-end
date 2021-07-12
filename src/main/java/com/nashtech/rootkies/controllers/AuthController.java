package com.nashtech.rootkies.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.nashtech.rootkies.constants.ErrorCode;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.converter.UserConverter;
import com.nashtech.rootkies.dto.auth.LoginDTO;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.UserAuthenticationException;
import com.nashtech.rootkies.model.ERole;
import com.nashtech.rootkies.model.Role;
import com.nashtech.rootkies.model.User;
import com.nashtech.rootkies.payload.response.JwtResponse;
import com.nashtech.rootkies.repository.RoleRepository;
import com.nashtech.rootkies.repository.UserRepository;
import com.nashtech.rootkies.security.services.UserDetailsImpl;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nashtech.rootkies.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api( tags = "Auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

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

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);


	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@Valid @RequestBody SignupDTO signupDto)
			throws UserAuthenticationException {
		ResponseDTO response = new ResponseDTO();

		if (userRepository.existsByUsername(signupDto.getUsername())) {
			response.setErrorCode(ErrorCode.ERR_USER_EXISTED);
		}

		if (userRepository.existsByEmail(signupDto.getEmail())) {
			response.setErrorCode(ErrorCode.ERR_USER_EXISTED);
		}

		try{
			User user = userConverter.convertToEntity(signupDto);
			Set<Role> roles = new HashSet<>();
			Role userRole = roleRepository.findByName(ERole.ROLE_USER).get();
			roles.add(userRole);
			user.setRoles(roles);
			response.setData(userRepository.save(user));
			response.setSuccessCode(SuccessCode.USER_SIGNUP_SUCCESS);
		}catch(Exception ex){
			throw new UserAuthenticationException(ErrorCode.ERR_USER_SIGNUP_FAIL);
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDTO) throws UserAuthenticationException {

		ResponseDTO response = new ResponseDTO();

		try{

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));

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

			response.setData(jwtRes);
			response.setSuccessCode(SuccessCode.USER_LOGIN_SUCCESS);
		}catch(Exception ex){
			throw new UserAuthenticationException(ErrorCode.ERR_USER_LOGIN_FAIL);
		}
		return ResponseEntity.ok(response);
	}


}
