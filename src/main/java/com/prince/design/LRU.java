package com.prince.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Prince Raj
 */
public class LRU {

    private final int maxSize;

    private Node head;

    private Node tail;

    private final Map<Integer, Node> nodeMap;

    private LRU(int maxSize) {
        this.maxSize = maxSize;
        this.nodeMap = new HashMap<>();
    }

    public static void main(String[] args) {
        LRU lru = new LRU(4);

        int[] arr = {2, 3, 4, 1, 2, 4, 5, 10, 5, 1};

        for (int x : arr) {
            lru.add(x);
            lru.print();
        }
    }

    private void add(int val) {
        if (head == null && tail == null) {
            Node node = getNode(val);
            head = node;
            tail = node;
            nodeMap.put(val, node);
        } else {
            Node node = nodeMap.get(val);
            if (node != null) {
                if (node.previous != null) {
                    node.previous.next = node.next;
                }else {
                    head = node.next;
                }
                if (node.next != null) {
                    node.next.previous = node.previous;
                }
                node.next=null;
            } else {
                if (nodeMap.size() == maxSize) {
                    nodeMap.remove(head.val);
                    head = head.next;
                }

                node = getNode(val);
                nodeMap.put(val, node);
            }

            // move to tail
            tail.next = node;
            node.previous = tail;

            tail = node;
        }
    }

    private void print() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.val + " ");
            temp = temp.next;
        }

        System.out.println();
    }

    private Node getNode(int val) {
        Node node = new Node();
        node.val = val;
        node.previous = null;
        node.next = null;

        return node;
    }

    private static final class Node {

        private int val;

        private Node previous;

        private Node next;

        // NOTE: equals() and hashcode() not needed
        @Override
        public boolean equals(Object obj) {
            final boolean equals;

            if (this == obj) {
                equals = true;
            } else {
                if (obj == null || getClass() != obj.getClass()) {
                    equals = false;
                } else {
                    Node node = (Node)obj;

                    equals = val == node.val;
                }
            }

            return equals;
        }

        @Override
        public int hashCode() {
            int result = val;
            result = 31 * result;

            return result;
        }
    }
}
