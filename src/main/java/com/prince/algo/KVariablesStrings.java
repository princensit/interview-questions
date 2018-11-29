package com.prince.algo;

import java.util.Arrays;

/**
 * Generate all strings of length from 0 - (k-1) variables
 */
public class KVariablesStrings {

    public static void main(String[] args) {
        int n = 4;
        int k = 2;

        int[] arr = new int[n];

        generateStrings(arr, n, k);
    }

    private static void generateStrings(int[] arr, int n, int k) {
        if (n == 0) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int j = 0; j < k; j++) {
            arr[n - 1] = j;
            generateStrings(arr, n - 1, k);
        }
    }
}
