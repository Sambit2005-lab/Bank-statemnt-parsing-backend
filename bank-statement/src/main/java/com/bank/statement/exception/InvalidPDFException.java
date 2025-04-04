package com.bank.statement.exception;

/**
 * Exception thrown when an invalid PDF file is encountered
 */
public class InvalidPDFException extends RuntimeException {

    /**
     * Constructs a new InvalidPDFException with default message
     */
    public InvalidPDFException() {
        super("Invalid PDF file provided");
    }

    /**
     * Constructs a new InvalidPDFException with custom message
     * @param message the detail message
     */
    public InvalidPDFException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidPDFException with custom message and cause
     * @param message the detail message
     * @param cause the root cause
     */
    public InvalidPDFException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidPDFException with cause
     * @param cause the root cause
     */
    public InvalidPDFException(Throwable cause) {
        super(cause);
    }
}
