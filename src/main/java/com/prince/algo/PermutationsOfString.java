package com.prince.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * http://www.geeksforgeeks.org/write-a-c-program-to-print-all-permutations-of-a-given-string/
 *
 * http://www.geeksforgeeks.org/print-all-permutations-of-a-string-with-duplicates-allowed-in-input-string/
 *
 * Solved using backtracking. Time Complexity: O(n*n!)
 *
 * @author Prince Raj
 */
public class PermutationsOfString {

    public static void main(String[] args) {
        String str1 = "abc";
        char[] arr1 = str1.toCharArray();

        // approach 1
        print(arr1, 0);
        System.out.println("##############");

        // approach 2
        List<String> permutations = getPermutations(str1);
        System.out.println(permutations);

        System.out.println("##############");

        String str2 = "abaab";
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr2);

        // TODO to be fixed
        printHavingDuplicates(arr2, 0);
    }

    private static void print(char[] arr, int start) {
        if (start == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            print(arr, start + 1);
            swap(arr, i, start);
        }
    }

    private static List<String> getPermutations(String str) {
        if (str == null) {
            return null;
        }

        List<String> permutations = new ArrayList<>();
        if (str.isEmpty()) {
            permutations.add("");
            return permutations;
        }

        char firstChar = str.charAt(0);
        String remainder = str.substring(1);
        List<String> oldPermutations = getPermutations(remainder);
        for (String current : oldPermutations) {

            for (int i = 0; i <= current.length(); i++) {
                String s1 = current.substring(0, i);
                String s2 = current.substring(i);

                permutations.add(s1 + firstChar + s2);
            }
        }

        return permutations;
    }

    private static void printHavingDuplicates(char[] arr, int start) {
        if (start == arr.length) {
            System.out.println(Arrays.toString(arr));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            while ((i + 1 < arr.length) && arr[i + 1] == arr[start]) {
                i++;
            }

            swap(arr, start, i);
            printHavingDuplicates(arr, start + 1);
            swap(arr, i, start);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
