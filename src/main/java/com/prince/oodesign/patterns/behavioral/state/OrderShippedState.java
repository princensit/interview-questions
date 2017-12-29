package com.prince.oodesign.patterns.behavioral.state;

/**
 * @author Prince Raj
 */
public class OrderShippedState implements State {

    private final Order order;

    public OrderShippedState(Order order) {
        this.order = order;
    }

    @Override
    public void verifyPayment() {
        System.out.println("Payment can not be verified, as order is already shipped");
    }

    @Override
    public void cancelOrder() {
        System.out.println("Shipped order can not be cancelled");
    }

    @Override
    public void shipOrder() {
        System.out.println("Order is already shipped");
    }
}
