package com.prince.design.circuit_breaker;

/**
 * @author Prince Raj
 */
enum State {
    OPEN {

        @Override
        public State oppositeState() {
            return CLOSED;
        }
    },
    CLOSED {

        @Override
        public State oppositeState() {
            return OPEN;
        }
    };

    public abstract State oppositeState();
}