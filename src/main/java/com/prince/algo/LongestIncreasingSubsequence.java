package com.prince.algo;

import java.util.Arrays;

/**
 * @author Prince Raj
 */
public class LongestIncreasingSubsequence {

    private static int maxRef = 1;

    public static void main(String[] args) {
        int[] arr = {10, 22, 9, 33, 21, 50, 41, 60};

        System.out.println(lis(arr, arr.length));

        System.out.println(lisNonRecursive(arr));
    }

    private static int lis(int[] arr, int n) {

        // base case
        if (n == 1) {
            return 1;
        }

        int res;
        int maxEndingHere = 1;

        for (int i = 1; i < n; i++) {
            res = lis(arr, i);
            if (arr[i - 1] < arr[n - 1] && res + 1 > maxEndingHere) {
                maxEndingHere = res + 1;
            }
        }
        if (maxRef < maxEndingHere) {
            maxRef = maxEndingHere;
        }

        return maxEndingHere;
    }

    private static int lisNonRecursive(int[] arr) {
        int maxRef = 1;
        int n = arr.length;

        int[] res = new int[n];
        Arrays.fill(res, 1);

        int[] parentIndex = new int[n];
        Arrays.fill(parentIndex, -1);

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i] && res[i] < res[j] + 1) {
                    parentIndex[i] = j;
                    res[i] = res[j] + 1;
                }
            }
        }

        int maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (maxRef < res[i]) {
                maxRef = res[i];
                maxIndex = i;
            }
        }

        int k = maxRef;
        int[] path = new int[k];
        int index = maxIndex;
        while (k > 0) {
            path[--k] = arr[index];
            index = parentIndex[index];
        }

        System.out.println(Arrays.toString(path));

        return maxRef;
    }
}
