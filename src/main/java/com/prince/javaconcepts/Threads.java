package com.prince.javaconcepts;

/**
 * <pre>
 *  A process is an execution of a program, while a Thread is a single execution sequence within a process. A process can contain multiple threads. A Thread is sometimes called a lightweight process.
 *
 *  Three ways to create thread;
 *  1. A class may extend the Thread class.
 *  2. A class may implement the Runnable interface.
 *  3. An application can use the Executor framework, in order to create a thread pool.
 *
 *  Thread -
 *     Each thread creates a unique object and gets associated with it.
 *     As each thread create a unique object, more memory required.
 *     Multiple inheritance not allowed.
 *     A user must extend thread class only if it wants to override the other methods in Thread class.
 *  Runnable -
 *     Multiple threads share the same objects.
 *     As multiple threads share the same object less memory is used.
 *     If a class define thread implementing the Runnable interface it has a chance of extending one class.
 *     If you only want to specialize run method then implementing Runnable is a better option.
 *
 *  Possible thread states (See image: thread_state_diagram_1.png)
 *  1. Runnable: A thread becomes ready to run, but does not necessarily start running immediately.
 *  2. Running: The processor is actively executing the thread code.
 *  3. Waiting: A thread is in a blocked state waiting for some external processing to finish.
 *  4. Sleeping: The thread is forced to sleep.
 *  5. Blocked on I/O: Waiting for an I/O operation to complete.
 *     Blocked on Synchronization: Waiting to acquire a lock.
 *  6. Dead: The thread has finished its execution.
 *
 *  The JVM uses locks in conjunction with monitors. A monitor is basically a guardian that watches over a sequence of
 *  synchronized code and ensuring that only one thread at a time executes a synchronized piece of code. Each monitor is
 *  associated with an object reference. The thread is not allowed to execute the code until it obtains the lock.
 *
 *  Deadlock is a condition that occurs when two processes are waiting for each other to complete, before proceeding.
 *  The result is that both processes wait endlessly.
 *
 *  To ensure that N threads can access N resources without deadlock is by imposing an ordering on the locks. Thus, if
 *  all threads lock and unlock the mutexes in the same order, no deadlocks can arise.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Threads {

}
