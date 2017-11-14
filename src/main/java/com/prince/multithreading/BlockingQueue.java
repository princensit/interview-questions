package com.prince.multithreading;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Prince Raj
 */
public class BlockingQueue {

    private int capacity;

    private List<Object> queue;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new LinkedList<>();
    }

    public synchronized void enqueue(Object obj) throws InterruptedException {
        int size = queue.size();
        while (size == capacity) {
            wait();
        }

        if (size == 0) {
            notifyAll();
        }

        queue.add(obj);
    }

    public synchronized Object dequeue() throws InterruptedException {
        int size = queue.size();
        while (size == 0) {
            wait();
        }

        if (size == capacity) {
            notifyAll();
        }

        return queue.remove(0);
    }
}
