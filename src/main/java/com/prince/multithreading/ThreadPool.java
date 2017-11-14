package com.prince.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author Prince Raj
 */
public class ThreadPool {

    private final BlockingQueue<Runnable> tasks;

    private final List<MyThread> threads;

    private boolean isStopped = false;

    private ThreadPool(int noOfThreads, int maxTasks) {
        this.tasks = new ArrayBlockingQueue<>(maxTasks);

        threads = new ArrayList<>(noOfThreads);
        for (int i = 0; i < noOfThreads; i++) {
            threads.add(new MyThread(tasks));
        }

        for (MyThread thread : threads) {
            thread.start();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threads = 3;
        List<Runnable> tasks = getTasks();

        ThreadPool pool = new ThreadPool(threads, tasks.size());

        for (Runnable task : tasks) {
            pool.submit(task);
        }

        Thread.sleep(5000);

        pool.stop();

        pool.submit(new Task("test"));
    }

    private synchronized void submit(Runnable task) {
        if (isStopped) {
            throw new IllegalStateException("Thread pool is stopped");
        }
        tasks.add(task);
    }

    private synchronized void stop() {
        isStopped = true;

        for (MyThread thread : threads) {
            thread.doStop();
        }
    }

    private static List<Runnable> getTasks() {
        List<Runnable> tasks = new ArrayList<>();
        Task task1 = new Task("Hello");
        tasks.add(task1);
        Task task2 = new Task("World");
        tasks.add(task2);
        Task task3 = new Task("Time");
        tasks.add(task3);
        Task task4 = new Task("to");
        tasks.add(task4);
        Task task5 = new Task("Do");
        tasks.add(task5);
        Task task6 = new Task("it");
        tasks.add(task6);

        return tasks;
    }

    private static final class MyThread extends Thread {

        private boolean isStopped = false;

        private final BlockingQueue<Runnable> tasks;

        private MyThread(BlockingQueue<Runnable> tasks) {
            this.tasks = tasks;
        }

        private synchronized void doStop() {
            isStopped = true;
            this.interrupt();
        }

        private synchronized boolean isStopped() {
            return isStopped;
        }

        @Override
        public void run() {
            while (!isStopped()) {
                try {
                    Runnable task = tasks.take();
                    task.run();

                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static final class Task implements Runnable {

        private final String name;

        private Task(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " - " + name);
        }
    }
}
