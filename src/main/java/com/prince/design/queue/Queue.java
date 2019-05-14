package com.prince.design.queue;

/**
 * @author Prince Raj
 */
public interface Queue<T> {

    void sendMessage(T payload) throws InterruptedException;

    T receiveMessage() throws InterruptedException;

    void print();
}
