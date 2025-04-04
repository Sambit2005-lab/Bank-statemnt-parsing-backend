package com.bank.statement.exception;

/**
 * Custom exception for LLM (Large Language Model) service related errors
 */
public class LLMServiceException extends RuntimeException {

    /**
     * Constructs a new LLMServiceException with the specified detail message
     * @param message the detail message
     */
    public LLMServiceException(String message) {
        super(message);
    }

    /**
     * Constructs a new LLMServiceException with the specified detail message and cause
     * @param message the detail message
     * @param cause the root cause
     */
    public LLMServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new LLMServiceException with the specified cause
     * @param cause the root cause
     */
    public LLMServiceException(Throwable cause) {
        super(cause);
    }
}
