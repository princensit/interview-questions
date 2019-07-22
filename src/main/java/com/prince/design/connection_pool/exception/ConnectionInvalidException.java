package com.prince.design.connection_pool.exception;

/**
 * @author Prince Raj
 */
public class ConnectionInvalidException extends RuntimeException {

    public ConnectionInvalidException(String message) {
        super(message);
    }

    public ConnectionInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionInvalidException(Throwable cause) {
        super(cause);
    }
}
