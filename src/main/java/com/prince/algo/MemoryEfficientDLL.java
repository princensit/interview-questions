package com.prince.algo;

import lombok.Data;

/**
 * A memory efficient doubly linked list.
 *
 * We can traverse the XOR list in both forward and reverse direction. While traversing the list we
 * need to remember the address of the previously accessed node in order to calculate the next
 * node’s address.
 *
 * Node A: ptrdiff = 0 XOR address(B) // bitwise XOR of zero and address of B
 *
 * Node B: ptrdiff = address(A) XOR address(C) // bitwise XOR of address of A and address of C
 *
 * ...
 *
 * Node D: ptrdiff = address(C) XOR 0 // bitwise XOR of address of C and 0
 *
 */
public class MemoryEfficientDLL {

    public static void main(String[] args) {
        Node head = new Node();
        head.setData(1);
        head.setPtrdiff(XOR(null, null));

        head = insertAtBeginning(head, 2);
        head = insertAtBeginning(head, 3);

        traverse(head);
    }

    private static Node insertAtBeginning(Node head, int val) {
        Node node = new Node();
        node.setData(val);

        Node ptrdiff = XOR(null, head);
        node.setPtrdiff(ptrdiff);

        if (head != null) {
            ptrdiff = XOR(node, XOR(null, head.getPtrdiff()));
            head.setPtrdiff(ptrdiff);
        }

        head = node;
        return head;
    }

    private static void traverse(Node head) {
        Node current = head;

        Node prev = null;
        while (current != null) {
            System.out.println(current.getData());
            current = XOR(prev, current.getPtrdiff());
        }
    }

    private static Node XOR(Node a, Node b) {
        if (a == null) {
            return b;
        }

        if (b == null) {
            return a;
        }

        // In Java, we can't get the direct address of Java object
        // http://www.params.me/2011/06/xor-linked-list-in-java.html
        // return (Node) a.hashCode() ^ b.hashCode();
        return null;
    }

    @Data
    private static class Node {
        private int data;
        private Node ptrdiff;
    }
}
