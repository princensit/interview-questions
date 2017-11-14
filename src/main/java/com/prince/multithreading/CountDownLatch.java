package com.prince.multithreading;

/**
 *
 * @author Prince Raj
 */
public class CountDownLatch {

    private int count;

    private CountDownLatch(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(3);

        Thread waiter = new Thread(new Waiter(latch));
        Thread decrementer = new Thread(new Decrementer(latch));

        System.out.println("Waiter will be released after 3 count down");
        waiter.start();
        decrementer.start();
    }

    private synchronized void await() throws InterruptedException {
        if (count > 0) {
            wait();
        }
    }

    private synchronized void countDown() {
        count--;

        if (count == 0) {
            notifyAll();
        }
    }

    private static final class Waiter implements Runnable {

        private final CountDownLatch latch;

        private Waiter(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("Waiter Released");
        }
    }

    private static final class Decrementer implements Runnable {

        private final CountDownLatch latch;

        private Decrementer(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
                latch.countDown();
                System.out.println("Latch count reduced to " + latch.count);

                Thread.sleep(1000);
                latch.countDown();
                System.out.println("Latch count reduced to " + latch.count);

                Thread.sleep(1000);
                latch.countDown();
                System.out.println("Latch count reduced to " + latch.count);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
