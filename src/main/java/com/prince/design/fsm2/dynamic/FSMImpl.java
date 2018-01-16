package com.prince.design.fsm2.dynamic;

import com.prince.design.fsm2.dynamic.state.State;

/**
 * @author Prince Raj
 */
public class FSMImpl implements FSM {

    private State currentState;

    private StateEngine stateEngine;

    @Override
    public State getState() {
        return currentState;
    }

    @Override
    public void doAction(Input in) {

    }
}
