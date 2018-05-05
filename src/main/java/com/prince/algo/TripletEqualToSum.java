package com.prince.algo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Prince Raj
 */
public class TripletEqualToSum {

    public static void main(String[] args) {
        int[] arr1 = {12, 3, 4, 1, 6, 9};
        int sum = 24;
        printAllTripletsWithoutSpaceComplexity(arr1, sum);

        int[] arr2 = {0, -1, 2, -3, 1};
        printAllTripletsWithSpaceComplexityForZeroSum(arr2);
    }

    // Time Complexity - O(n2), Space Complexity - O(n)
    // Another condition is that all elements are distinct
    private static void printAllTripletsWithSpaceComplexityForZeroSum(int[] arr) {
        for (int i = 0; i < arr.length - 2; i++) {
            Set<Integer> set = new HashSet<>();

            for (int j = i + 1; j < arr.length; j++) {
                int val = -(arr[i] + arr[j]);
                if (set.contains(val)) {
                    System.out.println("Triplet that sum to zero is found: "
                            + arr[i]
                            + ","
                            + arr[j]
                            + ","
                            + -(arr[i] + arr[j]));
                } else {
                    set.add(arr[j]);
                }
            }
        }
    }

    // Time Complexity - O(n2), Space Complexity - O(1)
    private static void printAllTripletsWithoutSpaceComplexity(int[] arr, int sum) {
        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 2; i++) {
            int[] pair = findPair(arr, i + 1, arr.length - 1, sum - arr[i]);
            if (pair != null) {
                System.out.println("Triplet that sum to "
                        + sum
                        + " is found: "
                        + arr[i]
                        + ","
                        + arr[pair[0]]
                        + ","
                        + arr[pair[1]]);
            }
        }
    }

    private static int[] findPair(int[] arr, int start, int end, int sum) {
        int[] pair = null;

        while (start < end) {
            int value = arr[start] + arr[end];
            if (sum == value) {
                pair = new int[2];
                pair[0] = start;
                pair[1] = end;
                break;
            } else if (value < sum) {
                start++;
            } else {
                end--;
            }
        }

        return pair;
    }
}
