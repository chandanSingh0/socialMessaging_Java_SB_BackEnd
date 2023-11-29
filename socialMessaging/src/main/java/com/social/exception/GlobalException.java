package com.social.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalException {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> ohterExceptionHandler(Exception ex, WebRequest wb){
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(),wb.getDescription(false),LocalDateTime.now());
		
		return new ResponseEntity<>(errorDetails,HttpStatus.BAD_REQUEST);
	}
	
}
