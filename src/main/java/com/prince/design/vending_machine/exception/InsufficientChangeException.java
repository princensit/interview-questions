package com.prince.design.vending_machine.exception;

/**
 * @author Prince Raj
 */
public class InsufficientChangeException extends RuntimeException {

    private String message;

    public InsufficientChangeException(String message) {
        this.message = message;
    }

    public InsufficientChangeException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public InsufficientChangeException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public InsufficientChangeException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public InsufficientChangeException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace,
            String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
