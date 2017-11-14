package com.prince.multithreading;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author Prince Raj
 */
public class ScheduledExecutorServiceUsage {

    public static void main(String[] args) throws Exception {
        ScheduledExecutorService se = Executors.newScheduledThreadPool(10);

        // 1. schedule (Runnable task, long delay, TimeUnit timeunit) - Returns null if task is completed
        ScheduledFuture scheduledFuture = se.schedule(new Runnable() {

            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println("result = " + scheduledFuture.get());

        // 2. schedule (Callable task, long delay, TimeUnit timeunit)
        scheduledFuture = se.schedule(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("Executed!");
                return "Called!";
            }
        }, 5, TimeUnit.SECONDS);

        System.out.println("result = " + scheduledFuture.get());

        // 3. scheduleAtFixedRate (Runnable, long initialDelay, long period, TimeUnit timeunit)
        // If a task takes longer to execute than the period between its scheduled executions, the next execution will
        // start *after the current execution finishes*. The scheduled task will not be executed by more than one thread
        // at a time.
        // In the scheduleAtFixedRate() method, the period is interpreted as a delay between the start of the previous
        // execution, until the start of the next execution.
        se.scheduleAtFixedRate(new Runnable() {

            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        }, 2, 10, TimeUnit.SECONDS);

        // 4. scheduleWithFixedDelay (Runnable, long initialDelay, long period, TimeUnit timeunit)
        // In this method, however, the period is interpreted as the delay between the end of the previous execution,
        // until the start of the next. The delay is thus between finished executions, not between the beginning of
        // executions.
        se.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        }, 2, 10, TimeUnit.SECONDS);

        se.shutdown();
    }
}
