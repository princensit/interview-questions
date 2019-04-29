package com.prince.algo;

/**
 * <pre>
 * Version A (efficient push):
 *   push: enqueue in queue1
 *   pop:
 *      1. while size of queue1 is bigger than 1, pipe dequeued items from queue1 into queue2
 *      2. dequeue and return the last item of queue1, then switch the names of queue1 and queue2
 *
 * Version B (efficient pop):
 *   push:
 *      1. enqueue in queue2
 *      2. enqueue all items of queue1 in queue2, then switch the names of queue1 and queue2
 *   pop: dequeue from queue1
 * </pre>
 *
 * @author Prince Raj
 */
public class StackWithTwoQueue {
}
