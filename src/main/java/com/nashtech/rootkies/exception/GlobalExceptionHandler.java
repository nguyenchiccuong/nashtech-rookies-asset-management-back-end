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

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConverterUserException.class)
    	public ResponseEntity convertUserFail(ConverterUserException ex, WebRequest request) {
		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
//	@ExceptionHandler(DataNotFoundException.class)
//	public ResponseEntity dataNotFoundException(DataNotFoundException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(CreateDataFailException.class)
//	public ResponseEntity createDataFailException(CreateDataFailException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(DeleteDataFailException.class)
//	public ResponseEntity updateDataFailException(DeleteDataFailException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(UpdateDataFailException.class)
//	public ResponseEntity updateDataFailException(UpdateDataFailException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(UserAuthenticationException.class)
//	public ResponseEntity DuplicateDataException(UserAuthenticationException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);
//	}
//
//	@ExceptionHandler(UserExistedException.class)
//	public ResponseEntity UserExistedException(UserExistedException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(DuplicateDataException.class)
//	public ResponseEntity DuplicateDataException(DuplicateDataException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	@ExceptionHandler(UserNotFoundException.class)
//	public ResponseEntity UserNotFoundException(UserNotFoundException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(ProductOutOfStockException.class)
//	public ResponseEntity ProductOutOfStockException(ProductOutOfStockException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(CartNotFoundException.class)
//	public ResponseEntity CartNotFoundException(CartNotFoundException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
//	}
//
//	@ExceptionHandler(InvalidRequestDataException.class)
//	public ResponseEntity invalidRequestDataException(InvalidRequestDataException ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
//	}
//
//	/** Global Exception **/
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<?> globleExcpetionHandler(Exception ex, WebRequest request) {
//		ResponseDTO errorResponse = new ResponseDTO(ex.getMessage(), null , null);
//		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
//	}
}
