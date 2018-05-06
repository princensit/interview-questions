package com.prince.multithreading.producerconsumer;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Prince Raj
 */
public class ProducerConsumer {

    public static void main(String[] args) throws InterruptedException {
        int capacity = 2;
        BlockingQueue<Integer> sharedQueue = new LinkedBlockingQueue<>();

        Producer producer = new Producer(capacity, sharedQueue);
        Consumer consumer = new Consumer(capacity, sharedQueue);

        producer.start();
        consumer.start();

        Thread.sleep(15000);

        producer.doStop();
        consumer.doStop();
    }
}

class Producer extends Thread {

    private static final Random RANDOM = new Random();

    private final int capacity;

    private final BlockingQueue<Integer> sharedQueue;

    private boolean stopped;

    Producer(int capacity, BlockingQueue<Integer> sharedQueue) {
        this.capacity = capacity;
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (!stopped) {
            if (isFull()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                produce();
            }
        }
    }

    public void doStop() {
        stopped = true;
    }

    private void produce() {
        int value = RANDOM.nextInt(100);
        System.out.println("Producing " + value + " ...");
        sharedQueue.add(value);
    }

    private boolean isFull() {
        return sharedQueue.size() == capacity;
    }
}

class Consumer extends Thread {

    private final int capacity;

    private final BlockingQueue<Integer> sharedQueue;

    private boolean stopped;

    Consumer(int capacity, BlockingQueue<Integer> sharedQueue) {
        this.capacity = capacity;
        this.sharedQueue = sharedQueue;
    }

    @Override
    public void run() {
        while (!stopped) {
            if (isEmpty()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                consume();
            }
        }
    }

    public void doStop() {
        stopped = true;
    }

    private void consume() {
        try {
            int value = sharedQueue.take();
            System.out.println("Consuming " + value + " ...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean isEmpty() {
        return sharedQueue.size() == 0;
    }
}
