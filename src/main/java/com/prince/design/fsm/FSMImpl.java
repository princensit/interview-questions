package com.prince.design.fsm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * Reference:
 * http://www.java2s.com/Code/Java/Collections-Data-Structure/AprogrammableFiniteStateMachineimplementation.htm
 *
 * <p>
 * A programmable Finite State Machine implementation. To use this class, establish any number of states with the
 * 'addState' method. Next, add some FSM.Transition objects (the Transition class is designed to be used as an
 * superclass for your anonymous implementation). Each Transition object has two useful methods that can be defined by
 * your implementation: doBeforeTransition and doAfterTransition. To drive your FSM, simply give it events using the
 * addEvent method with the name of an event. If there is an appropriate transition for the current state, the
 * transition's doBefore/doAfter methods are called and the FSM is put into the new state. It is legal (and highly
 * useful) for the start/end states of a transition to be the same state.
 *
 * @author Prince Raj
 */
@Data
@Slf4j
public class FSMImpl implements FSM {

    private static final String FSM = "FSM";

    private final Map<String, State> states = new HashMap<>();

    private final List<ChangeListener> changeListeners = new ArrayList<>();

    private final String name;

    private String currentState = null;

    // turn debugging on/off
    private boolean debug;

    /**
     * Create a blank FSM with the given name
     *
     * @param name name of FSM
     */
    public FSMImpl(String name) {
        this.name = name;
    }

    @Override
    public void addState(String state) {
        addState(state, null, null, null);
    }

    /**
     * Establish a new state the FSM is aware of. If the FSM does not currently have any states, this state becomes the
     * current, initial state. This is the only way to put the FSM into an initial state.
     * <p>
     * The entryCode, exitCode, and alwaysRunCode are Runnable(s) that the FSM executes during the course of a
     * transition. entryCode and exitCode are run only if the transition is between two distinct states (i.e. A->B where
     * A != B). alwaysRunCode is executed even if the transition is re-entrant (i.e. A->B where A = B).
     **/
    @Override
    public void addState(String state, Runnable entryCode, Runnable alwaysRunCode, Runnable exitCode) {
        boolean isInitial = states.size() == 0;
        if (!states.containsKey(state)) {
            states.put(state, new State(entryCode, alwaysRunCode, exitCode));
        }

        if (isInitial) {
            setCurrentState(state);
        }
    }

    /**
     * There are cases where a state is meant to be transitional, and the FSM should always immediately transition to
     * some other state. In those cases, use this method to specify the start and end states. After the startState has
     * fully transitioned (and any change events have been fired) the FSM will check to see if there is another state
     * that the FSM should automatically transition to. If there is one, addEvent(endState) is called.
     *
     * Note: this creates a special transition in the lookup table called "(auto)".
     */
    @Override
    public void setAutoTransition(String startState, String endState) {
        if (debug) {
            log.debug(FSM, "Establishing auto transition for {} -> {}", startState, endState);
        }
        states.get(startState).setAutoTransitionState(endState);
        addTransition(new Transition("(auto)", startState, endState));
    }

    /**
     * Establish a new transition. You might use this method something like this:
     *
     * <pre>
     * fsm.addTransition(new FSM.Transition(&quot;someEvent&quot;, &quot;firstState&quot;, &quot;secondState&quot;) {
     *
     *     public void doBeforeTransition() {
     *         System.out.println(&quot;about to transition...&quot;);
     *     }
     *
     *     public void doAfterTransition() {
     *         fancyOperation();
     *     }
     * });
     * </pre>
     */
    @Override
    public void addTransition(Transition transition) {
        State state = states.get(transition.getStartState());
        if (state == null) {
            throw new NoSuchElementException("Missing state: " + transition.getStartState());
        }

        state.addTransition(transition);
    }

    /**
     * Feed the FSM with the named event. If the current state has a transition that responds to the given event, the
     * FSM will performed the transition using the following steps, assume start and end states are A and B:
     *
     * <ol>
     * <li>Execute the transition's "doBeforeTransition" method</li>
     * <li>Run fsm.setState(B) -- see docs for that method</li>
     * <li>Execute the transition's "doAfterTransition" method</li>
     * <li>Fire a change event, notifying interested observers that the transition has completed.</li>
     * <li>Now firmly in state B, see if B has a third state C that we must automatically transition to via addEvent(C).
     * </li>
     * </ol>
     */
    @Override
    public void addEvent(String eventName) {
        State state = states.get(currentState);
        Transition transition = state.getTransitions().get(eventName);
        if (transition != null) {
            String startState = transition.getStartState();
            String endState = transition.getEndState();

            if (debug) {
                log.debug(FSM, "Event: {}, {} -> {}", eventName, startState, endState);
            }
            transition.doBeforeTransition();
            setState(endState, false);
            transition.doAfterTransition();

            fireChangeEvent();

            String autoTransitionState = states.get(endState).getAutoTransitionState();
            if (autoTransitionState != null) {
                if (debug) {
                    log.debug(FSM, "Automatically transitioning from {} to {}", endState, autoTransitionState);
                }
                addEvent("(auto)");
            }
        }
    }

    /**
     * Sets the current state without following a transition. This will cause a change event to be fired.
     */
    private void setState(String state) {
        setState(state, true);
    }

    /**
     * Sets the current state without following a transition, and optionally causing a change event to be triggered.
     * During state transitions (with the 'addEvent' method), this method is used with the triggerEvent parameter as
     * false.
     *
     * The FSM executes non-null runnables according to the following logic, given start and end states A and B:
     *
     * <ol>
     * <li>If A and B are distinct, run A's exit code.</li>
     * <li>Record current state as B.</li>
     * <li>Run B's "alwaysRunCode".</li>
     * <li>If A and B are distinct, run B's entry code.</li>
     * </ol>
     */
    private void setState(String state, boolean triggerEvent) {
        boolean runExtraCode = !state.equals(currentState);
        if (runExtraCode && currentState != null) {
            states.get(currentState).runExitCode();
        }

        currentState = state;

        states.get(currentState).runAlwaysCode();

        if (runExtraCode) {
            states.get(currentState).runEntryCode();
        }

        if (triggerEvent) {
            fireChangeEvent();
        }
    }

    /**
     * Add a change listener -- this is a standard java change listener and is only used to report changes that have
     * already happened. ChangeEvents are only fired AFTER a transition's doAfterTransition is called.
     */
    public void addChangeListener(ChangeListener changeListener) {
        if (!changeListeners.contains(changeListener)) {
            changeListeners.add(changeListener);
        }
    }

    /**
     * Fire a change event to registered listeners.
     */
    private void fireChangeEvent() {
        ChangeEvent changeEvent = new ChangeEvent(this);
        for (ChangeListener listener : changeListeners) {
            listener.stateChanged(changeEvent);
        }
    }
}
