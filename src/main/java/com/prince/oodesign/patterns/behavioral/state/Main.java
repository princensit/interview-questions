package com.prince.oodesign.patterns.behavioral.state;

/**
 * Design pattern that implements a state machine in an object-oriented way.
 *
 * With the state pattern, a state machine is implemented by implementing each individual state as a derived class of
 * the state pattern interface, and implementing state transitions by invoking methods defined by the pattern's
 * superclass.
 *
 * Allow an object to alter its behavior when its internal state changes. The object will appear to change its class.
 *
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("######### case 1: cancelling order");
        cancellingOrder();

        System.out.println("\n######### case 2: shipping order");
        shippingOrder();
    }

    private static void shippingOrder() {
        Order order = new Order();
        printState(order);

        State currentState = order.getCurrentState();
        currentState.verifyPayment();
        printState(order);

        currentState = order.getCurrentState();
        currentState.shipOrder();
        printState(order);
    }

    private static void cancellingOrder() {
        Order order = new Order();
        printState(order);

        State currentState = order.getCurrentState();
        currentState.verifyPayment();
        printState(order);

        currentState = order.getCurrentState();
        currentState.cancelOrder();
        printState(order);

        currentState = order.getCurrentState();
        currentState.shipOrder();
        printState(order);
    }

    private static void printState(Order order) {
        State currentState = order.getCurrentState();
        System.out.println("State: " + currentState.getClass().getSimpleName());
    }
}
