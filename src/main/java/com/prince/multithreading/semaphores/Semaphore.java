package com.prince.multithreading.semaphores;

/**
 * Two uses:
 *
 * 1. To guard a critical section against entry by more than N threads at a time.
 *
 * 2. To send signals between two threads.
 *
 * @author Prince Raj
 */
public class Semaphore {

    private boolean signal;

    public synchronized void acquire() {
        signal = true;
        notify();
    }

    public synchronized void release() throws InterruptedException {
        while (!signal) {
            wait();
        }
        signal = false;
    }

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore();

        Thread sender = new Thread(new SendingThread(semaphore));
        Thread receiver = new Thread(new ReceivingThread(semaphore));

        sender.start();
        receiver.start();
    }

    private static class SendingThread implements Runnable {

        private Semaphore semaphore;

        private SendingThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while (true) {
                // do something, then signal
                semaphore.acquire();
            }
        }
    }

    private static class ReceivingThread implements Runnable {

        private Semaphore semaphore;

        private ReceivingThread(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // receive signal, then do something...
            }
        }
    }
}
