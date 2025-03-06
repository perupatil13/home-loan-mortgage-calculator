package com.bankapplication.homeloan.exception;

public class InvalidMortgageRequestException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidMortgageRequestException(String message) {
		super(message);
	}
}
