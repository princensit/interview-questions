package com.prince.oodesign.patterns.behavioral.state;

/**
 * @author Prince Raj
 */
public class PaymentPendingState implements State {

    private final Order order;

    public PaymentPendingState(Order order) {
        this.order = order;
    }

    @Override
    public void verifyPayment() {
        System.out.println("Payment is done successfully");
        order.setCurrentState(order.getOrderBeingPreparedState());
    }

    @Override
    public void cancelOrder() {
        System.out.println("Cancelling your unpaid order");
        order.setCurrentState(order.getCancelledOrderState());
    }

    @Override
    public void shipOrder() {
        throw new RuntimeException("Cannot ship the order since payment is not done");
    }
}
