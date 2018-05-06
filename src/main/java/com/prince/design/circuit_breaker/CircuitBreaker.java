package com.prince.design.circuit_breaker;

/**
 * @author Prince Raj
 */
public interface CircuitBreaker<T> {

    boolean isOpen();

    boolean isClosed();

    void open();

    void close();

    /**
     * Checks the state of this circuit breaker and changes it if necessary. The return
     * value indicates whether the circuit breaker is now in state {@code CLOSED}; a value
     * of <strong>true</strong> typically means that the current operation can continue.
     *
     * @return <strong>true</strong> if the circuit breaker is now closed;
     * <strong>false</strong> otherwise
     */
    boolean checkState();

    /**
     * Increments the monitored value and performs a check of the current state of this
     * circuit breaker. This method works like {@link #checkState()}, but the monitored
     * value is incremented before the state check is performed.
     *
     * @param increment value to increment in the monitored value of the circuit breaker
     * @return <strong>true</strong> if the circuit breaker is now closed;
     * <strong>false</strong> otherwise
     */
    boolean incrementAndCheckState(T increment);
}
