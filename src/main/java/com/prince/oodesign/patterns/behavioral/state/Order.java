package com.prince.oodesign.patterns.behavioral.state;

/**
 * @author Prince Raj
 */
public class Order {

    private final State orderBeingPreparedState;

    private final State cancelledOrderState;

    private final State orderShippedState;

    private final State paymentPendingState;

    private State currentState;

    public Order() {
        this.orderBeingPreparedState = new OrderBeingPreparedState(this);
        this.cancelledOrderState = new CancelledOrderState(this);
        this.orderShippedState = new OrderShippedState(this);
        this.paymentPendingState = new PaymentPendingState(this);

        this.setCurrentState(paymentPendingState);
    }

    public State getCurrentState() {
        return currentState;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getOrderBeingPreparedState() {
        return orderBeingPreparedState;
    }

    public State getCancelledOrderState() {
        return cancelledOrderState;
    }

    public State getOrderShippedState() {
        return orderShippedState;
    }

    public State getPaymentPendingState() {
        return paymentPendingState;
    }
}
