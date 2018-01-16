package com.prince.design.fsm2.dynamic.state;

/**
 * @author Prince Raj
 */
public class CompletedState implements  State{

    @Override
    public void preAction() {
        System.out.println("Completed state - pre action");
    }

    @Override
    public void postAction() {
        System.out.println("Completed state - post action");
    }
}
