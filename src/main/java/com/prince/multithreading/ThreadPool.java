package com.prince.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * http://venkateshcm.com/2014/05/How-To-Determine-Web-Applications-Thread-Poll-Size/
 *
 * <pre>
 * 1. CPU Bound Applications:
 *    a) Thread Pool size should be equal number of cpu(s) on the box. Adding more threads would
 *       interrupt request processing due to thread context switching and also increases response time.
 *    b) Non-blocking IO applications will be CPU bound as there are no thread wait time while requests get processed.
 * 2. IO Bound Applications:
 *    a) Determining thread pool size of IO bound application is lot more complicated and depends on response time of
 *       down stream systems, since a thread is blocked until other system responds. We would have to increase the number
 *       of threads to better utilise CPU.
 *    b) Threads = (WebRequests/sec) X ResponseTime. While the above equation provides the number of threads required to
 *       handle incoming requests, it does not provide information on the threads to cpu ratio i.e. how many threads
 *       should be allocated on a given box with x CPUs.
 *    c) Starting with minimum a thread per cpu (Threads Pool Size = No of CPUs). Application thread pool size is directly
 *       proportional to the average response time of down stream systems until CPU usage is maxed out or response time
 *       is not degraded.
 *
 * Optimal Thread pool size - CPU gets efficiently used with good throughput and fewer thread context switching. We notice
 * good response time due to efficient request processing with fewer interrupts (context switching).
 *
 * Thread Pool isolation - In most web applications, few types of web request take much longer to process than other web
 * request types.The slower web requests might hog all threads and bring down entire application.
 *  Couple of ways to handle this issue is
 *  1. to have separate box to handle slow web requests.
 *  2. to allocate a separate thread pool for slow web requests within the same application.
 *
 * </pre>
 *
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
