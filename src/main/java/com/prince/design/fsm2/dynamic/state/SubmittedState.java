package com.prince.design.fsm2.dynamic.state;

/**
 * @author Prince Raj
 */
public class SubmittedState implements State {

    @Override
    public void preAction() {
        System.out.println("Submitted state - pre action");
    }

    @Override
    public void postAction() {
        System.out.println("Submitted state - post action");
    }
}
