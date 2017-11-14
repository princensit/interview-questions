package com.prince.algo.tree;

import java.util.ArrayDeque;
import java.util.Deque;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Prince Raj
 */
public class ThreadedBinaryTree {

    private Node threadedNext = null;

    private final Deque<Node> deque = new ArrayDeque<>();

    public static void main(String[] args) {
        ThreadedBinaryTree tbt = new ThreadedBinaryTree();

        Node root;

        root = tbt.insertNodeAsBalanceTree(1);
        tbt.insertNodeAsBalanceTree(2);
        tbt.insertNodeAsBalanceTree(3);
        tbt.insertNodeAsBalanceTree(4);
        tbt.insertNodeAsBalanceTree(5);
        tbt.insertNodeAsBalanceTree(6);
        tbt.insertNodeAsBalanceTree(7);
        tbt.insertNodeAsBalanceTree(8);

        System.out.print("Inorder: ");
        tbt.printInorder(root);
        System.out.print("\nPreorder: ");
        tbt.printPreorder(root);
        System.out.print("\nPostorder: ");
        tbt.printPostorder(root);
        System.out.println();

        // convert to threaded tree
        System.out.print("\nConverting to threaded binary tree");
        tbt.convertToThreadedTree(root);
        System.out.print("\nInorder threaded: ");
        tbt.printInorderThreadedTree(root);
        System.out.print("\nReset threaded binary tree to original tree");
        tbt.resetTree(root);
        System.out.print("\nInorder: ");
        tbt.printInorder(root);
    }

    private void convertToThreadedTree(Node node) {
        if (node != null) {
            convertToThreadedTree(node.getRight());

            Node right = node.getRight();
            if (right == null && threadedNext != null) {
                node.setRight(threadedNext);
                node.setRightThreaded(true);
            }

            threadedNext = node;

            convertToThreadedTree(node.getLeft());
        }
    }

    private void resetTree(Node node) {
        if (node != null) {
            if (node.isRightThreaded()) {
                node.setRightThreaded(false);
                node.setRight(null);
            }

            resetTree(node.getLeft());
            resetTree(node.getRight());
        }
    }

    private void printInorderThreadedTree(Node node) {
        node = getLeftMost(node);

        while (node != null) {
            System.out.print(node.getVal() + " ");

            if (node.isRightThreaded()) {
                node = node.getRight();
            } else {
                node = getLeftMost(node.getRight());
            }
        }
    }

    private void printInorder(Node node) {
        if (node != null) {
            Node left = node.getLeft();
            if (left != null) {
                printInorder(left);
            }

            System.out.print(node.getVal() + " ");

            Node right = node.getRight();
            if (right != null) {
                printInorder(right);
            }
        }
    }

    private void printPreorder(Node node) {
        if (node != null) {
            System.out.print(node.getVal() + " ");

            Node left = node.getLeft();
            if (left != null) {
                printPreorder(left);
            }

            Node right = node.getRight();
            if (right != null) {
                printPreorder(right);
            }
        }
    }

    private void printPostorder(Node node) {
        if (node != null) {
            Node left = node.getLeft();
            if (left != null) {
                printPostorder(left);
            }

            Node right = node.getRight();
            if (right != null) {
                printPostorder(right);
            }

            System.out.print(node.getVal() + " ");
        }
    }

    private void printTree(Node node) {
        System.out.println(node);
    }

    private Node insertNode(Node node, int val) {
        if (node == null) {
            node = getNode(val);
        } else {
            Node left = node.getLeft();
            if (left == null) {
                node.setLeft(insertNode(null, val));
            } else {
                Node right = node.getRight();
                node.setRight(insertNode(right, val));
            }
        }

        return node;
    }

    private Node getLeftMost(Node node) {
        if (node == null) {
            return null;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
        }

        return node;
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
        node.setRightThreaded(false);

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

        @Getter
        @Setter
        private boolean rightThreaded;
    }
}
