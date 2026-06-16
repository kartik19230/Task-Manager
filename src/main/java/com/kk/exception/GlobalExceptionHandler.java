package com.kk.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.kk.dto.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException ex) {

		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT.value(), ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidExceptionO(MethodArgumentNotValidException ex) {

		String errorMsg = ex.getBindingResult().getFieldErrors().stream()
	            .map(error -> error.getField() + ": " + error.getDefaultMessage())
	            .collect(Collectors.joining(", "));
				
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), errorMsg);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
