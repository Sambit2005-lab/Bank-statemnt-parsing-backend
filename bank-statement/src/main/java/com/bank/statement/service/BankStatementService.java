package com.bank.statement.service;

import com.bank.statement.dto.request.PasswordGenRequest;
import com.bank.statement.dto.response.StatementResponse;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service interface for bank statement operations
 */
public interface BankStatementService {
    StatementResponse parseStatement(MultipartFile file, String password);
    String generatePassword(PasswordGenRequest request);
}
