package com.prince.multithreading;

/**
 * @author Prince Raj
 */
public class EvenOddV2 {

    public static void main(String[] args) throws InterruptedException {
        int n = 1;
        int max = 10;
        MyThread printer = new MyThread(n, max);

        Thread oddThread = new Thread(printer, "Odd Thread");
        Thread evenThread = new Thread(printer, "Even Thread");

        oddThread.start();

        // To ensure that odd thread start first
        Thread.sleep(1000);

        evenThread.start();
    }

    private static final class MyThread implements Runnable {

        private int n;

        private final int max;

        MyThread(int n, int max) {
            this.n = n;
            this.max = max;
        }

        @Override
        public void run() {
            synchronized (this) {
                while (n <= max) {
                    if (n % 2 != 0) {
                        System.out.println(Thread.currentThread().getName() + " - " + n++);

                        this.notify();

                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + " - " + n++);

                        this.notify();

                        try {
                            this.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
