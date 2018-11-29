package com.prince.algo;

import lombok.Data;

/**
 * Reverse k-nodes in Linked list.
 *
 * <pre>
 * Ex- n=10, k=4
 * Input:  1->2->3->4->5->6->7->8->9->10
 * Output: 4->3->2->1->8->7->6->5->10->9
 * </pre>
 */
public class ReverseKNodesLinkedList {

    public static void main(String[] args) {
        boolean first = true;

        int[] kArr = {1, 3, 4, 5};
        for (int k : kArr) {
            Node head = createLL(10);
            if (first) {
                System.out.print("Original LL:  ");
                print(head);
                first = false;
            }

            Node newHead = reverseKNodes(head, k);
            System.out.print("New LL (k=" + k + "): ");
            print(newHead);
        }
    }

    private static Node reverseKNodes(Node head, int k) {
        Node head2 = null;
        if (k <= 1 || head == null) {
            head2 = head;
        } else {
            int i = 0;
            boolean first = true;
            Node current = head;
            Node temp = null;
            Node prev = null;
            while (current != null) {
                if (i == 0) {
                    temp = current;
                }

                i++;

                if (i == k || current.getNext() == null) {
                    Node next = current.getNext();
                    current.setNext(null);
                    Node newHead = reverseLLRecursive(temp);
                    if (prev != null) {
                        prev.setNext(newHead);
                    }

                    if (first) {
                        head2 = newHead;
                        first = false;
                    }

                    temp.setNext(next);
                    current = temp;
                    prev = temp;

                    i = 0;
                }

                current = current.getNext();
            }
        }

        return head2;
    }


    private static Node reverseLLRecursive(Node node) {
        if (node == null || node.getNext() == null) {
            return node;
        }

        Node newHead = reverseLLRecursive(node.getNext());
        node.getNext().setNext(node);
        node.setNext(null);

        return newHead;
    }


    private static Node createLL(int n) {
        if (n < 1) {
            return null;
        }

        Node head = null;
        for (int i = n; i > 0; i--) {
            Node node = new Node(i);
            node.setNext(head);
            head = node;
        }

        return head;
    }

    private static void print(Node head) {
        while (head != null) {
            System.out.print(head.getData() + "->");
            head = head.getNext();
        }

        System.out.print("NULL\n");
    }

    @Data
    private static class Node {
        private final int data;
        private Node next;
    }
}
