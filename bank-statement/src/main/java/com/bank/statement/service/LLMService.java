package com.bank.statement.service;

import com.bank.statement.dto.response.StatementResponse;
import com.bank.statement.exception.LLMServiceException;

/**
 * Service interface for Large Language Model (LLM) operations
 */
public interface LLMService {

    /**
     * Parses bank statement text using LLM
     * @param extractedText Raw text extracted from PDF
     * @return Structured statement data
     * @throws LLMServiceException If LLM processing fails
     */
    StatementResponse parseStatement(String extractedText) throws LLMServiceException;

    /**
     * Generates a password based on user data using LLM
     * @param firstName User's first name
     * @param dob Date of birth in YYYY-MM-DD format
     * @param accountType Type of bank account
     * @return Generated password
     * @throws LLMServiceException If password generation fails
     */
    String generatePassword(String firstName, String dob, String accountType) throws LLMServiceException;
}
