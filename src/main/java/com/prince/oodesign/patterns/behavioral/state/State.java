package com.prince.oodesign.patterns.behavioral.state;

/**
 * @author Prince Raj
 */
public interface State {

    void verifyPayment();

    void cancelOrder();

    void shipOrder();
}
