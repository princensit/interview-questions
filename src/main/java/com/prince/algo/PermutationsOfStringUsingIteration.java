package com.prince.algo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Prince Raj
 */
public class PermutationsOfStringUsingIteration {

    public static void main(String[] args) {
        String str = "abc";
        char[] arr = str.toCharArray();

        print(arr);
    }

    private static void print(char[] arr) {
        List<String> results = new ArrayList<>();

        results.add(String.valueOf(arr[0]));

        for (int i = 1; i < arr.length; i++) {
            char c = arr[i];
            for (int j = results.size() - 1; j >= 0; j--) {
                String result = results.remove(j);
                for (int k = 0; k <= result.length(); k++) {
                    results.add(result.substring(0, k) + c + result.substring(k));
                }
            }
        }

        System.out.println(results);
    }
}
