package com.prince.design.fsm2.dynamic.state;

/**
 * @author Prince Raj
 */
public class InitiatedState implements State {

    @Override
    public void preAction() {
        System.out.println("Initiated state - pre action");
    }

    @Override
    public void postAction() {
        System.out.println("Initiated state - post action");
    }
}
