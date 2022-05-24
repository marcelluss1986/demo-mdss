package com.mdss.demo.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.mdss.demo.services.exceptions.DatabaseException;
import com.mdss.demo.services.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
		
		@ExceptionHandler(ResourceNotFoundException.class)
		public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
			String error = "Resource not Found";
			HttpStatus status = HttpStatus.NOT_FOUND;
			StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		}
		
		@ExceptionHandler(DatabaseException.class)
		public ResponseEntity<StandardError> databaseException(DatabaseException e, HttpServletRequest request){
			String erro = "Client has order";
			HttpStatus status = HttpStatus.BAD_REQUEST;
			StandardError err = new StandardError(Instant.now(),status.value(),erro, e.getMessage(), request.getRequestURI());
			return ResponseEntity.status(status).body(err);
		}
}
