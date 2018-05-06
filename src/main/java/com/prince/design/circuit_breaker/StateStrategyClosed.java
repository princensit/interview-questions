package com.prince.design.circuit_breaker;

/**
 * @author Prince Raj
 */
public class StateStrategyClosed extends StateStrategy {

    @Override
    public boolean isStateTransition(
            EventCountCircuitBreaker breaker,
            CheckIntervalData currentData,
            CheckIntervalData nextData) {
        return nextData.getEventCount() > breaker.getOpeningThreshold();
    }

    @Override
    public long fetchCheckInterval(EventCountCircuitBreaker breaker) {
        return breaker.getOpeningInterval();
    }
}
