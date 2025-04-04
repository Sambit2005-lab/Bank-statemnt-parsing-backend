package com.bank.statement.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ParseRequest {
    @NotBlank
    private String filePath;
    private String password;
}
