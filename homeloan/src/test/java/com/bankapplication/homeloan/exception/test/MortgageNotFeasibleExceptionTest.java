package com.bankapplication.homeloan.exception.test;

import org.junit.jupiter.api.Test;

import com.bankapplication.homeloan.exception.MortgageNotFeasibleException;

import static org.junit.jupiter.api.Assertions.*;

public class MortgageNotFeasibleExceptionTest {

	@Test
	void testExceptionMessage() {
		String expectedMessage = "Mortgage is not feasible";
		MortgageNotFeasibleException exception = new MortgageNotFeasibleException(expectedMessage);

		// Check that the message is correctly set
		assertEquals(expectedMessage, exception.getMessage());
	}

	@Test
	void testExceptionType() {
		String expectedMessage = "Mortgage is not feasible";
		MortgageNotFeasibleException exception = new MortgageNotFeasibleException(expectedMessage);

		// Check that the exception is of the correct type
		assertTrue(exception instanceof RuntimeException);
	}
}