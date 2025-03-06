package com.bankapplication.homeloan.model;

import java.sql.Timestamp;

/*
public record MortgageRate(int maturityPeriod, double interestRate, Timestamp lastUpdate) {
    // Constructor will be auto-generated with parameters
}
*/

public class MortgageRate {
	private int maturityPeriod;
	private double interestRate;
	private Timestamp lastUpdate;

	public MortgageRate(int maturityPeriod, double interestRate, Timestamp lastUpdate) {
		this.maturityPeriod = maturityPeriod;
		this.interestRate = interestRate;
		this.lastUpdate = lastUpdate;
	}

	public MortgageRate(int maturityPeriod, double interestRate) {
		// TODO Auto-generated constructor stub
		this.maturityPeriod = maturityPeriod;
		this.interestRate = interestRate;
	}

	public int getMaturityPeriod() {
		return maturityPeriod;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

}
