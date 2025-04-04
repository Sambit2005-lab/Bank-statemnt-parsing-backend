package com.bank.statement.service.impl;

import com.bank.statement.dto.request.PasswordGenRequest;
import com.bank.statement.dto.response.StatementResponse;
import com.bank.statement.exception.InvalidPDFException;
import com.bank.statement.service.BankStatementService;
import com.bank.statement.service.LLMService;
import com.bank.statement.util.PDFParser;
import com.bank.statement.util.PasswordGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.concurrent.CompletableFuture;

@Slf4j  // This enables the 'log' variable
@Service
@RequiredArgsConstructor
public class BankStatementServiceImpl implements BankStatementService {

    private final LLMService llmService;
    private final PDFParser pdfParser;
    private final PasswordGenerator passwordGenerator;

    @Override
    @Cacheable(value = "statements", key = "#file.originalFilename.concat(#password ?: '')")
    public StatementResponse parseStatement(MultipartFile file, String password) {
        validatePDF(file);

        try {
            String extractedText = pdfParser.extractText(file, password);
            StatementResponse response = llmService.parseStatement(extractedText);

            log.info("Parsed statement for account: {}", response.getAccountNumber());
            return response;
        } catch (IOException e) {
            throw new InvalidPDFException("Failed to process PDF file", e);
        }
    }

    @Async
    public CompletableFuture<StatementResponse> asyncParseStatement(MultipartFile file, String password) {
        return CompletableFuture.completedFuture(parseStatement(file, password));
    }

    @Override
    public String generatePassword(PasswordGenRequest request) {
        return passwordGenerator.generateSecurePassword(
                request.getFirstName(),
                request.getDob(),
                request.getAccountType()
        );
    }

    private void validatePDF(MultipartFile file) {
        if (file.isEmpty() || !"application/pdf".equals(file.getContentType())) {
            throw new InvalidPDFException("Invalid PDF file");
        }
    }
}
