package com.prince.multithreading.locks;

import java.util.Random;

/**
 * @author Prince Raj
 */
public class ReentrantReadWriteLock {

    private static final Random RANDOM = new Random();

    private static String message = "";

    private int readLockCount;

    private int writeLockCount;

    private ReadLock readLock;

    private WriteLock writeLock;

    public ReentrantReadWriteLock() {
        this.readLock = new ReadLock();
        this.writeLock = new WriteLock();
    }

    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        Thread reader1 = new Thread(new Reader(readWriteLock), "Reader-1");
        Thread reader2 = new Thread(new Reader(readWriteLock), "Reader-2");
        Thread writer1 = new Thread(new WriterA(readWriteLock), "Writer-1");
        Thread writer2 = new Thread(new WriterB(readWriteLock), "Writer-2");

        writer1.start();
        reader1.start();
        reader2.start();
        writer2.start();
    }

    private static class Reader implements Runnable {

        private final ReentrantReadWriteLock readWriteLock;

        private Reader(ReentrantReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    readWriteLock.readLock.lock();
                    System.out.println(Thread.currentThread().getName() + " " + message);
                    try {
                        Thread.sleep(RANDOM.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    readWriteLock.readLock.unlock();
                }
            }
        }
    }

    private static class WriterA implements Runnable {

        private final ReentrantReadWriteLock readWriteLock;

        private WriterA(ReentrantReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    readWriteLock.writeLock.lock();
                    message = message.concat("a");
                    System.out.println(Thread.currentThread().getName() + " " + message);
                    try {
                        Thread.sleep(RANDOM.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    readWriteLock.writeLock.unlock();
                }
            }
        }
    }

    private static class WriterB implements Runnable {

        private final ReentrantReadWriteLock readWriteLock;

        private WriterB(ReentrantReadWriteLock readWriteLock) {
            this.readWriteLock = readWriteLock;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    readWriteLock.writeLock.lock();
                    message = message.concat("b");
                    System.out.println(Thread.currentThread().getName() + " " + message);
                    try {
                        Thread.sleep(RANDOM.nextInt(100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } finally {
                    readWriteLock.writeLock.unlock();
                }
            }
        }
    }

    private final class ReadLock {

        public synchronized void lock() {
            if (writeLockCount == 0) {
                readLockCount++;
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void unlock() {
            readLockCount--;
            if (readLockCount == 0) {
                notifyAll();
            }
        }
    }

    private final class WriteLock {

        public synchronized void lock() {
            if (readLockCount == 0 && writeLockCount == 0) {
                writeLockCount++;
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public synchronized void unlock() {
            writeLockCount--;
            notifyAll();
        }
    }
}
