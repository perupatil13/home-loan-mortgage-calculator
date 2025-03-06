package com.bankapplication.homeloan.service;

import java.util.List;

import com.bankapplication.homeloan.model.MortgageCheckRequest;
import com.bankapplication.homeloan.model.MortgageCheckResult;
import com.bankapplication.homeloan.model.MortgageRate;

public interface MortageRateService {

	List<MortgageRate> getInterestRates();

	MortgageCheckResult checkMortgage(MortgageCheckRequest request);

}
