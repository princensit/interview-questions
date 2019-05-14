package com.prince.multithreading;

import java.util.LinkedList;
import java.util.List;

/**
 * {@link java.util.concurrent.ArrayBlockingQueue} is a classic "bounded buffer", in which a
 * fixed-sized array holds elements inserted by producers and extracted by consumers. This class
 * supports an optional fairness policy for ordering waiting producer and consumer threads
 *
 * {@link java.util.concurrent.LinkedBlockingQueue} typically have higher throughput than
 * array-based queues but less predictable performance in most concurrent applications.
 * LinkedBlockingQueue will take a lock before any modification. So your offer calls would block
 * until they get the lock.
 *
 * {@link java.util.concurrent.ConcurrentLinkedQueue} means no locks are taken (i.e. no
 * synchronized(this) or Lock.lock calls). It will use a CAS - Compare and Swap operation during
 * modifications to see if the head/tail node is still the same as when it started. If so, the
 * operation succeeds. If the head/tail node is different, it will spin around and try again.
 *
 * The most important difference between LinkedBlockingQueue and ConcurrentLinkedQueue is that if
 * you request an element from a LinkedBlockingQueue and the queue is empty, your thread will wait
 * until there is something there. A ConcurrentLinkedQueue will return right away with the behavior
 * of an empty queue.
 *
 * Which one depends on if you need the blocking. Where you have many producers and one consumer, it
 * sounds like it. On the other hand, where you have many consumers and only one producer, you may
 * not need the blocking behavior, and may be happy to just have the consumers check if the queue is
 * empty and move on if it is.
 *
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

        queue.add(obj);

        if (queue.size() == 1) {
            // wake up any blocked dequeue
            notifyAll();
        }
    }

    public synchronized Object dequeue() throws InterruptedException {
        int size = queue.size();
        while (size == 0) {
            wait();
        }

        Object item = queue.remove(0);

        if (queue.size() == capacity - 1) {
            // wake up any blocked enqueue
            notifyAll();
        }

        return item;
    }
}
