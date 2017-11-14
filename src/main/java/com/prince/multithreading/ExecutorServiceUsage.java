package com.prince.multithreading;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Prince Raj
 */
public class ExecutorServiceUsage {

    public static void main(String[] args) throws Exception {
        ExecutorService es1 = Executors.newSingleThreadExecutor();
        ExecutorService es2 = Executors.newFixedThreadPool(10);

        // 1. execute(Runnable)
        es1.execute(new Runnable() {

            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        // 2. submit(Runnable) - Returns Future object to check if Runnable has finished executing
        Future future = es1.submit(new Runnable() {

            @Override
            public void run() {
                System.out.println("Asynchronous task");
            }
        });

        future.get(); // returns null if the task has finished correctly.

        // 3. submit(Callable)

        future = es1.submit(new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("Asynchronous Callable");
                return "Callable Result";
            }
        });

        System.out.println("future.get() = " + future.get()); // Callable Result

        // 4. invokeAny(callables)
        Set<Callable<String>> callableSet = new HashSet<>();
        callableSet.add(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callableSet.add(new Callable<String>() {

            @Override
            public String call() throws Exception {
                return "Task 2";
            }
        });

        String result = es2.invokeAny(callableSet);
        System.out.println("result = " + result);

        // 5. invokeAll(callables)
        List<Future<String>> futures = es2.invokeAll(callableSet);
        for (Future<String> f : futures) {
            System.out.println("future.get = " + f.get());
        }

        // When you are done using the ExecutorService you should shut it down, so the threads do not keep running.
        // The active threads inside this ExecutorService prevents the JVM from shutting down.
        es1.shutdown();
        es2.shutdown();
    }
}
