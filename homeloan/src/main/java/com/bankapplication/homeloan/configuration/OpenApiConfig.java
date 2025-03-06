package com.bankapplication.homeloan.configuration;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
	@Bean
	public GroupedOpenApi mortgageApi() {
		// adding controller paths in pathsToMatch
		return GroupedOpenApi.builder().group("mortgage-api").pathsToMatch("/api/mortgage/**").build();
	}

	@Bean
	public GroupedOpenApi adminApi() {
		return GroupedOpenApi.builder().group("admin").pathsToMatch("/api/admin/**").build();
	}

	@Bean
	public GroupedOpenApi publicApi() {
		return GroupedOpenApi.builder().group("public").pathsToMatch("/swagger-ui/**").build();
	}

}

