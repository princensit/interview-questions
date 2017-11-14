package com.prince.multithreading.locks;

/**
 * @author Prince Raj
 */
public class ReentrantLock {

    private boolean isLocked = false;

    private Thread lockingThread;

    private int lockedCount = 0;

    public synchronized void lock() {
        Thread currentThread = Thread.currentThread();
        // re-entrant means if thread is holding a lock, can retake it
        while (isLocked && lockingThread != currentThread) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        isLocked = true;
        lockedCount++;
        lockingThread = currentThread;
    }

    public synchronized void unlock() {
        if (lockingThread != Thread.currentThread()) {
            lockedCount--;

            if (lockedCount == 0) {
                isLocked = false;
                notify();
            }
        }
    }
}
