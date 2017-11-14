package com.prince.algo;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/find-minimum-number-of-coins-that-make-a-change/
 *
 * Given a value V, if we want to make change for V cents, and we have infinite supply of each of C = { C1, C2, .. , Cm}
 * valued coins, what is the minimum number of coins to make the change
 *
 * @author Prince Raj
 */
public class MinimumCoins {

    private static int counter1;

    private static int counter2;

    public static void main(String[] args) {
        int[] coins = {9, 6, 5, 1};
        int length = coins.length;
        int value = 11;

        System.out.println("Using recursion, min coins: "
                + minCoinsUsingRecursion(coins, 3, value)
                + " in "
                + counter1
                + " calls");

        int[][] dp = new int[length][value];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }
        System.out.println("Using DP (top-down), min coins: "
                + minCoinsUsingDPTopDown(coins, 3, value, dp)
                + " in "
                + counter2
                + " calls");

        System.out.println("Using DP (bottom-up), min coins: " + minCoinsUsingDPBottomUp(coins, 3, value));
    }

    private static int minCoinsUsingRecursion(int[] coins, int m, int v) {
        if (v == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            if (coins[i] <= v) {
                int subMin = minCoinsUsingRecursion(coins, m, v - coins[i]);

                // check for overflow
                if (subMin != Integer.MAX_VALUE && subMin + 1 < min) {
                    min = subMin + 1;
                }
            }
        }

        counter1++;

        return min;
    }

    private static int minCoinsUsingDPTopDown(int[] coins, int m, int v, int dp[][]) {
        if (v == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            if (coins[i] <= v) {
                final int subMin;
                if (dp[m - 1][v - coins[i]] != -1) {
                    subMin = dp[m - 1][v - coins[i]];
                } else {
                    subMin = minCoinsUsingDPTopDown(coins, m - 1, v - coins[i], dp);
                    dp[m - 1][v - coins[i]] = subMin;
                }

                // check for overflow
                if (subMin != Integer.MAX_VALUE && subMin + 1 < min) {
                    min = subMin + 1;
                }
            }
        }

        counter2++;

        return min;
    }

    private static int minCoinsUsingDPBottomUp(int[] coins, int m, int v) {
        // table[i] will be storing the minimum number of coins required for i value. So table[V] will have result
        int table[] = new int[v + 1];
        Arrays.fill(table, Integer.MAX_VALUE);

        // Base case (If given value V is 0)
        table[0] = 0;

        for (int i = 1; i <= v; i++) {
            for (int j = 0; j < m; j++) {
                if (coins[j] <= i) {
                    int subMin = table[i - coins[j]];
                    if (subMin != Integer.MAX_VALUE && subMin + 1 < table[i]) {
                        table[i] = subMin + 1;
                    }
                }
            }
        }

        return table[v];
    }
}
