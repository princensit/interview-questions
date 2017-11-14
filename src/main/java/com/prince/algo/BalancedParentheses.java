package com.prince.algo;

/**
 * @author Prince Raj
 */
public class BalancedParentheses {

    public static void main(String[] args) {
        int k = 4;

        char arr[] = new char[k * 2];
        print(k, 0, 0, arr, 0);
    }

    private static void print(int n, int open, int close, char arr[], int index) {
        if (close == n) {
            for (char c : arr) {
                System.out.print(c + " ");
            }
            System.out.println();
            return;
        }

        if (open < n) {
            arr[index] = '(';
            print(n, open + 1, close, arr, index + 1);
        }

        if (open > close) {
            arr[index] = ')';
            print(n, open, close + 1, arr, index + 1);
        }
    }
}
