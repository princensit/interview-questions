package com.prince.multithreading;

/**
 * @author Prince Raj
 */
public class EvenOdd {

    private int x;

    private EvenOdd(int x) {
        this.x = x;
    }

    public static void main(String[] args) throws InterruptedException {
        EvenOdd eo = new EvenOdd(1);

        EvenThread evenThread = new EvenThread(eo, "EvenThread");
        OddThread oddThread = new OddThread(eo, "OddThread");

        evenThread.start();
        oddThread.start();

        Thread.sleep(15);

        evenThread.doStop();
        oddThread.doStop();
    }

    private synchronized void printOdd(String name) {
        if (x % 2 == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " - " + x);
        x++;
        notify();
    }

    private synchronized void printEven(String name) {
        if (x % 2 != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println(name + " - " + x);
        x++;
        notify();
    }

    private static final class EvenThread extends Thread {

        private final String name;

        private final EvenOdd evenOdd;

        private boolean isStopped;

        private EvenThread(EvenOdd evenOdd, String name) {
            this.evenOdd = evenOdd;
            this.name = name;
        }

        private synchronized void doStop() {
            isStopped = true;
        }

        @Override
        public void run() {
            while (!isStopped) {
                evenOdd.printEven(name);
            }
        }
    }

    private static final class OddThread extends Thread {

        private final String name;

        private final EvenOdd evenOdd;

        private boolean isStopped;

        private OddThread(EvenOdd evenOdd, String name) {
            this.evenOdd = evenOdd;
            this.name = name;
        }

        private synchronized void doStop() {
            isStopped = true;
        }

        @Override
        public void run() {
            while (!isStopped) {
                evenOdd.printOdd(name);
            }
        }
    }
}
