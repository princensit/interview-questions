package com.prince.design.connection_pool.exception;

/**
 * @author Prince Raj
 */
public class ConnectionCreationException extends RuntimeException {

    public ConnectionCreationException(String message) {
        super(message);
    }

    public ConnectionCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionCreationException(Throwable cause) {
        super(cause);
    }
}
