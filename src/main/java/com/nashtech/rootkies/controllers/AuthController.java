package com.nashtech.rootkies.controllers;

import javax.validation.Valid;

import com.nashtech.rootkies.constants.SuccessCode;
import com.nashtech.rootkies.dto.auth.JwtResponse;
import com.nashtech.rootkies.dto.auth.LoginRequest;
import com.nashtech.rootkies.dto.auth.SignUpRequest;
import com.nashtech.rootkies.dto.common.ResponseDTO;
import com.nashtech.rootkies.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Tag(name = "AUTH", description = "AUTH API")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest){
		JwtResponse response = authService.signIn(loginRequest);
		ResponseDTO dto = new ResponseDTO();
		dto.setData(response);
		dto.setSuccessCode(SuccessCode.USER_LOGIN_SUCCESS);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping("/fakesignup")
	public ResponseEntity<String> fakeSignUp(@RequestBody SignUpRequest request){
		String message = authService.fakeSignUp(request);
		return ResponseEntity.ok(message);
	}

	@GetMapping("/home")
	public String getHome(){
		return "<h1>Home</h1>";
	}
}

