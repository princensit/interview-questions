package com.prince.oodesign.patterns.behavioral.state;

/**
 * @author Prince Raj
 */
public class CancelledOrderState implements State {

    private final Order order;

    public CancelledOrderState(Order order) {
        this.order = order;
    }

    @Override
    public void verifyPayment() {
        System.out.println("Order cancelled, you cannot verify payment");
    }

    @Override
    public void cancelOrder() {
        System.out.println("Order is already cancelled");
    }

    @Override
    public void shipOrder() {
        System.out.println("Order cannot ship, since it was cancelled");
    }
}
