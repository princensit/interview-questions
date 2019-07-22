package com.prince.design.connection_pool.exception;

/**
 * @author Prince Raj
 */
public class ConnectionPoolExhaustException extends RuntimeException {

    public ConnectionPoolExhaustException(String message) {
        super(message);
    }

    public ConnectionPoolExhaustException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionPoolExhaustException(Throwable cause) {
        super(cause);
    }
}
