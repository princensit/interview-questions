package com.prince.design.fsm.predefined;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Prince Raj
 */
public class StateEngineImpl implements StateEngine {

    // x - state, y - action, value = new state
    private int[][] transition = {
            {-1, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, 3, 2, 6, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, 3, 4, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, 6, 5, -1},
            {-1, -1, -1, -1, -1, -1, 3, 5, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1},};

    private final Map<State, Action> stateActions = new HashMap<>();

    @Override
    public void registerAction(State oldState, Action action, State newState) {

    }

    public void execute(Input in) {

    }
}
