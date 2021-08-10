package com.nashtech.rootkies.exception;

import com.nashtech.rootkies.dto.common.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

// @ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConverterUserException.class)
    	public ResponseEntity convertUserFail(ConverterUserException ex, WebRequest request) {
		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity getDataFail(ConverterUserException ex, WebRequest request) {
		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
