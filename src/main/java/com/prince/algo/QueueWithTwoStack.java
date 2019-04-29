package com.prince.algo;

import java.util.Stack;

/**
 * <pre>
 * Enqueue:
 *   Push the new element onto inbox
 *
 * Dequeue:
 *   1. If outbox is empty, refill it by popping each element from inbox and pushing it onto outbox
 *   2. Pop and return the top element from outbox
 * </pre>
 *
 * @author Prince Raj
 */
public class QueueWithTwoStack<E> {

    private Stack<E> inbox = new Stack<E>();
    private Stack<E> outbox = new Stack<E>();

    public void queue(E item) {
        inbox.push(item);
    }

    public E dequeue() {
        if (outbox.isEmpty()) {
            while (!inbox.isEmpty()) {
                outbox.push(inbox.pop());
            }
        }
        return outbox.pop();
    }
}
