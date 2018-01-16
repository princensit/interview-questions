package com.prince.design.fsm2.dynamic;

import com.prince.design.fsm2.dynamic.action.Action;
import com.prince.design.fsm2.dynamic.state.State;

/**
 * @author Prince Raj
 */
public interface StateEngine {

    void add(State oldState, Action action, State newState);
}
