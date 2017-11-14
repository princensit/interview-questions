package com.prince.design.fsm.dynamic.state;

/**
 * @author Prince Raj
 */
public class RollbackScheduledState implements State {

    @Override
    public void preAction() {
        System.out.println("Rollback scheduled state - pre action");
    }

    @Override
    public void postAction() {
        System.out.println("Rollback scheduled state - post action");
    }
}
