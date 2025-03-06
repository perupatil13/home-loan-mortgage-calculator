package com.bankapplication.homeloan.exception.test;

import org.junit.jupiter.api.Test;

import com.bankapplication.homeloan.exception.InvalidMortgageRequestException;

import static org.junit.jupiter.api.Assertions.*;

public class InvalidMortgageRequestExceptionTest {

	@Test
	void testExceptionMessage() {
		String expectedMessage = "Invalid mortgage request";
		InvalidMortgageRequestException exception = new InvalidMortgageRequestException(expectedMessage);

		// Check that the message is correctly set
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void testExceptionType() {
		String expectedMessage = "Invalid mortgage request";
		InvalidMortgageRequestException exception = new InvalidMortgageRequestException(expectedMessage);

		// Check that the exception is of the correct type
		assertTrue(exception instanceof RuntimeException);
	}
}
