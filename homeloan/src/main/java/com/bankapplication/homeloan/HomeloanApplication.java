package com.bankapplication.homeloan;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HomeloanApplication {

	@Value("${spring.profiles.active:default}")
	private String activeProfile;

	public static void main(String[] args) {
		SpringApplication.run(HomeloanApplication.class, args);
	}

}

//Validating Spring profile pom file changes 
/*
 * @SpringBootApplication public class HomeloanApplication implements
 * CommandLineRunner{
 * 
 * @Value("${spring.profiles.active:default}") private String activeProfile;
 * 
 * public static void main(String[] args) {
 * SpringApplication.run(HomeloanApplication.class, args); }
 * 
 * @Override public void run(String... args) {
 * System.out.println("Active Spring Profile: " + activeProfile); } }
 */