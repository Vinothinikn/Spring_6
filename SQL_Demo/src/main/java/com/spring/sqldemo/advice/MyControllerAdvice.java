package com.spring.sqldemo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spring.sqldemo.customexception.InputException;
import com.spring.sqldemo.dto.StatusCode;

@ControllerAdvice
public class MyControllerAdvice extends ResponseEntityExceptionHandler{

	@ExceptionHandler(InputException.class)
	public ResponseEntity<?> inputValidationException(InputException ex){
		StatusCode status = new StatusCode(400, "Input request cannot be null");
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(IndexOutOfBoundsException.class)
	public ResponseEntity<?> indexException(IndexOutOfBoundsException ex){
		StatusCode status = new StatusCode(400, "Input request list cannot be null");
		return new ResponseEntity<>(status, HttpStatus.BAD_REQUEST);
	}
	
}
