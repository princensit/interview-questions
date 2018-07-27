package com.prince.design.vending_machine.exception;

/**
 * @author Prince Raj
 */
public class ItemNotAvailableException extends RuntimeException {

    private String message;

    public ItemNotAvailableException(String message) {
        this.message = message;
    }

    public ItemNotAvailableException(String message, String message1) {
        super(message);
        this.message = message1;
    }

    public ItemNotAvailableException(String message, Throwable cause, String message1) {
        super(message, cause);
        this.message = message1;
    }

    public ItemNotAvailableException(Throwable cause, String message) {
        super(cause);
        this.message = message;
    }

    public ItemNotAvailableException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace,
            String message1) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
    }
}
