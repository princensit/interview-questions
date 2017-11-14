package com.prince.design.fsm.predefined;

/**
 * @author Prince Raj
 */
public interface StateEngine {

    void registerAction(State oldState, Action action, State newState);
}
