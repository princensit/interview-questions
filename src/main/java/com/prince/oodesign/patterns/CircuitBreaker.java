package com.prince.oodesign.patterns;

/**
 * https://martinfowler.com/bliki/CircuitBreaker.html
 *
 * https://en.wikipedia.org/wiki/Circuit_breaker_design_pattern
 *
 * @author Prince Raj
 */
public class CircuitBreaker {

    public static void main(String[] args) {
        System.out
                .println("Circuit breaker is a design pattern sed to detect failures and encapsulates the logic of preventing a failure from constantly recurring, during maintenance, temporary external system failure or unexpected system difficulties.");
        System.out
                .println("Generally Circuit Breaker can be used to check the availability of an external service. An external service can be a database server or a web service used by the application.");
        System.out
                .println("Circuit breaker detects failures and prevents the application from trying to perform the action that is doomed to fail (until it's safe to retry).");
        System.out.println("Circuit Breaker records the state of the external service on a given interval.");
        System.out
                .println("Before the external service is used from the application, the storage layer (ex - redis, memcached) is queried to retrieve the current state.");
        System.out
                .println("You might also have different thresholds for different errors, such as a threshold of 10 for timeouts but 3 for connection failures.");
        System.out
                .println("Netflix have open-sourced Hystrix, a sophisticated tool for dealing with latency and fault tolerance for distributed systems. It includes an implementation of the circuit breaker pattern with the thread pool limit");
    }
}
