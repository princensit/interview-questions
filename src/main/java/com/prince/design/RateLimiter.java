package com.prince.design;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Limit 3rd party api calls to x calls per second
 *
 * @author Prince Raj
 */
public class RateLimiter {

    private BlockingQueue<Object> blockingQueue;

    private long timer;

    private int messages;

    private long seconds;

    public RateLimiter(int messages, long seconds) {
        blockingQueue = new ArrayBlockingQueue<>(messages);
        this.messages = messages;
        this.seconds = seconds;
        timer = System.currentTimeMillis();
    }

    public synchronized void tick() {
        long elapsedTime = System.currentTimeMillis() - timer;
        if (elapsedTime >= seconds * 1000) {

            int numToRemove = blockingQueue.size();
            List<Object> discardedObjects = new ArrayList<>(numToRemove);
            blockingQueue.drainTo(discardedObjects, numToRemove);

            timer = System.currentTimeMillis();
            System.out.println("Current time: " + new Date());
        }
    }

    public boolean limit() {
        try {
            blockingQueue.put(new Object());
        } catch (InterruptedException e) {
            return false;
        }

        return true;
    }

    public static void main(String[] args) {

        int messages = 10;
        int seconds = 8;

        final RateLimiter limiter = new RateLimiter(messages, seconds);

        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                limiter.tick();
            }
        }, 0, seconds, TimeUnit.SECONDS);

        for (int i = 0; i < 100; i++) {
            boolean status = limiter.limit();
            System.out.println("### " + i + " " + status);
        }
    }
}
