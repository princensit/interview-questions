package com.prince.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prince Raj
 */
public class MinHeap {

    private List<Integer> list;

    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap();
        minHeap.insert(5);
        minHeap.insert(4);
        minHeap.insert(3);
        minHeap.insert(6);
        minHeap.insert(2);
        minHeap.insert(1);

        minHeap.print();
        System.out.println("Minimum: " + minHeap.extractMin());
        System.out.println("-----------------------");
        minHeap.print();
        System.out.println("Minimum: " + minHeap.getMin());
    }

    public MinHeap() {
        list = new ArrayList<>();
    }

    public MinHeap(List<Integer> items) {
        list = items;

        buildHeap();
    }

    // bubble-up until heap property is restored
    public void insert(int item) {
        list.add(item);

        int i = list.size() - 1;
        int parent = getParent(i);

        while (parent != i && list.get(i) < list.get(parent)) {
            swap(i, parent);
            i = parent;
            parent = getParent(i);
        }
    }

    public int extractMin() {
        int size = list.size();

        if (size == 0) {
            throw new RuntimeException("Min Heap is empty");
        }

        if (size == 1) {
            return list.remove(0);
        }

        // remove the last item and set it as root
        int min = list.get(0);
        int lastItem = list.remove(size - 1);
        list.set(0, lastItem);

        // bubble-down until heap property is maintained
        minHeapify(0);

        return min;
    }

    public int getMin() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }

    public void print() {
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            int left = getLeft(i);
            int right = getRight(i);

            String leftChild = left < size ? String.valueOf(list.get(left)) : "NA";
            String rightChild = right < size ? String.valueOf(list.get(right)) : "NA";
            System.out.println("Parent: " + list.get(i) + ", left child: " + leftChild
                    + ", right child: " + rightChild);
        }
    }

    private void buildHeap() {
        // only right half is considered because all leaf nodes are on the right side
        for (int i = list.size() / 2; i >= 0; i--) {
            minHeapify(i);
        }
    }

    private void minHeapify(int i) {
        int size = list.size();

        int left = getLeft(i);
        int right = getRight(i);
        int smallest = i;

        // smallest between node and its children
        if (left < size && list.get(left) < list.get(i)) {
            smallest = left;
        } else if (right < size && list.get(right) < list.get(i)) {
            smallest = right;
        }

        if (smallest != i) {
            swap(smallest, i);

            minHeapify(smallest);
        }
    }

    private void swap(int i, int parent) {
        int temp = list.get(parent);
        list.set(parent, list.get(i));
        list.set(i, temp);
    }

    private int getLeft(int i) {
        return 2 * i + 1;
    }

    private int getRight(int i) {
        return 2 * i + 2;
    }

    private int getParent(int i) {
        if (i % 2 == 0) {
            return i / 2;
        } else {
            return (i - 1) / 2;
        }
    }
}
