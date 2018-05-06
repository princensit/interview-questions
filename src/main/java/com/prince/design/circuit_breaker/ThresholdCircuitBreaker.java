package com.prince.design.circuit_breaker;

import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 * It contains an internal counter that starts in zero, and each call increments the counter by a given amount. If the
 * threshold is zero, the circuit breaker will be in a permanent <em>open</em> state.
 * </p>
 *
 * <p>
 * An example of use case could be a memory circuit breaker.
 * </p>
 *
 * <pre>
 * long threshold = 10L;
 * ThresholdCircuitBreaker breaker = new ThresholdCircuitBreaker(threshold);
 * ...
 * public void handleRequest(Request request) {
 *     long memoryUsed = estimateMemoryUsage(request);
 *     if (breaker.incrementAndCheckState(memoryUsed)) {
 *         // actually handle this request
 *     } else {
 *         // do something else, e.g. send an error code
 *     }
 * }
 * </pre>
 *
 * @author Prince Raj
 */
public class ThresholdCircuitBreaker extends AbstractCircuitBreaker<Long> {

    /**
     * The initial value of the internal counter.
     */
    private static final long INITIAL_COUNT = 0L;

    /**
     * The threshold.
     */
    private final long threshold;

    /**
     * Controls the amount used.
     */
    private final AtomicLong used;

    /**
     * <p>
     * Creates a new instance of {@code ThresholdCircuitBreaker} and initializes the threshold.
     * </p>
     *
     * @param threshold the threshold.
     */
    public ThresholdCircuitBreaker(final long threshold) {
        super();
        this.used = new AtomicLong(INITIAL_COUNT);
        this.threshold = threshold;
    }

    /**
     * Gets the threshold.
     *
     * @return the threshold
     */
    public long getThreshold() {
        return threshold;
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * Resets the internal counter back to its initial value (zero).
     * </p>
     */
    @Override
    public void close() {
        super.close();
        this.used.set(INITIAL_COUNT);
    }

    @Override
    public boolean checkState() {
        return isOpen();
    }

    /**
     * {@inheritDoc}
     *
     * <p>
     * If the threshold is zero, the circuit breaker will be in a permanent <em>open</em> state.
     * </p>
     */
    @Override
    public boolean incrementAndCheckState(final Long increment) throws CircuitBreakingException {
        if (threshold == 0) {
            open();
        }

        final long used = this.used.addAndGet(increment);
        if (used > threshold) {
            open();
        }

        return checkState();
    }
}
