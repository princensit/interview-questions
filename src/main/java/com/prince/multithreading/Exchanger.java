package com.prince.multithreading;

/**
 * Exchanger class represents a kind of rendezvous point where two threads can exchange objects.
 *
 * @author Prince Raj
 */
public class Exchanger {

    private Object object;

    public static void main(String[] args) {
        Exchanger exchanger = new Exchanger();

        Thread exchangerThread1 = new Thread(new ExchangerRunnable(exchanger, "A"));
        Thread exchangerThread2 = new Thread(new ExchangerRunnable(exchanger, "B"));

        exchangerThread1.start();
        exchangerThread2.start();
    }

    private synchronized Object exchange(Object object) throws InterruptedException {
        Object previous = this.object;
        this.object = object;

        if (previous == null) {
            wait();

            previous = this.object;
        } else {
            notify();
        }

        return previous;
    }

    private static final class ExchangerRunnable implements Runnable {

        private final Exchanger exchanger;

        private Object object;

        private ExchangerRunnable(Exchanger exchanger, Object object) {
            this.exchanger = exchanger;
            this.object = object;
        }

        @Override
        public void run() {
            try {
                Object previous = object;
                object = exchanger.exchange(previous);

                System.out.println(Thread.currentThread().getName() + " exchanged " + previous + " for " + object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
