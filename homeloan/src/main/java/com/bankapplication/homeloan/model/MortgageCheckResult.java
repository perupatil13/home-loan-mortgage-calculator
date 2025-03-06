package com.bankapplication.homeloan.model;

import java.math.BigDecimal;

/*public record MortgageCheckResult(boolean mortageFeasible, BigDecimal monthlyPayment) {
    // Constructor, getters, toString(), equals(), and hashCode() are automatically generated
}*/


public class MortgageCheckResult {
	
	// private double monthlyPayment;
	private boolean mortageFeasible;
    private BigDecimal monthlyPayment;
	public MortgageCheckResult(boolean mortageFeasible, BigDecimal monthlyPayment) {
		super();
		this.mortageFeasible = mortageFeasible;
		this.monthlyPayment = monthlyPayment;
	}
    
	public boolean isFeasible() {
        return mortageFeasible;
    }

    public BigDecimal getMonthlyPayment() {
        return monthlyPayment;
    }
}
