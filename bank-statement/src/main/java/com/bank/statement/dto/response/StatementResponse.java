package com.bank.statement.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class StatementResponse {
    private String customerName;
    private String email;
    private String accountNumber;
    private double openingBalance;
    private double closingBalance;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate statementStartDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate statementEndDate;
}
