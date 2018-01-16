package com.prince.design.fsm2.predefined;

/**
 * @author Prince Raj
 */
public interface FSM {

    /**
     * Get the current state of this fsm.predefined.
     *
     * @return
     */
    State getState();

    /**
     * Perform the action and transition to the next state based on the current state of the FSM and the input.
     */
    void doAction(Input in);
}
