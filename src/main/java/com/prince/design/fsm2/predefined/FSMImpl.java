package com.prince.design.fsm2.predefined;

/**
 * http://www.java2s.com/Code/Java/Collections-Data-Structure/AprogrammableFiniteStateMachineimplementation.htm
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
