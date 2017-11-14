package com.prince.multithreading.semaphores;

/**
 * @author Prince Raj
 */
public class CountingSemaphore {

    private int signals;

    public synchronized void acquire() {
        signals++;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (signals == 0) {
            wait();
        }

        signals--;
    }
}
