package com.prince.multithreading.locks;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Advantages of Re-entrant locks over synchronized
 * <ul>
 * <li>Supports lock polling using tryLock()</li>
 * <li>Supports interruptible timed locks using lockInterruptibly() and tryLock(long, TimeUnit)</li>
 * <li>Supports fairness policy using Reentrant(fair)</li>
 * <li>Supports non block-structured locks like foo() { lock.lock() }; bar() { lock.unlock() }</li>
 * <li>Supports multiple condition variables</li>
 * </ul>
 *
 * @author Prince Raj
 */
public class LockUsage {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();

        lock.lock();
        lock.lockInterruptibly();
        lock.tryLock();
        lock.tryLock(10, TimeUnit.SECONDS);
        lock.unlock();
    }
}
