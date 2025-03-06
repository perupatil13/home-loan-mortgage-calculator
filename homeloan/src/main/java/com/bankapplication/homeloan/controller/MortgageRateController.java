package com.bankapplication.homeloan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankapplication.homeloan.model.MortgageCheckRequest;
import com.bankapplication.homeloan.model.MortgageCheckResult;
import com.bankapplication.homeloan.model.MortgageRate;
import com.bankapplication.homeloan.service.MortageRateService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/mortgage")
@Tag(name = "Mortgage Rate Controller", description = "APIs for mortgage calculations")
public class MortgageRateController {

	private final MortageRateService mortageRateService;

	@Autowired
	public MortgageRateController(final MortageRateService mortageRateService) {
		this.mortageRateService = mortageRateService;
	}

	@GetMapping("/interest-rates")
	public ResponseEntity getInterestRates() {
		List<MortgageRate> mortageRateLst = mortageRateService.getInterestRates();
		return new ResponseEntity<>(mortageRateLst,HttpStatus.OK);
	}

	@PostMapping("/mortgage-check")
	public ResponseEntity checkMortgage(@Valid @RequestBody MortgageCheckRequest request) {
		MortgageCheckResult mortgageValue = mortageRateService.checkMortgage(request);
		return new ResponseEntity<>(mortgageValue,HttpStatus.OK);
	}

	/*
	 * 
	 * Things to implement swagger 
	 * exception handler - custom exceptions. All the
	 * possibles status codes have not been covered. 
	 * data type correctly seperate
	 * business logic to service impl formula documentation Interest rates are
	 * hard-coded in controller test case for these 
	 * add logger & java doc 
	 * Spring profile 
	 * Spring security use recorders for mapping
	 * 
	 * 
	 */
}