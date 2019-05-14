package com.prince.design.queue;

/**
 * Nice explanation: https://stackoverflow.com/a/3186336
 *
 * Consider array as circular buffer.
 *
 * wait( ) tells the calling thread to give up the monitor and go to sleep until some other thread
 * enters the same monitor and calls notify( ).
 *
 * notify( ) wakes up a thread that called wait( ) on the same object.
 *
 * notifyAll( ) wakes up all the threads that called wait( ) on the same object. The highest
 * priority thread will run first.
 *
 * @author Prince Raj
 */
@SuppressWarnings("unchecked")
public class QueueImpl<T> implements Queue<T> {

    private int rear = -1;

    private int front = -1;

    private Object[] messages;

    private int size;

    public QueueImpl(int maxCapacity) {
        this.size = maxCapacity;

        messages = new Object[maxCapacity];
    }

    @Override
    public synchronized void sendMessage(T payload) throws InterruptedException {
        // condition for queue is full

        // while loop is required because of 2 following reasons:
        // 1. Since notify() and notifyAll() randomly wakes up threads that are waiting on this
        // object’s monitor, it’s not always important that the condition is met. Sometimes it can
        // happen that the thread is woken up, but the condition isn’t actually satisfied yet.
        // 2. To handle spurious wakeup calls
        while (front - 1 == rear || (front == 0 && rear == size - 1)) {

            // wait releases the lock as soon as it is called

            // The thread releases ownership of this monitor and waits until another thread notifies
            // threads waiting on this object's monitor to wake up either through a call to the
            // notify method or the notifyAll method. The thread then waits until it can re-obtain
            // ownership of the monitor and resumes execution.
            wait();
        }

        if (front == -1) {
            front = 0;
        }

        rear = (rear + 1) % size;
        messages[rear] = payload;

        // TODO what would happen if front and rear = 0
        // notify/notifyAll don't release locks like wait does. The awakened thread can't run until
        // the code which called notify releases its lock.
        notifyAll();
    }

    @Override
    public synchronized T receiveMessage() throws InterruptedException {
        // condition for queue is empty
        // while loop is used to handle spurious wakeup calls
        while ((front == -1 && rear == -1) || (front == rear)) {
            wait();
        }

        T payload = (T) messages[front];
        front = (front + 1) % size;

        notifyAll();

        return payload;
    }

    @Override
    public void print() {
        if (front != -1) {
            if (front <= rear) {
                for (int i = front; i <= rear; i++) {
                    System.out.print(messages[i]);
                }
            } else {
                for (int i = front; i <= size - 1; i++) {
                    System.out.print(messages[i]);
                }

                for (int i = 0; i <= rear; i++) {
                    System.out.print(messages[i]);
                }
            }
        }
    }
}
