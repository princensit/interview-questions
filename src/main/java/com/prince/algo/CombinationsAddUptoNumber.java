package com.prince.algo;

/**
 * http://www.geeksforgeeks.org/find-all-combinations-that-adds-upto-given-number-2/
 *
 * @author Prince Raj
 */
public class CombinationsAddUptoNumber {

    public static void main(String[] args) {
        int sum = 5;
        int arr[] = new int[sum];

        print(arr, sum, 0);
    }

    private static void print(int arr[], int sum, int index) {
        if (sum == 0) {
            for (int i = 0; i < index; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }

        for (int i = 1; i <= sum; i++) {
            // To ensure numbers are in increasing order
            if (index >= 1 && arr[index - 1] > i) {
                continue;
            }

            arr[index] = i;
            print(arr, sum - i, index + 1);
        }
    }
}
