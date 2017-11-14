package com.prince.algo.slidingwindow;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author Prince Raj
 */
public class MaxInKSizeWindow {

    private final int[] arr;

    private final int windowSize;

    // indexes for elements in that order
    private final Deque<Integer> indexes = new ArrayDeque<>();

    private MaxInKSizeWindow(int[] arr, int windowSize) {
        this.arr = arr;
        this.windowSize = windowSize;

        if (arr.length < windowSize) {
            throw new RuntimeException("Window size is greater than arr size");
        }

        for (int i = 0; i < windowSize - 1; i++) {
            add(i);
        }
    }

    public static void main(String[] args) {
        int windowSize = 3;
        int[][] matrix =
                { {1, 2, 3, 1, 4, 5, 2, 3, 6}, {8, 5, 10, 7, 9, 4, 15, 12, 90, 13}, {12, 1, 78, 90, 57, 89, 56}};

        for (int[] arr : matrix) {
            System.out.println("=============>");
            MaxInKSizeWindow window = new MaxInKSizeWindow(arr, windowSize);

            for (int i = windowSize - 1; i < arr.length; i++) {
                System.out.println(window.getMax(i));
            }
        }
    }

    private Integer getMax(int i) {
        int index = indexes.peekFirst();
        if (index < i + 1 - windowSize) {
            indexes.removeFirst();
        }

        add(i);

        return arr[indexes.peekFirst()];
    }

    private void add(int i) {
        Integer first = indexes.peekFirst();
        if (first == null) {
            indexes.add(i);
        } else {
            if (arr[first] < arr[i]) {
                indexes.clear();
            } else {
                while (arr[indexes.peekLast()] < arr[i]) {
                    indexes.removeLast();
                }
            }

            indexes.add(i);
        }
    }
}
