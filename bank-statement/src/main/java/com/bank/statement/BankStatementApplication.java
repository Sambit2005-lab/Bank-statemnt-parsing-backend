package com.bank.statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * Main entry point for the Bank Statement Parser application
 */
@SpringBootApplication
@EnableAsync
@EnableCaching
public class BankStatementApplication {
	public static void main(String[] args) {
		SpringApplication.run(BankStatementApplication.class, args);
	}
}
