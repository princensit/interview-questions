package com.prince.multithreading.nonblocking;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This version is just as thread-safe
 *
 * @author Prince Raj
 */
public class OptimisticLockingWithCompareAndSwap {

    private AtomicLong count = new AtomicLong(0);

    // The inc() method no longer contains a synchronized block.
    public void inc() {
        boolean updated = false;
        while (!updated) {
            long val = count.get();
            // The compareAndSet() is an atomic method operation, typically supported by compare-and-swap instructions
            // directly in the CPU. Therefore no synchronization is necessary, and no thread suspension is necessary.
            // This saves the thread suspension overhead.
            updated = count.compareAndSet(val, val + 1);
        }
    }

    public long count() {
        return count.get();
    }
}
