package com.bankapplication.homeloan.model;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class MortgageCheckRequest {

	@NotNull(message = "Income cannot be null")
	@Positive(message = "Income must be greater than zero")
	private BigDecimal income;
	//private double income;

	@NotNull(message = "Maturity period cannot be null")
	@Min(value = 1, message = "Maturity period must be at least 1 year")
	private int maturityPeriod;

	@NotNull(message = "Loan value cannot be null")
	@Positive(message = "Loan value must be greater than zero")
	private BigDecimal loanValue;
	//private double loanValue

	@NotNull(message = "Home value cannot be null")
	@Positive(message = "Home value must be greater than zero")
	private BigDecimal homeValue;
	//private double homeValue

	public MortgageCheckRequest(BigDecimal income, BigDecimal loanValue, int maturityPeriod, BigDecimal homeValue) {
		this.income = income;
		this.maturityPeriod = maturityPeriod;
		this.loanValue = loanValue;
		this.homeValue = homeValue;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public int getMaturityPeriod() {
		return maturityPeriod;
	}

	public BigDecimal getLoanValue() {
		return loanValue;
	}

	public BigDecimal getHomeValue() {
		return homeValue;
	}
}
