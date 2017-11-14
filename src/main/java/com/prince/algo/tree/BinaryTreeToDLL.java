package com.prince.algo.tree;

import java.util.ArrayDeque;
import java.util.Deque;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Prince Raj
 */
public class BinaryTreeToDLL {

    private final Deque<Node> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        BinaryTreeToDLL treeToDLL = new BinaryTreeToDLL();

        Node root;

        root = treeToDLL.insertNodeAsBalanceTree(1);
        treeToDLL.insertNodeAsBalanceTree(2);
        treeToDLL.insertNodeAsBalanceTree(3);
        treeToDLL.insertNodeAsBalanceTree(4);
        treeToDLL.insertNodeAsBalanceTree(5);
        treeToDLL.insertNodeAsBalanceTree(6);
        treeToDLL.insertNodeAsBalanceTree(7);
        treeToDLL.insertNodeAsBalanceTree(8);

        treeToDLL.convertToDLL(root);
        treeToDLL.print(root);
    }

    // TODO
    private Node convertToDLL(Node node) {
        if (node == null) {
            return null;
        }

        Node leftList = convertToDLL(node.getLeft());
        Node rightList = convertToDLL(node.getRight());

        if (rightList != null) {
            while (rightList.getLeft() != null) {
                rightList = rightList.getLeft();
            }
        }

        return concatenate(leftList, rightList, node);
    }

    private Node concatenate(Node leftList, Node rightList, Node node) {
        if (leftList != null) {
            leftList.setRight(node);
            node.setLeft(leftList);
        }

        if (rightList != null) {
            rightList.setLeft(node);
            node.setRight(rightList);
        }

        while (node.getRight() != null) {
            node = node.getRight();
        }

        return node;
    }

    private void print(Node node) {
        while (node != null) {
            System.out.print(node.getVal() + " ");
            node = node.getRight();
        }
    }

    private Node insertNodeAsBalanceTree(int val) {
        Node newNode = getNode(val);
        if (deque.isEmpty()) {
            deque.add(newNode);
        } else {
            while (true) {
                Node first = deque.peekFirst();
                Node firstLeft = first.getLeft();
                Node firstRight = first.getRight();

                if (firstLeft == null) {
                    first.setLeft(newNode);
                    deque.add(newNode);
                    break;
                } else if (firstRight == null) {
                    first.setRight(newNode);
                    deque.add(newNode);
                    deque.remove();
                    break;
                }
            }
        }

        return newNode;
    }

    private Node getNode(int val) {
        Node node = new Node();
        node.setVal(val);
        node.setLeft(null);
        node.setRight(null);

        return node;
    }

    private static final class Node {

        @Getter
        @Setter
        private int val;

        @Getter
        @Setter
        private Node left;

        @Getter
        @Setter
        private Node right;
    }
}
