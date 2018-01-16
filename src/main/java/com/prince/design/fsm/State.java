package com.prince.design.fsm;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;

/**
 * Represents a state with some number of associated transitions.
 *
 * @author Prince Raj
 */
@Data
public class State {

    private final Map<String, Transition> transitions = new HashMap<>();

    private final Runnable entryCode;

    private final Runnable alwaysRunCode;

    private final Runnable exitCode;

    private String autoTransitionState = null;

    public State(Runnable entryCode, Runnable alwaysRunCode, Runnable exitCode) {
        this.entryCode = entryCode;
        this.alwaysRunCode = alwaysRunCode;
        this.exitCode = exitCode;
    }

    public void addTransition(Transition transition) {
        transitions.put(transition.getEventName(), transition);
    }

    public void runEntryCode() {
        if (entryCode != null) {
            entryCode.run();
        }
    }

    public void runAlwaysCode() {
        if (alwaysRunCode != null) {
            alwaysRunCode.run();
        }
    }

    public void runExitCode() {
        if (exitCode != null) {
            exitCode.run();
        }
    }
}
