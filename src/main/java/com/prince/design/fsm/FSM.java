package com.prince.design.fsm;

import javax.swing.event.ChangeListener;

/**
 * @author Prince Raj
 */
public interface FSM {

    void setDebug(boolean debug);

    void addState(String state);

    void addState(String state, Runnable entryCode, Runnable alwaysRunCode, Runnable exitCode);

    void setAutoTransition(String startState, String endState);

    void addTransition(Transition transition);

    void addEvent(String eventName);

    void addChangeListener(ChangeListener changeListener);
}
