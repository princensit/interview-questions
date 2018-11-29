package com.prince.algo;

import lombok.Data;

import java.util.*;

/**
 * Clone a linked list with next and random pointer:
 * <p>
 * https://www.geeksforgeeks.org/a-linked-list-with-next-and-arbit-pointer/
 * https://www.geeksforgeeks.org/clone-linked-list-next-random-pointer-o1-space/
 */
public class CopyLinkedList {

    public static void main(String[] args) {
        Random random = new Random();
        int sizeOfLL = 5 + random.nextInt(5);

        Node head = createLL(sizeOfLL);
        print(head);

        System.out.println("\n\nCloned LL using hash map: O(n) time complexity, O(n) space complexity");
        Node head2 = copyLLUsingHashMap(head);
        print(head2);

        System.out.println("\n\nCloned LL without extra space: O(n) time complexity, O(1) space complexity");
        head2 = copyLLOptimized(head);
        print(head2);
    }

    private static Node copyLLUsingHashMap(Node head) {
        Map<Node, Node> nodeMap = new HashMap<>();

        Node head2 = null;
        Node current2 = null;

        // copy next pointers
        Node current = head;
        while (current != null) {
            int data = current.getData();
            Node newNode = new Node(data);
            if (head2 == null) {
                head2 = newNode;
                current2 = newNode;
            } else {
                current2.setNext(newNode);
                current2 = current2.getNext();
            }

            nodeMap.put(current, newNode);

            current = current.getNext();
        }

        if (current2 != null) {
            current2.setNext(null);
        }

        // copy random pointers
        current = head;
        current2 = head2;
        while (current != null) {
            Node random = current.getRandom();
            Node newRandom = nodeMap.get(random);
            current2.setRandom(newRandom);

            current = current.getNext();
            current2 = current2.getNext();
        }

        return head2;
    }

    /*
    1->2->3->4
    !  !  !  !
    3  1  2  4

    1->1->2->2->3->3->4->4

     */
    private static Node copyLLOptimized(Node head) {
        if (head == null) {
            return null;
        }

        // copy next pointers
        Node current = head;
        while (current != null) {
            int data = current.getData();
            Node newNode = new Node(data);

            Node next = current.getNext();
            current.setNext(newNode);
            newNode.setNext(next);

            current = current.getNext().getNext();
        }

        // copy random pointers
        current = head;
        while (current != null) {
            Node random = current.getRandom();
            current.getNext().setRandom(random.getNext());

            current = current.getNext().getNext();
        }

        // split LL
        current = head;
        Node head2 = current.getNext();
        while (current != null) {
            Node next = current.getNext().getNext();
            Node nextNext = null;
            if (next != null) {
                nextNext = next.getNext();
            }
            current.getNext().setNext(nextNext);
            current.setNext(next);

            current = current.getNext();
        }

        return head2;
    }

    private static Node createLL(int n) {
        if (n < 1) {
            return null;
        }

        List<Integer> shuffledNumbers = new ArrayList<>();
        Map<Integer, Node> nodeMap = new HashMap<>();
        Node head = null;
        for (int i = n; i > 0; i--) {
            Node node = new Node(i);
            node.setNext(head);
            head = node;

            nodeMap.put(i, node);
            shuffledNumbers.add(i);
        }

        // setting random pointers
        Collections.shuffle(shuffledNumbers);
        for (int i = 1; i <= n; i++) {
            Node node = nodeMap.get(i);
            Node randomNode = nodeMap.get(shuffledNumbers.get(i - 1));
            node.setRandom(randomNode);
        }

        return head;
    }

    private static void print(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.getData() + "->");
            current = current.getNext();
        }
        System.out.print("NULL");
        System.out.println();

        current = head;
        while (current != null) {
            System.out.print("!  ");
            current = current.getNext();
        }
        System.out.println();

        current = head;
        while (current != null) {
            System.out.print(current.getRandom().getData() + "  ");
            current = current.getNext();
        }
    }

    @Data
    private static class Node {
        private final int data;
        private Node next;
        private Node random;

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }

            if (obj == null || obj.getClass() != this.getClass()) {
                return false;
            }

            Node node = (Node) obj;
            return node.data == this.data;
        }

        @Override
        public int hashCode() {
            return this.data;
        }
    }
}
