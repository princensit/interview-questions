package com.prince.design.queue;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author Prince Raj
 */
@SuppressWarnings("unchecked")
public class Main {

    private static final Random RANDOM = new Random();
    private static final int SEED = 10;

    public static void main(String[] args) throws Exception {
        Queue queue = new QueueImpl(5);

        final ExecutorService producers = Executors.newFixedThreadPool(5);
        final ExecutorService consumers = Executors.newFixedThreadPool(5);

        for (int i = 0; i < 5; i++) {
            producers.execute(new Producer(queue));
            consumers.execute(new Consumer(queue));
        }

        Thread.sleep(3000);

        producers.shutdownNow();
        consumers.shutdownNow();
    }

    private static final class Producer implements Runnable {

        private final Queue<String> queue;

        private boolean isInterrupted;

        public Producer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!isInterrupted) {
                try {
                    String word = RandomStringUtils.randomAlphanumeric(6);
                    System.out.println("Producing message: " + word);
                    queue.sendMessage(word);
                } catch (InterruptedException e) {
                    isInterrupted = true;
                    System.out.println("Threads are interrupted");
                    return;
                }

                long randomMilliSeconds = (RANDOM.nextInt(SEED) + 1) * 10;
                try {
                    Thread.sleep(randomMilliSeconds);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        }
    }

    private static final class Consumer implements Runnable {

        private final Queue<String> queue;

        private boolean isInterrupted;

        public Consumer(Queue queue) {
            this.queue = queue;
        }

        @Override
        public void run() {
            while (!isInterrupted) {
                String message;
                try {
                    message = queue.receiveMessage();
                } catch (InterruptedException e) {
                    isInterrupted = true;
                    System.out.println("Threads are interrupted");
                    return;
                }

                System.out.println("Consuming message: " + message);

                long randomMilliSeconds = (RANDOM.nextInt(SEED) + 1) * 10;
                try {
                    Thread.sleep(randomMilliSeconds);
                } catch (InterruptedException e) {
                    // do nothing
                }
            }
        }
    }
}
