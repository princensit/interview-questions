package com.prince.algo;

import java.util.Arrays;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-10-0-1-knapsack-problem/
 *
 * @author Prince Raj
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] price = {60, 100, 120};
        int[] weight = {10, 20, 30};
        int w = 50; // max capacity of knapsack

        // Approach 1
        System.out.println(knapsackUsingRecursion(price, weight, w, price.length));

        int[][] dp = new int[w + 1][price.length + 1];
        for (int[] arr : dp) {
            Arrays.fill(arr, -1);
        }

        // Approach 2
        System.out.println(knapsackUsingDPTopDown(price, weight, w, price.length, dp));

        // Approach 3
        System.out.println(knapsackUsingDPBottomUp(price, weight, w));
    }

    private static int knapsackUsingRecursion(int[] price, int[] weight, int w, int n) {
        if (n == 0 || w == 0) {
            return 0;
        }

        if (weight[n - 1] > w) {
            return knapsackUsingRecursion(price, weight, w, n - 1);
        } else {
            return Math.max(
                    price[n - 1] + knapsackUsingRecursion(price, weight, w - weight[n - 1], n - 1),
                    knapsackUsingRecursion(price, weight, w, n - 1));
        }
    }

    private static int knapsackUsingDPTopDown(int[] price, int[] weight, int w, int n, int dp[][]) {
        if (n == 0 || w == 0) {
            return 0;
        }

        if (weight[n - 1] > w) {
            if (dp[w][n - 1] == -1) {
                dp[w][n - 1] = knapsackUsingDPTopDown(price, weight, w, n - 1, dp);
            }
            return dp[w][n - 1];
        } else {
            int val1;
            int val2;

            if (dp[w - weight[n - 1]][n - 1] == -1) {
                dp[w - weight[n - 1]][n - 1] = knapsackUsingDPTopDown(price, weight, w - weight[n - 1], n - 1, dp);
            }
            val1 = dp[w - weight[n - 1]][n - 1];

            if (dp[w][n - 1] == -1) {
                dp[w][n - 1] = knapsackUsingDPTopDown(price, weight, w, n - 1, dp);
            }
            val2 = dp[w][n - 1];

            return Math.max(price[n - 1] + val1, val2);
        }
    }

    private static int knapsackUsingDPBottomUp(int[] price, int[] weight, int W) {
        int[][] dp = new int[price.length + 1][W + 1];

        for (int i = 0; i <= price.length; i++) {
            for (int j = 0; j <= W; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (weight[i - 1] > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(price[i - 1] + dp[i - 1][j - weight[i - 1]], dp[i - 1][j]);
                    }
                }
            }
        }

        return dp[price.length][W];
    }
}
