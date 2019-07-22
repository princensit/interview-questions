package com.prince.design.connection_pool.exception;

/**
 * @author Prince Raj
 */
public class ConnectionNotClosedException extends RuntimeException {

    public ConnectionNotClosedException(String message) {
        super(message);
    }

    public ConnectionNotClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionNotClosedException(Throwable cause) {
        super(cause);
    }
}
