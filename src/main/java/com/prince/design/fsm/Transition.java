package com.prince.design.fsm;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Create a new transition. See the documentation for addEvent and addTransition in FSM.
 *
 * @author Prince Raj
 */
@Data
@Slf4j
public class Transition {

    private final String eventName;

    private final String startState;

    private final String endState;

    /**
     * Create a transition object that responds to the given event when in the given startState, and puts the FSM into
     * the endState provided.
     */
    public Transition(String eventName, String startState, String endState) {
        this.eventName = eventName;
        this.startState = startState;
        this.endState = endState;
    }

    /**
     * Override this to have FSM execute code immediately before following a state transition.
     */
    public void doBeforeTransition() {
        System.out.println("doBeforeTransition() for " + startState + " -> " + endState);
    }

    /**
     * Override this to have FSM execute code immediately after following a state transition.
     */
    public void doAfterTransition() {
        System.out.println("doAfterTransition() for " + startState + " -> " + endState);
    }
}
