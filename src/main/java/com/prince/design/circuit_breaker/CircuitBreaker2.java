package com.prince.design.circuit_breaker;

/**
 * https://docs.microsoft.com/en-us/azure/architecture/patterns/circuit-breaker
 *
 * @author Prince Raj
 */
public class CircuitBreaker2 {

    /**
     * A circuit breaker acts as a proxy for operations that might fail. The proxy should monitor the number of recent
     * failures that have occurred, and use this information to decide whether to allow the operation to proceed, or
     * simply return an exception immediately.
     *
     * The proxy can be implemented as a state machine with the following states that mimic the functionality of an
     * electrical circuit breaker. <b>See image.</b>
     *
     * The Circuit Breaker pattern provides stability while the system recovers from a failure and minimizes the impact
     * on performance. It can help to maintain the response time of the system by quickly rejecting a request for an
     * operation that's likely to fail, rather than waiting for the operation to time out, or never return. If the
     * circuit breaker raises an event each time it changes state, this information can be used to monitor the health of
     * the part of the system protected by the circuit breaker, or to alert an administrator when a circuit breaker
     * trips to the Open state.
     *
     * The pattern is customizable and can be adapted according to the type of the possible failure. For example, you
     * can apply an increasing timeout timer to a circuit breaker.
     *
     * A circuit breaker should log all failed requests (and possibly successful requests) to enable an administrator to
     * monitor the health of the operation.
     *
     * A service can return HTTP 429 (Too Many Requests) if it is throttling the client, or HTTP 503 (Service
     * Unavailable) if the service is not currently available. The response can include additional information, such as
     * the anticipated duration of the delay.
     */
    public void breakCircuit() {

    }
}
