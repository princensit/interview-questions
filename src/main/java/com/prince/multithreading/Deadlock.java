package com.prince.multithreading;

import java.util.Random;

/**
 * A deadlock is when two or more threads are blocked waiting to obtain locks that some of the other
 * threads in the deadlock are holding. Deadlock can occur when multiple threads need the same
 * locks, at the same time, but obtain them in different order.
 *
 * <pre>
 * Ex-
 * Thread 1  locks A, waits for B
 * Thread 2  locks B, waits for A
 *
 * Deadlock can arise if following four conditions hold simultaneously:
 * 1. Mutual Exclusion: One or more than one resource are non-sharable (Only one process can use at a time)
 * 2. Hold and Wait: A process is holding at least one resource and waiting for resources.
 * 3. No Preemption: A resource cannot be taken from a process unless the process releases the resource.
 * 4. Circular Wait: A set of processes are waiting for each other in circular form.
 *
 * Deadlock prevention:
 * 1. Lock Ordering
 * 2. Lock Timeout
 * 3. Deadlock Detection : Deadlock detection is a heavier deadlock prevention mechanism aimed at cases in which lock
 *    ordering isn't possible, and lock timeout isn't feasible. Every time a thread takes a lock it is noted in a data
 *    structure (map, graph etc.) of threads and locks. Additionally, whenever a thread requests a lock this is also
 *    noted in this data structure.
 *
 * What to do when deadlock is detected?
 * - One possible action is to release all locks, backup, wait a random amount of time and then retry.
 * - A better option is to determine or assign a priority of the threads so that only one (or a few) thread backs up.
 *   The rest of the threads continue taking the locks they need as if no deadlock had occurred. If the priority
 *   assigned to the threads is fixed, the same threads will always be given higher priority. To avoid this you may
 *   assign the priority randomly whenever a deadlock is detected.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Deadlock {

    private static final Random RANDOM = new Random(5);

    private static final Object objLock1 = new Object();

    private static final Object objLock2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable1());
        Thread t2 = new Thread(new Runnable2());

        t1.start();
        t2.start();
    }

    private static final class Runnable1 implements Runnable {

        @Override
        public void run() {
            synchronized (objLock1) {
                System.out.println(
                        "Acquired lock on object 1, now trying to acquire lock on object 2");

                try {
                    long randomMilliSeconds = (RANDOM.nextInt(5) + 1) * 200;
                    Thread.sleep(randomMilliSeconds);
                } catch (InterruptedException e) {
                    // do nothing
                }

                synchronized (objLock2) {
                    System.out.println("Now, acquired lock on object 2");
                }
            }
        }
    }

    private static final class Runnable2 implements Runnable {

        @Override
        public void run() {
            synchronized (objLock2) {
                System.out.println(
                        "Acquired lock on object 2, now trying to acquire lock on object 1");

                long randomMilliSeconds = (RANDOM.nextInt(5) + 1) * 100;
                try {
                    Thread.sleep(randomMilliSeconds);
                } catch (InterruptedException e) {
                    // do nothing
                }

                synchronized (objLock1) {
                    System.out.println("Now, acquired lock on object 1");
                }
            }
        }
    }
}
