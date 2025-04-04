package com.bank.statement.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.List;

/**
 * Standard error response structure for the API
 */
@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    /**
     * HTTP status code
     */
    private int status;

    /**
     * HTTP status reason phrase
     */
    private String error;

    /**
     * Error message for client
     */
    private String message;

    /**
     * Timestamp when error occurred
     */
    @Builder.Default
    private Instant timestamp = Instant.now();

    /**
     * Detailed error messages (for validation errors)
     */
    private List<FieldError> fieldErrors;

    /**
     * API path where error occurred
     */
    private String path;

    /**
     * Nested class for field-level validation errors
     */
    @Data
    @Builder
    public static class FieldError {
        private String field;
        private String code;
        private String message;
        private Object rejectedValue;
    }
}
