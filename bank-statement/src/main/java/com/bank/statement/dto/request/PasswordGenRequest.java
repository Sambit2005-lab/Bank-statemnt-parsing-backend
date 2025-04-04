package com.bank.statement.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for password generation request
 *
 * Validates:
 * - firstName: non-blank
 * - dob: non-blank and in YYYY-MM-DD format
 * - accountType: non-blank
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PasswordGenRequest {

    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @NotBlank(message = "Date of birth cannot be blank")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Date of birth must be in YYYY-MM-DD format")
    private String dob;

    @NotBlank(message = "Account type cannot be blank")
    private String accountType;
}