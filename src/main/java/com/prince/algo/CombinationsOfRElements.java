package com.prince.algo;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
 *
 * @author Prince Raj
 */
public class CombinationsOfRElements {

    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int r1 = 3;
        int[] result1 = new int[r1];

        print(arr1, 0, 0, r1, result1);

        System.out.println("#############");

        // handling duplicates
        int[] arr2 = {1, 2, 1, 3, 1};
        int r2 = 3;
        int[] result2 = new int[r2];

        Arrays.sort(arr2);
        printHavingDuplicates(arr2, 0, 0, r2, result2);
    }

    private static void print(int[] arr, int start, int index, int r, int[] result) {
        if (index == r) {
            System.out.println(Arrays.toString(result));
            return;
        }

        // additional condition is used to reduce the iterations
        for (int i = start; i < arr.length && i <= start + r - 1; i++) {
            result[index] = arr[i];
            print(arr, i + 1, index + 1, r, result);
        }
    }

    private static void printHavingDuplicates(int[] arr, int start, int index, int r, int[] result) {
        if (index == r) {
            System.out.println(Arrays.toString(result));
            return;
        }

        // additional condition is used to reduce the iterations
        for (int i = start; i < arr.length && i <= start + r - 1; i++) {
            result[index] = arr[i];
            printHavingDuplicates(arr, i + 1, index + 1, r, result);

            // remove duplicates
            while (i < arr.length - 1 && arr[i] == arr[i + 1]) {
                i++;
            }
        }
    }
}
