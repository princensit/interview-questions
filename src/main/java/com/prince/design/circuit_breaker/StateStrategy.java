package com.prince.design.circuit_breaker;

/**
 * @author Prince Raj
 */
public abstract class StateStrategy {

    public boolean isCheckIntervalFinished(
            final EventCountCircuitBreaker breaker,
            final CheckIntervalData currentData,
            final long now) {
        return (now - currentData.getCheckIntervalStart()) > fetchCheckInterval(breaker);
    }

    protected abstract boolean isStateTransition(
            EventCountCircuitBreaker breaker,
            CheckIntervalData currentData,
            CheckIntervalData nextData);

    protected abstract long fetchCheckInterval(EventCountCircuitBreaker breaker);
}
