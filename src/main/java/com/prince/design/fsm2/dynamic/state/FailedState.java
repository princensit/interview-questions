package com.prince.design.fsm2.dynamic.state;

/**
 * @author Prince Raj
 */
public class FailedState implements State {

    @Override
    public void preAction() {
        System.out.println("Failed state - pre action");
    }

    @Override
    public void postAction() {
        System.out.println("Failed state - post action");
    }
}
