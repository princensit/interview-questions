package com.prince.design.fsm.dynamic.state;

/**
 * @author Prince Raj
 */
public interface State {

    // before entering state
    void preAction( );

    // before exiting state
    void postAction();
}
