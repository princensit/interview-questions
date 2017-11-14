package com.prince.algo;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 *
 * Given a rod of length n inches and an array of prices that contains prices of all pieces of size smaller than n.
 * Determine the maximum value obtainable by cutting up the rod and selling the pieces.
 *
 * @author Prince Raj
 */
public class RodCutting {

    private static int counter1;

    private static int counter2;

    public static void main(String[] args) {
        int[] price = {3, 5, 8, 9, 10, 17, 17, 20};
        int n = price.length;

        System.out.println("Using recursion, max price: "
                + cutRodUsingRecursion(price, n)
                + " in "
                + counter1
                + " calls");

        int[] dp = new int[n];
        Arrays.fill(dp, -1);
        System.out.println("Using DP (top-down), max price: "
                + cutRodUsingDPTopDown(price, n, dp)
                + " in "
                + counter2
                + " calls");
    }

    private static int cutRodUsingRecursion(int price[], int n) {
        if (n == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            max = Math.max(max, price[i] + cutRodUsingRecursion(price, n - i - 1));
        }

        counter1++;

        return max;
    }

    private static int cutRodUsingDPTopDown(int price[], int n, int dp[]) {
        if (n == 0) {
            return 0;
        }

        int max = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            final int tempPrice;
            if (dp[n - i - 1] != -1) {
                tempPrice = dp[n - i - 1];
            } else {
                tempPrice = cutRodUsingDPTopDown(price, n - i - 1, dp);
                dp[n - i - 1] = tempPrice;
            }
            max = Math.max(max, price[i] + tempPrice);
        }

        counter2++;

        return max;
    }

    private static int cutRodUsingDPBottomUp(int price[], int n) {
        // TODO discuss
        return 0;
    }
}
