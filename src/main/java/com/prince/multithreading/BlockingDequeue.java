package com.prince.multithreading;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class BlockingDequeue<E> {

    private final int capacity;

    private int count;

    private Node head;

    private Node tail;

    public BlockingDequeue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void putFirst(E obj) throws InterruptedException {
        while (count == capacity) {
            wait();
        }

        Node<E> node = new Node<>(obj);

        if (count == 0) {
            head = node;
            tail = node;

            notifyAll();
        } else {
            node.next = head;
            head.prev = node;

            head = node;
        }

        count++;
    }

    public synchronized void putLast(E obj) throws InterruptedException {
        while (count == capacity) {
            wait();
        }

        Node<E> node = new Node<>(obj);

        if (count == 0) {
            head = node;
            tail = node;

            notifyAll();
        } else {
            tail.next = node;
            node.prev = tail;

            tail = node;
        }

        count++;
    }

    public synchronized E takeFirst() throws InterruptedException {
        if (count == 0) {
            wait();
        }

        Node<E> node = head;
        if (count == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;

            head.prev = null;
            node.next = null;
        }

        if (count == capacity) {
            notifyAll();
        }

        count--;

        return node.getItem();
    }

    public synchronized E takeLast() throws InterruptedException {
        if (count == 0) {
            wait();
        }

        Node<E> node = tail;
        if (count == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;

            tail.next = null;
            node.prev = null;
        }

        if (count == capacity) {
            notifyAll();
        }

        count--;

        return node.getItem();
    }

    @Data
    private static final class Node<E> {

        private E item;

        private Node prev;

        private Node next;

        private Node(E item) {
            this.item = item;
        }
    }
}
