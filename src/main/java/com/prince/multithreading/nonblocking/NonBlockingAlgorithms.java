package com.prince.multithreading.nonblocking;

/**
 * @author Prince Raj
 */
public class NonBlockingAlgorithms {

    public static void main(String[] args) {
        System.out.println("The Benefit of Non-blocking Algorithms - \n");
        System.out.println("1. Choice - threads are given a choice about what to do when their requested action cannot"
                + " be performed. Instead of just being blocked, the request thread has a choice about what to do.");
        System.out.println("2. No Deadlocks");
        System.out.println("3. No Thread Suspension - Suspending and reactivating a thread is costly.");
        System.out.println("4. Reduced Thread Latency");
    }
}
