package com.prince.design.fsm.dynamic;

import com.prince.design.fsm.dynamic.action.Action;
import com.prince.design.fsm.dynamic.state.State;

/**
 * @author Prince Raj
 */
public interface StateEngine {

    void add(State oldState, Action action, State newState);
}
