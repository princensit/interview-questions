package com.prince.design.circuit_breaker;

/**
 * @author Prince Raj
 */
public class StateStrategyOpen extends StateStrategy {

    @Override
    public boolean isStateTransition(
            final EventCountCircuitBreaker breaker,
            final CheckIntervalData currentData,
            final CheckIntervalData nextData) {
        return nextData.getCheckIntervalStart() != currentData.getCheckIntervalStart()
                && currentData.getEventCount() < breaker.getClosingThreshold();
    }

    @Override
    protected long fetchCheckInterval(final EventCountCircuitBreaker breaker) {
        return breaker.getClosingInterval();
    }
}