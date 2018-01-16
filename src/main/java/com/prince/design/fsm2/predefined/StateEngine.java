package com.prince.design.fsm2.predefined;

/**
 * @author Prince Raj
 */
public interface StateEngine {

    void registerAction(State oldState, Action action, State newState);
}
