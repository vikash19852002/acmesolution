package com.acme.banksolution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication (scanBasePackages = "com.acme.banksolution")
public class AcmesolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AcmesolutionApplication.class, args);
	}

}