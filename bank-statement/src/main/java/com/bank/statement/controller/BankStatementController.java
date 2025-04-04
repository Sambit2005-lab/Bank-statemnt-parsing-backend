package com.bank.statement.controller;

import com.bank.statement.dto.request.ParseRequest;
import com.bank.statement.dto.request.PasswordGenRequest;
import com.bank.statement.dto.response.ApiResponse;
import com.bank.statement.dto.response.StatementResponse;
import com.bank.statement.service.BankStatementService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST controller for bank statement operations
 */
@RestController
@RequestMapping("/v1/statements")
@RequiredArgsConstructor
@Tag(name = "Bank Statement API", description = "Operations for parsing bank statements")
public class BankStatementController {

    private final BankStatementService statementService;

    @Operation(summary = "Parse bank statement PDF")
    @PostMapping(value = "/parse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<StatementResponse>> parseStatement(
            @RequestParam("file") MultipartFile file,
            @RequestParam(required = false) String password) {
        StatementResponse response = statementService.parseStatement(file, password);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @Operation(summary = "Generate account password")
    @PostMapping("/generate-password")
    public ResponseEntity<ApiResponse<String>> generatePassword(
            @RequestBody @Valid PasswordGenRequest request) {
        String password = statementService.generatePassword(request);
        return ResponseEntity.ok(ApiResponse.success(password));
    }
}
