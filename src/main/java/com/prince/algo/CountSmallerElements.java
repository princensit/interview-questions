package com.prince.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Count of smaller elements on right side for a given number. This works for distinct elements.
 *
 * I/p - {2, 7, 4, 5, 6, 1, 8, 2, 9, 3}
 *
 * O/p - {1, 6, 3, 3, 3, 0, 2, 0, 1, 0}
 *
 * @author Prince Raj
 */
public class CountSmallerElements {

    public static void main(String[] args) throws IOException {
        boolean stdIn = false;

        if (stdIn) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int noOfCases = Integer.parseInt(br.readLine());

            int i = 0;
            while (i < noOfCases) {
                int count = Integer.parseInt(br.readLine());
                String[] arrStr = br.readLine().split(" ");
                int[] arr = new int[count];

                for (int j = 0; j < arrStr.length; j++) {
                    arr[j] = Integer.parseInt(arrStr[j]);
                }

                printCountOfSmallerElements(arr);
                System.out.println();

                i++;
            }
        } else {
            int[] arr = {2, 7, 4, 5, 6, 1, 8, 2, 9, 3};

            printCountOfSmallerElements(arr);
        }
    }

    private static void printCountOfSmallerElements(int[] arr) {
        SortedMap<Integer, Integer> sortedMap = new TreeMap<>();
        int[] outputArr = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            int x = arr[i];

            Integer count = sortedMap.get(x);
            if (count == null) {
                count = 0;
            }
            count = count + 1;
            sortedMap.put(x, count);

            SortedMap<Integer, Integer> headMap = sortedMap.headMap(x);

            int smallElements = 0;
            Collection<Integer> values = headMap.values();
            for (Integer c : values) {
                smallElements += c;
            }

            outputArr[i] = smallElements;
        }

        for (int x : outputArr) {
            System.out.print(x + " ");
        }
    }
}
