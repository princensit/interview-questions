package com.prince.multithreading.semaphores;

/**
 * @author Prince Raj
 */
public class BoundedSemaphore {

    private final int bound;

    private int signals;

    public BoundedSemaphore(int bound) {
        this.bound = bound;
    }

    public synchronized void acquire() throws InterruptedException {
        while (signals == bound) {
            wait();
        }
        signals++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (signals == 0) {
            wait();
        }

        signals--;
        notify();
    }
}
