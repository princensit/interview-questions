package com.prince.design.circuit_breaker;

/**
 * An exception class used for reporting runtime error conditions related to circuit breakers.
 *
 * @author Prince Raj
 */
public class CircuitBreakingException extends RuntimeException {

    private static final long serialVersionUID = 2222176654686911121L;

    public CircuitBreakingException() {
        super();
    }

    public CircuitBreakingException(String message) {
        super(message);
    }

    public CircuitBreakingException(Throwable cause) {
        super(cause);
    }

    public CircuitBreakingException(String message, Throwable cause) {
        super(message, cause);
    }
}
