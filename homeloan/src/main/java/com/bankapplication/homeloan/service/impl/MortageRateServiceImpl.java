package com.bankapplication.homeloan.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bankapplication.homeloan.exception.MortgageNotFeasibleException;
import com.bankapplication.homeloan.model.MortgageCheckRequest;
import com.bankapplication.homeloan.model.MortgageCheckResult;
import com.bankapplication.homeloan.model.MortgageRate;
import com.bankapplication.homeloan.service.MortageRateService;

@Service
public class MortageRateServiceImpl implements MortageRateService {

	@Override
	public List<MortgageRate> getInterestRates() {
		List<MortgageRate> mortageRateList = Arrays.asList(
				new MortgageRate(15, 3.5, new Timestamp(System.currentTimeMillis())),
				new MortgageRate(20, 4.0, new Timestamp(System.currentTimeMillis())),
				new MortgageRate(30, 4.5, new Timestamp(System.currentTimeMillis())));

		return mortageRateList;
	}

	@Override
	public MortgageCheckResult checkMortgage(MortgageCheckRequest request) {

		MortgageCheckResult mortgageCheckResult;

		

		if (!isMortgageFeasible(request)) {
			throw new MortgageNotFeasibleException("Mortgage is Not Feasible!!!");
		} else {
			//boolean mortgageFeasible = isMortgageFeasible(request);
			// Ensure the monthly payment is always BigDecimal
			BigDecimal monthlyPayment = true
					? calculateMonthlyPayment(request.getLoanValue(), new BigDecimal("4.0"),
							request.getMaturityPeriod())
					: BigDecimal.ZERO; // Use BigDecimal.ZERO for consistency
			mortgageCheckResult = new MortgageCheckResult(true, monthlyPayment);
		}

		return mortgageCheckResult;
	}

	private BigDecimal calculateMonthlyPayment(BigDecimal loanAmount, BigDecimal annualInterestRate, int years) {
		// Convert the annual interest rate to monthly interest rate
		BigDecimal monthlyInterestRate = annualInterestRate.divide(new BigDecimal("100"), 10, RoundingMode.HALF_UP)
				.divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);

		BigDecimal numberOfPayments = new BigDecimal(years * 12); // Convert number of payments to BigDecimal

		// Handle zero interest rate case (simple division)
		if (monthlyInterestRate.compareTo(BigDecimal.ZERO) == 0) {
			return loanAmount.divide(numberOfPayments, 2, RoundingMode.HALF_UP); // Return loanAmount / numberOfPayments
		}

		// Standard mortgage payment formula: M = P * r * (1 + r)^n / ((1 + r)^n - 1)
		BigDecimal onePlusR = BigDecimal.ONE.add(monthlyInterestRate);
		BigDecimal powerOfOnePlusR = onePlusR.pow(numberOfPayments.intValue());

		BigDecimal numerator = loanAmount.multiply(monthlyInterestRate).multiply(powerOfOnePlusR);
		BigDecimal denominator = powerOfOnePlusR.subtract(BigDecimal.ONE);

		return numerator.divide(denominator, 2, RoundingMode.HALF_UP);
	}

	private boolean isMortgageFeasible(MortgageCheckRequest request) {
		// Mortgage feasibility based on income and home value
		BigDecimal fourTimesIncome = request.getIncome().multiply(new BigDecimal("4"));
		return request.getLoanValue().compareTo(fourTimesIncome) <= 0
				&& isMortgageWithinHomeValue(request.getLoanValue(), request.getHomeValue());
	}

	// Updated to use BigDecimal instead of double
	public static boolean isMortgageWithinHomeValue(BigDecimal loanAmount, BigDecimal homeValue) {
		return loanAmount.compareTo(homeValue) <= 0;
	}
}
