package com.prince.multithreading;

/**
 * http://tutorials.jenkov.com/java-concurrency/anatomy-of-a-synchronizer.html
 *
 * <ul>
 * <li>1. State</li>
 * <li>2. Access Condition</li>
 * <li>3. State Changes</li>
 * <li>4. Notification Strategy</li>
 * <li>5. Test and Set Method</li>
 * <li>6. Set Method</li>
 * </ul>
 *
 * @author Prince Raj
 */
public class AnatomyOfASynchronizer {

    public class BoundedSemaphore {

        // 1. state is kept here
        private int signals = 0;

        // 1. state is kept here
        private int bound = 0;

        public BoundedSemaphore(int upperBound) {
            this.bound = upperBound;
        }

        // 5. Test and Set Method
        public synchronized void take() throws InterruptedException {
            // 2. access condition
            while (this.signals == bound) {
                // 4. wait strategy - related to notification strategy
                wait();
            }

            // 3. state change
            this.signals++;

            // 4. notification strategy
            this.notify();
        }

        // 5. Test and Set Method
        public synchronized void release() throws InterruptedException {
            // 2. access condition
            while (this.signals == 0) {
                // 3. wait strategy - related to notification strategy
                wait();
            }

            // 3. state change
            this.signals--;

            // 4. notification strategy
            this.notify();
        }
    }

    public class Lock {

        private boolean isLocked = false;

        // 6. Set Method (The set method just sets the internal state of the synchronizer without testing it first)
        public synchronized void unlock() {
            isLocked = false;
            notify();
        }

    }
}
