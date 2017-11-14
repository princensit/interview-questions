package com.prince.multithreading.locks;

import com.multithreading.semaphores.BoundedSemaphore;

/**
 * @author Prince Raj
 */
public class SemaphoreAsLock {

    public static void main(String[] args) throws InterruptedException {

        // If bound is more than 1, then multiple threads can enter critical section
        BoundedSemaphore semaphore = new BoundedSemaphore(1);

        semaphore.acquire();

        try {
            // critical section
        } finally {
            semaphore.release();
        }
    }
}
