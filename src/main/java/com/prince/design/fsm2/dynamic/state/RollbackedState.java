package com.prince.design.fsm2.dynamic.state;

/**
 * @author Prince Raj
 */
public class RollbackedState implements State {

    @Override
    public void preAction() {
        System.out.println("Rollbacked state - pre action");
    }

    @Override
    public void postAction() {
        System.out.println("Rollbacked state - post action");
    }
}
