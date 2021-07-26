package com.nashtech.rootkies.controllers;


import javax.validation.Valid;
import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.auth.LoginDTO;
import com.nashtech.rootkies.dto.auth.SignupDTO;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.exception.UserAuthenticationException;
import com.nashtech.rootkies.exception.UserSignupException;
import com.nashtech.rootkies.service.AuthService;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Api( tags = "Auth")
public class AuthController {

	@Autowired
	AuthService authService;

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

	@PostMapping("/signup")
	public ResponseEntity<ResponseDTO> signup(@Valid @RequestBody SignupDTO signupDto) throws UserSignupException {
		ResponseDTO response = new ResponseDTO();
		response.setData(authService.signup(signupDto));
		response.setSuccessCode(SuccessCode.USER_SIGNUP_SUCCESS);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/signin")
	public ResponseEntity<?> login(@Valid @RequestBody LoginDTO loginDto) throws UserAuthenticationException {
		ResponseDTO response = new ResponseDTO();
		JwtResponse jwtRes = authService.signin(loginDto);
		response.setData(jwtRes);
		response.setSuccessCode(SuccessCode.USER_LOGIN_SUCCESS);
		return ResponseEntity.ok(response);
	}


}
