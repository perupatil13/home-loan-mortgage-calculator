package com.bankapplication.homeloan.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;
import com.bankapplication.homeloan.model.ErrorResponseDetails;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String, String>> handleException(Exception ex, HttpServletRequest request) {
	    if (request.getRequestURI().startsWith("/v3/api-docs") || request.getRequestURI().startsWith("/swagger-ui")) {
	        throw new RuntimeException(ex); // Convert to unchecked exception
	    }

	    Map<String, String> errorResponse = new HashMap<>();
	    errorResponse.put("error", ex.getMessage());
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors()
				.forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MortgageNotFeasibleException.class)
	public ResponseEntity<ErrorResponseDetails> handleMortageNotFeasibleException(MortgageNotFeasibleException ex) {
		logger.error("Mortage Check: " + ex.getMessage());
		ErrorResponseDetails errorResponse = new ErrorResponseDetails(ex.getMessage(),
				HttpStatus.NOT_ACCEPTABLE.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_ACCEPTABLE);
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponseDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
		logger.error("Resource Not Found: " + ex.getMessage());
		ErrorResponseDetails errorResponse = new ErrorResponseDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidMortgageRequestException.class)
	public ResponseEntity<ErrorResponseDetails> handleResourceNotFoundException(InvalidMortgageRequestException ex) {
		logger.error("Resource Not Found: " + ex.getMessage());
		ErrorResponseDetails errorResponse = new ErrorResponseDetails(ex.getMessage(), HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	
	/*
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseDetails> handleGenericException(Exception ex) {
		logger.error("Internal Server Error: " + ex.getMessage(), ex);
		ErrorResponseDetails errorResponse = new ErrorResponseDetails(
				"An unexpected error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}*/
}
