package com.prince.design.connection_pool.exception;

public class ConnectionNotAvailableException extends RuntimeException {

    public ConnectionNotAvailableException(String message) {
        super(message);
    }

    public ConnectionNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionNotAvailableException(Throwable cause) {
        super(cause);
    }
}
