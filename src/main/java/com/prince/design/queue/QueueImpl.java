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

    private int capacity;

    public QueueImpl(int maxCapacity) {
        this.capacity = maxCapacity;

        messages = new Object[maxCapacity];
    }

    @Override
    public synchronized void sendMessage(T payload) throws InterruptedException {
        // while loop is required because of 2 following reasons:
        // 1. Since notify() and notifyAll() randomly wakes up threads that are waiting on this
        // object’s monitor, it’s not always important that the condition is met. Sometimes it can
        // happen that the thread is woken up, but the condition isn’t actually satisfied yet.
        // 2. To handle spurious wakeup calls
        while (isFull()) {
            // wait() releases the lock as soon as it is called

            // The thread releases ownership of this monitor and waits until another thread notifies
            // threads waiting on this object's monitor to wake up either through a call to the
            // notify method or the notifyAll method. The thread then waits until it can re-obtain
            // ownership of the monitor and resumes execution.
            wait();
        }

        rear = (rear + 1) % capacity;
        messages[rear] = payload;

        if (front == -1) {
            front = rear;
        }

        // notify/notifyAll don't release locks like wait does. The awakened thread can't run until
        // the code which called notify releases its lock.
        notifyAll();
    }

    @Override
    public synchronized T receiveMessage() throws InterruptedException {
        // while loop is used to handle spurious wakeup calls
        while (isEmpty()) {
            wait();
        }

        T payload = (T) messages[front];

        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % capacity;
        }

        notifyAll();

        return payload;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return rear + 1 % capacity == front;
    }

    @Override
    public void print() {
        if (front != -1) {
            if (front <= rear) {
                for (int i = front; i <= rear; i++) {
                    System.out.print(messages[i]);
                }
            } else {
                for (int i = front; i <= capacity - 1; i++) {
                    System.out.print(messages[i]);
                }

                for (int i = 0; i <= rear; i++) {
                    System.out.print(messages[i]);
                }
            }
        }
    }
}
