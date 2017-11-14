package com.prince.multithreading;

/**
 * CyclicBarrier is a barrier that all threads must wait at, until all threads reach it, before any of the threads can
 * continue.
 *
 * 1. CyclicBarrier can be awaited repeatedly, but CountDownLatch can’t be awaited repeatedly i.e. once count has become
 * 0, CyclicBarrier can be used again but CountDownLatch cannot be used again.
 *
 * 2. CyclicBarrier can be used to trigger event
 *
 * @author Prince Raj
 */
public class CyclicBarrier {

    // total parties
    private int initialParties;

    // parties yet to arrive
    private int partiesAwait;

    private Runnable barrierAction;

    private CyclicBarrier(int parties, Runnable barrierAction) {
        this.initialParties = parties;
        this.partiesAwait = parties;
        this.barrierAction = barrierAction;
    }

    public static void main(String[] args) {
        Runnable barrierAction1 = new Runnable() {

            @Override
            public void run() {
                System.out.println("Barrier action 1 executed");
            }
        };

        Runnable barrierAction2 = new Runnable() {

            @Override
            public void run() {
                System.out.println("Barrier action 2 executed");
            }
        };

        CyclicBarrier barrier1 = new CyclicBarrier(2, barrierAction1);
        CyclicBarrier barrier2 = new CyclicBarrier(2, barrierAction2);

        Thread barrierThread1 = new Thread(new CyclicBarrierRunnable(barrier1, barrier2));
        Thread barrierThread2 = new Thread(new CyclicBarrierRunnable(barrier1, barrier2));

        barrierThread1.start();
        barrierThread2.start();
    }

    private synchronized void await() throws InterruptedException {
        partiesAwait--;

        if (partiesAwait > 0) {
            wait();
        } else {
            partiesAwait = initialParties;

            notifyAll();

            barrierAction.run();
        }
    }

    private static final class CyclicBarrierRunnable implements Runnable {

        private final CyclicBarrier barrier1;

        private final CyclicBarrier barrier2;

        private CyclicBarrierRunnable(CyclicBarrier barrier1, CyclicBarrier barrier2) {
            this.barrier1 = barrier1;
            this.barrier2 = barrier2;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " waiting at barrier 1");
                barrier1.await();

                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " waiting at barrier 2");
                barrier2.await();

                System.out.println(Thread.currentThread().getName() + " done!");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
