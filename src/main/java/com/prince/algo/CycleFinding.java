package com.prince.algo;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * Floyd's cycle-finding algorithm: A pointer algorithm that uses only two pointers, which move
 * through the sequence at different speeds.
 */
public class CycleFinding {

    public static void main(String[] args) {
        // Approach 1: Using Hash Map, start of loop is just meeting node
        Node head = createLL(10, -1);
        Node meetingNode = detectCycleUsingHashMap(head);
        Node startOfLoop = meetingNode;
        print(startOfLoop);

        head = createLL(11, 5);
        meetingNode = detectCycleUsingHashMap(head);
        startOfLoop = meetingNode;
        print(startOfLoop);

        // Approach 2: Using 2 pointers
        head = createLL(10, -1);
        meetingNode = detectCycleUsingPointers(head);
        startOfLoop = findStartingOfLoop(head, meetingNode);
        print(startOfLoop);

        head = createLL(11, 5);
        meetingNode = detectCycleUsingPointers(head);
        startOfLoop = findStartingOfLoop(head, meetingNode);
        print(startOfLoop);
    }

    // Approach 1: Using Hash Map
    private static Node detectCycleUsingHashMap(Node head) {
        Node meetingNode = null;

        Node current = head;
        Set<Integer> set = new HashSet<>();

        while (current != null) {
            int data = current.getData();
            if (set.contains(data)) {
                meetingNode = current;
                break;
            } else {
                set.add(data);
                current = current.getNext();
            }
        }

        return meetingNode;
    }

    // Approach 2: Using 2 pointers
    private static Node detectCycleUsingPointers(Node head) {
        Node meetingNode = null;

        if (head != null && head.getNext() != null) {
            Node slow = head;
            Node fast = head;

            while (true) {
                slow = slow.getNext();
                fast = fast.getNext().getNext();

                if (slow == fast) {
                    meetingNode = slow;
                    break;
                } else if (slow == null || fast == null || fast.getNext() == null) {
                    break;
                }
            }
        }

        return meetingNode;
    }

    /**
     * <pre>
     * xxxxxxxxxxxyyyyyyyy
     *            z      y
     *            z      y
     *            zzzzzzzy
     *
     * 2∗dist(slowPointer) = dist(fastPointer)
     * 2(x+(y+z)*k+y) = x+(y+z)*n+y
     * x+y = p*(y+z)
     *
     * for p = 1, x = z
     * </pre>
     */
    private static Node findStartingOfLoop(Node head, Node meetingNode) {
        Node startOfLoop = null;
        if (head != null && meetingNode != null) {
            Node p1 = head;
            Node p2 = meetingNode;
            while (p1 != p2) {
                p1 = p1.getNext();
                p2 = p2.getNext();
            }

            startOfLoop = p1;
        }

        return startOfLoop;
    }

    private static Node createLL(int n, int mergeNodeIndex) {
        if (n < 1) {
            return null;
        }

        Node head = null;
        Node lastNode = null;
        Node mergeNode = null;

        for (int i = n; i > 0; i--) {
            Node node = new Node(i);
            node.setNext(head);
            head = node;

            if (i == n) {
                lastNode = node;
            }

            if (i == mergeNodeIndex) {
                mergeNode = node;
            }
        }

        lastNode.setNext(mergeNode);

        return head;
    }

    private static void print(Node startOfLoop) {
        if (startOfLoop != null) {
            System.out.println(
                    "Is cycle detected in LL: true, start of loop: " + startOfLoop.getData());
        } else {
            System.out.println("Is cycle detected in LL: false");
        }
    }

    @Data
    private static class Node {
        private final int data;
        private Node next;
    }
}
