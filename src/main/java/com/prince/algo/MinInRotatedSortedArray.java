package com.prince.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/find-minimum-element-in-a-sorted-and-rotated-array/
 *
 * For duplicates array like {2, 2, 2, 2, 2, 2, 2, 2, 0, 1, 1, 2} and {2, 2, 2, 0, 2, 2, 2, 2, 2, 2, 2, 2}, algo won't
 * work and needs O(n) complexity.
 *
 * @author Prince Raj
 */
public class MinInRotatedSortedArray {

    private static final int[][] SAMPLE_MATRIX = {
            {5, 6, 1, 2, 3, 4},
            {1, 2, 3, 4},
            {1},
            {1, 2},
            {2, 1},
            {5, 6, 7, 1, 2, 3, 4},
            {2, 3, 4, 5, 6, 7, 8, 1},
            {3, 4, 5, 1, 2}};

    public static void main(String[] args) throws Exception {
        int[][] matrix = getMatrix(false);
        for (int k = 0; k < matrix.length; k++) {
            int[] arr = matrix[k];
            int size = arr.length;
            int minimum = getMinimum(matrix[k], 0, size - 1);
            System.out.println("Minimum in array: " + Arrays.toString(arr) + " is " + minimum);
        }
    }

    private static int getMinimum(int[] arr, int low, int high) {
        // this condition is for case when there is no rotation at all
        if (high < low) {
            return arr[0];
        }

        if (low == high) {
            return arr[low];
        }

        int mid = low + (high - low) / 2;
        if (mid < high && arr[mid] > arr[mid + 1]) {
            return arr[mid + 1];
        }

        if (mid > low && arr[mid] < arr[mid - 1]) {
            return arr[mid];
        }

        if (arr[mid] < arr[high]) {
            return getMinimum(arr, low, mid - 1);
        } else {
            return getMinimum(arr, mid + 1, high);
        }
    }

    private static int[][] getMatrix(boolean stdIn) throws IOException {
        int[][] matrix;

        if (stdIn) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int noOfCases = Integer.parseInt(br.readLine());
            matrix = new int[noOfCases][];
            for (int i = 0; i < noOfCases; i++) {
                int count = Integer.parseInt(br.readLine());
                String[] arrStr = br.readLine().split(" ");
                int[] arr = new int[count];

                for (int j = 0; j < arrStr.length; j++) {
                    arr[j] = Integer.parseInt(arrStr[j]);
                }

                matrix[i] = arr;
            }
        } else {
            matrix = SAMPLE_MATRIX;
        }

        return matrix;
    }
}
