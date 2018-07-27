package com.prince.design.vending_machine.exception;

/**
 * @author Prince Raj
 */
public class NotFullPaidException extends RuntimeException {

    private String message;

    private long remaining;

    public NotFullPaidException(String message, long remaining) {
        this.message = message;
        this.remaining = remaining;
    }

    public NotFullPaidException(String message, String message1, long remaining) {
        super(message);
        this.message = message1;
        this.remaining = remaining;
    }

    public NotFullPaidException(String message, Throwable cause, String message1, long remaining) {
        super(message, cause);
        this.message = message1;
        this.remaining = remaining;
    }

    public NotFullPaidException(Throwable cause, String message, long remaining) {
        super(cause);
        this.message = message;
        this.remaining = remaining;
    }

    public NotFullPaidException(
            String message,
            Throwable cause,
            boolean enableSuppression,
            boolean writableStackTrace,
            String message1,
            long remaining) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.remaining = remaining;
    }
}
