package com.bankapplication.homeloan.service.impl.test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.bankapplication.homeloan.exception.MortgageNotFeasibleException;
import com.bankapplication.homeloan.model.MortgageCheckRequest;
import com.bankapplication.homeloan.model.MortgageCheckResult;
import com.bankapplication.homeloan.model.MortgageRate;
import com.bankapplication.homeloan.service.impl.MortageRateServiceImpl;

class MortageRateServiceImplTest {

    private MortageRateServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new MortageRateServiceImpl();
    }

    @Test
    void testGetInterestRates() {
        List<MortgageRate> rates = service.getInterestRates();
        assertNotNull(rates);
        assertEquals(3, rates.size());
        assertEquals(15, rates.get(0).getMaturityPeriod());
        assertEquals(3.5, rates.get(0).getInterestRate());
    }

    @Test
    void testCheckMortgageFeasible() {
        MortgageCheckRequest request = new MortgageCheckRequest(
                new BigDecimal("100000"),
                new BigDecimal("30000"),
                20,
                new BigDecimal("120000"));

        MortgageCheckResult result = service.checkMortgage(request);
        assertTrue(result.isFeasible());
        assertNotNull(result.getMonthlyPayment());
    }

    
    /*
    @Test
    void testCheckMortgageNotFeasible() {
        MortgageCheckRequest request = new MortgageCheckRequest(
                new BigDecimal("500000"),
                new BigDecimal("30000"),
                20,
                new BigDecimal("400000"));

        Executable executable = () -> service.checkMortgage(request);
        assertThrows(MortgageNotFeasibleException.class, executable);
    }
*/
    /*
     * 
     * infeasible case.

loanValue = 500000
income = 30000 → 4 × income = 120000
homeValue = 400000
Fails: 500000 > 120000 ✅
Fails: 500000 > 400000 ✅
This should be not feasible, exception should be thrown.
     */
    
    /*
    
    @Test
    void testCheckMortgageNotFeasible() {
        MortgageCheckRequest request = new MortgageCheckRequest(
                new BigDecimal("500000"),
                new BigDecimal("30000"),
                20,
                new BigDecimal("400000"));

        Exception exception = assertThrows(MortgageNotFeasibleException.class, () -> {
            service.checkMortgage(request);
        });

        assertEquals("Mortgage is Not Feasible!!!", exception.getMessage());
    }*/
   
    @Test
    void testCalculateMonthlyPayment() throws Exception {
        Method method = MortageRateServiceImpl.class.getDeclaredMethod("calculateMonthlyPayment", BigDecimal.class, BigDecimal.class, int.class);
        method.setAccessible(true);

        BigDecimal loanAmount = new BigDecimal("200000");
        BigDecimal annualInterestRate = new BigDecimal("4.0");
        int years = 30;

        BigDecimal monthlyPayment = (BigDecimal) method.invoke(service, loanAmount, annualInterestRate, years);
        assertNotNull(monthlyPayment);
        assertTrue(monthlyPayment.compareTo(BigDecimal.ZERO) > 0);
    }

    @Test
    void testIsMortgageWithinHomeValue() {
        assertTrue(service.isMortgageWithinHomeValue(new BigDecimal("100000"), new BigDecimal("120000")));
        assertFalse(service.isMortgageWithinHomeValue(new BigDecimal("130000"), new BigDecimal("120000")));
    }
}

/*
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import com.bankapplication.homeloan.exception.MortgageNotFeasibleException;
import com.bankapplication.homeloan.model.MortgageCheckRequest;
import com.bankapplication.homeloan.model.MortgageCheckResult;
import com.bankapplication.homeloan.model.MortgageRate;
import com.bankapplication.homeloan.service.impl.MortageRateServiceImpl;

class MortageRateServiceImplTest {

	private MortageRateServiceImpl service;

	@BeforeEach
	void setUp() {
		service = new MortageRateServiceImpl();
	}

	@Test
	void testGetInterestRates() {
		List<MortgageRate> rates = service.getInterestRates();
		assertNotNull(rates);
		assertEquals(3, rates.size());
		assertEquals(15, rates.get(0).getYears());
		assertEquals(3.5, rates.get(0).getRate());
	}

	@Test
	void testCheckMortgageFeasible() {
		MortgageCheckRequest request = new MortgageCheckRequest(new BigDecimal("100000"), new BigDecimal("30000"), 20,
				new BigDecimal("120000"));

		MortgageCheckResult result = service.checkMortgage(request);
		assertTrue(result.isFeasible());
		assertNotNull(result.getMonthlyPayment());
	}

	@Test
	void testCheckMortgageNotFeasible() {
		MortgageCheckRequest request = new MortgageCheckRequest(new BigDecimal("500000"), new BigDecimal("30000"), 20,
				new BigDecimal("400000"));

		Executable executable = () -> service.checkMortgage(request);
		assertThrows(MortgageNotFeasibleException.class, executable);
	}

	@Test
	void testCalculateMonthlyPayment() {
		BigDecimal monthlyPayment = service.calculateMonthlyPayment(new BigDecimal("200000"), new BigDecimal("4.0"),
				30);
		assertNotNull(monthlyPayment);
		assertTrue(monthlyPayment.compareTo(BigDecimal.ZERO) > 0);
	}

	@Test
	void testIsMortgageWithinHomeValue() {
		assertTrue(service.isMortgageWithinHomeValue(new BigDecimal("100000"), new BigDecimal("120000")));
		assertFalse(service.isMortgageWithinHomeValue(new BigDecimal("130000"), new BigDecimal("120000")));
	}
}*/
