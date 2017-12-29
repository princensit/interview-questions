package com.prince.oodesign.patterns.behavioral.state;

/**
 * @author Prince Raj
 */
public class OrderBeingPreparedState implements State {

    private final Order order;

    public OrderBeingPreparedState(Order order) {
        this.order = order;
    }

    @Override
    public void verifyPayment() {
        System.out.println("Payment already verified");
    }

    @Override
    public void cancelOrder() {
        System.out.println("Cancelling order");
        order.setCurrentState(order.getCancelledOrderState());
    }

    @Override
    public void shipOrder() {
        System.out.println("Shipping your order now");
        order.setCurrentState(order.getOrderShippedState());
    }
}
