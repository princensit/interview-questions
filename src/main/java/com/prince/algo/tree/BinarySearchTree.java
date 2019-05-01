package com.prince.algo.tree;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class BinarySearchTree {

    /**
     * Easy Case : If x's right subtree is nonempty, return minimum key in right subtree.
     *
     * Otherwise : Find the first ancestor y which is also a left child of it's parent z. Then
     * return z.
     *
     * <pre>
     *               3(z)
     *             /     \
     *            /       \
     *           1(y)      5
     *            \       /
     *             \     4
     *              2(x)
     * </pre>
     *
     * @param x node for which successor to be found
     * @return successor of x
     */
    public Node treeSuccessor(Node x) {
        if (x.getRightChild() != null) {
            return treeMinimum(x.getRightChild());
        }

        Node parent = x.getParent();

        while (parent != null && parent.getRightChild() == x) {
            x = parent;
            parent = x.getParent();
        }

        return parent;
    }

    /**
     * Easy Case : If x's left subtree is nonempty, return maximum key in left subtree.
     *
     * Otherwise : Follow parent pointers until you get to a key less than k.
     *
     * <pre>
     *               3
     *             /   \
     *            /     \
     *           1       5
     *            \      /
     *             \    4(x)
     *              2
     * </pre>
     *
     * @param x node for which successor to be found
     * @return successor of x
     */
    public Node treePredecessor(Node x) {
        if (x.getLeftChild() != null) {
            return treeMaximum(x.getLeftChild());
        }

        Node parent = x.getParent();

        while (parent != null && parent.getKey() > x.getKey()) {
            parent = parent.getParent();
        }

        return parent;
    }

    public boolean isBalanced(Node x) {
        if (x == null) {
            return false;
        }

        int leftDepth = getHeight(x.getLeftChild());
        int rightDepth = getHeight(x.getRightChild());

        if (Math.abs(leftDepth - rightDepth) <= 1 && isBalanced(x.getLeftChild())
                && isBalanced(x.getRightChild())) {
            return true;
        } else {
            return false;
        }
    }

    public int getHeight(Node x) {
        if (x == null) {
            return 0;
        }

        int left = 0;
        int right = 0;

        if (x.getLeftChild() != null) {
            left = getHeight(x.getLeftChild()) + 1;
        }

        if (x.getRightChild() != null) {
            right = getHeight(x.getRightChild()) + 1;
        }

        return Math.max(left, right);
    }

    public Node treeMinimum(Node x) {
        while (x.getLeftChild() != null) {
            x = x.getLeftChild();
        }

        return x;
    }

    public Node treeMaximum(Node x) {
        while (x.getRightChild() != null) {
            x = x.getRightChild();
        }

        return x;
    }

    @Data
    private static final class Node {

        private int key;

        private Node leftChild;

        private Node rightChild;

        private Node parent;
    }
}
