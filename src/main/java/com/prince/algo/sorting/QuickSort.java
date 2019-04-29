package com.prince.algo.sorting;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Prince Raj
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] numbers = {3, 6, 1, 5, 8, 9, 1};

        QuickSort algo = new QuickSort();
        algo.quickSort(numbers, 0, numbers.length - 1);

        System.out.println(Arrays.toString(numbers));
    }

    private void quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            // select pivot randomly
            int q = randomizedPartition(numbers, low, high);
            quickSort(numbers, low, q - 1);
            quickSort(numbers, q + 1, high);
        }
    }

    private int randomizedPartition(int[] numbers, int low, int high) {
        Random rand = new Random();
        int randomPivot = rand.nextInt(high - low + 1) + low;

        swap(numbers, randomPivot, high);
        return partition(numbers, low, high);
    }

    private int partition(int[] numbers, int low, int high) {
        // select the right most number as pivot
        int pivot = numbers[high];

        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (numbers[j] <= pivot) {
                i++;
                swap(numbers, j, i);
            }
        }

        // finally swap the pivot with the first number which is greater then pivot
        swap(numbers, i + 1, high);

        // return the index of pivot
        return i + 1;
    }

    private void swap(int[] numbers, int i, int j) {
        int temp = numbers[i];
        numbers[i] = numbers[j];
        numbers[j] = temp;
    }
}
