package com.prince.algo;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class ReverseLinkedList {

    private static Node head = null;

    private static Node tail = null;

    public static void main(String[] args) {
        addNode(1);
        addNode(2);
        addNode(3);
        addNode(4);

        printLL();
        System.out.println();

        reverseLLIterative();

        printLL();
        System.out.println();

        reverseLLRecursive();

        printLL();
    }

    private static void reverseLLIterative() {
        Node current = head;
        Node next = current.getNext();

        current.setNext(null);
        Node nextNext;

        while (next != null) {
            nextNext = next.getNext();
            next.setNext(current);

            current = next;
            next = nextNext;
        }

        head = current;
    }

    private static void reverseLLRecursive() {
        reverseLLRecursive(head, null);
    }

    private static void reverseLLRecursive(Node current, Node prev) {
        Node next = current.getNext();
        current.setNext(prev);

        if (next == null) {
            head = current;
        } else {
            reverseLLRecursive(next, current);
        }
    }

    private static void printLL() {
        Node current = head;
        while (current != null) {
            System.out.print(current.getVal() + " ");
            current = current.getNext();
        }
    }

    private static void addNode(int val) {
        Node node = new Node(val);
        node.setNext(null);

        if (tail == null) {
            head = node;
            tail = node;
        } else {
            tail.setNext(node);
            tail = tail.getNext();
        }
    }

    @Data
    private static class Node {

        private final int val;

        private Node next;
    }
}
