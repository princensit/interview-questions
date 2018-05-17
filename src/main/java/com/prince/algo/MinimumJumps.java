package com.prince.algo;

/**
 * https://www.geeksforgeeks.org/minimum-number-of-jumps-to-reach-end-of-a-given-array/
 *
 * @author Prince Raj
 */
public class MinimumJumps {

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 8, 9, 2, 6, 7, 6, 8, 9};

        System.out.println("Minimum jumps: " + getMinimumJumps(arr));
    }

    private static int getMinimumJumps(int[] arr) {
        int n = arr.length;
        int[] jumps = new int[n];

        if (n == 0 || arr[0] == 0) {
            return Integer.MAX_VALUE;
        }

        jumps[0] = 0;

        for (int i = 1; i < n; i++) {
            jumps[i] = Integer.MAX_VALUE;
            for (int j = 0; j < i; j++) {
                if (i - j <= arr[j] && jumps[j] != Integer.MAX_VALUE) {
                    jumps[i] = Math.min(jumps[i], jumps[j] + 1);
                    break;
                }
            }
        }

        return jumps[n - 1];
    }
}
