package com.bankapplication.homeloan.controller.test;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bankapplication.homeloan.controller.MortgageRateController;
import com.bankapplication.homeloan.model.MortgageCheckRequest;
import com.bankapplication.homeloan.model.MortgageCheckResult;
import com.bankapplication.homeloan.model.MortgageRate;
import com.bankapplication.homeloan.service.MortageRateService;

@ExtendWith(MockitoExtension.class)
class MortgageRateControllerTest {

	@Mock
	private MortageRateService mortageRateService;

	@InjectMocks
	private MortgageRateController mortgageRateController;

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(mortgageRateController).build();
	}

	@Test
	void testGetInterestRates() throws Exception {
		List<MortgageRate> mockRates = Arrays.asList(new MortgageRate(15, 3.5, null), new MortgageRate(20, 4.0, null),
				new MortgageRate(30, 4.5, null));

		when(mortageRateService.getInterestRates()).thenReturn(mockRates);

		mockMvc.perform(get("/api/mortgage/interest-rates")).andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(3)).andExpect(jsonPath("$[0].maturityPeriod").value(15))
				.andExpect(jsonPath("$[0].interestRate").value(3.5));
	}

	@Test
    void testCheckMortgage() throws Exception {
        MortgageCheckRequest request = new MortgageCheckRequest(
                new BigDecimal("100000"),
                new BigDecimal("30000"),
                20,
                new BigDecimal("120000"));

        MortgageCheckResult mockResult = new MortgageCheckResult(true, new BigDecimal("500.00"));
        when(mortageRateService.checkMortgage(any())).thenReturn(mockResult);

        mockMvc.perform(post("/api/mortgage/mortgage-check")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"loanValue\":100000,\"income\":30000,\"maturityPeriod\":20,\"homeValue\":120000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feasible").value(true))
                .andExpect(jsonPath("$.monthlyPayment").value(500.00));

    }
	
	
	
	
}
