package com.prince.algo;

import java.util.Arrays;

/**
 * @author Prince Raj
 */
public class BuySellStock {

    public static void main(String[] args) {
        int[][] arr =
                {
                        {},
                        {1},
                        {1, 2},
                        {1, 2, 3, 4, 6},
                        {7, 1, 5, 3, 6, 4},
                        {7, 6, 4, 3, 1},
                        {10, 22, 5, 75, 65, 80},
                        {2, 30, 15, 10, 8, 25, 80},
                        {100, 30, 15, 10, 8, 25, 80},
                        {90, 80, 70, 60, 50},
                        {100, 180, 260, 310, 40, 535, 695}};

        for (int[] anArr : arr) {
            buySellStockOnceToMaximizeProfit(anArr, true);
        }

        for (int[] anArr : arr) {
            buySellStockMultipleToMaximizeProfit(anArr, true);
        }

        for (int[] anArr : arr) {
            buySellStockUpto2TimesToMaximizeProfit(anArr, true);
        }

        for (int[] anArr : arr) {
            buySellStockUpto2TimesToMaximizeProfitOptimized(anArr, true);
        }

        for (int[] anArr : arr) {
            buySellStockUptoKTimesToMaximizeProfit(anArr, 3, true);
        }
    }

    private static int buySellStockOnceToMaximizeProfit(int[] arr, boolean print) {
        int maxProfit = 0;

        if (print) {
            System.out.println("[Once] Calculating max profit for " + Arrays.toString(arr));
        }

        int day1 = -1;
        int day2 = -1;
        int day1dash = 0;

        if (arr.length >= 2) {
            int j = 0;
            // move to first minimum element
            while (j < arr.length - 1 && arr[j] > arr[j + 1]) {
                j++;
            }

            int min = arr[j];
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[i] - min > maxProfit) {
                    maxProfit = arr[i] - min;

                    day1 = day1dash;
                    day2 = i;
                }

                if (arr[i] < min) {
                    min = arr[i];
                    day1dash = i;
                }
            }
        }

        if (print) {
            if (maxProfit > 0) {
                System.out.println("------------> Buy day: " + (day1 + 1) + ", sell day: " + (day2 + 1));
            }
            System.out.println("------------> Max profit: " + maxProfit);
            System.out.println();
        }

        return maxProfit;
    }

    private static int buySellStockMultipleToMaximizeProfit(int[] arr, boolean print) {
        int maxProfit = 0;

        if (print) {
            System.out.println("[Multiple] Calculating max profit for " + Arrays.toString(arr));
        }

        if (arr.length >= 2) {
            int j = 0;
            // move to first minimum element
            while (j < arr.length - 2 && arr[j] > arr[j + 1]) {
                j++;
            }

            int day1;
            int day2;
            int localMinima;
            int localMaxima;

            for (int i = j; i < arr.length; i++) {
                localMinima = arr[i];
                day1 = i;
                while (i < arr.length - 1 && arr[i] < arr[i + 1]) {
                    i++;
                }

                localMaxima = arr[i];
                day2 = i;

                int profit = localMaxima - localMinima;
                if (profit > 0) {
                    maxProfit += profit;
                    if (print) {
                        System.out.println("------------> Buy day: " + (day1 + 1) + ", sell day: " + (day2 + 1));
                    }
                }
            }
        }

        if (print) {
            System.out.println("------------> Max profit: " + maxProfit);
            System.out.println();
        }

        return maxProfit;
    }

    // Time complexity = O(n2)
    private static int buySellStockUpto2TimesToMaximizeProfit(int[] arr, boolean print) {
        int maxProfit = 0;

        if (print) {
            System.out.println("[2 times] Calculating max profit for " + Arrays.toString(arr));
        }

        for (int i = 1; i < arr.length - 1; i++) {
            int profit1 = buySellStockOnceToMaximizeProfit(Arrays.copyOfRange(arr, 0, i + 1), false);
            int profit2 = buySellStockOnceToMaximizeProfit(Arrays.copyOfRange(arr, i + 1, arr.length), false);

            int totalProfit = profit1 + profit2;
            maxProfit = Math.max(maxProfit, totalProfit);
        }

        if (print) {
            System.out.println("------------> Max profit: " + maxProfit);
            System.out.println();
        }

        return maxProfit;
    }

    // Time complexity = O(n), Space complexity = O(n)
    private static int buySellStockUpto2TimesToMaximizeProfitOptimized(int[] arr, boolean print) {
        int maxProfit = 0;

        if (print) {
            System.out.println("[2 times] Calculating max profit for " + Arrays.toString(arr));
        }

        int size = arr.length;
        if (size >= 4) {
            int[] profitFromLeft = new int[size];
            int[] profitFromRight = new int[size];

            // max profit from left to right, considering one buy-sell done
            profitFromLeft[0] = 0;
            int min = arr[0];
            for (int j = 1; j < size; j++) {
                if (arr[j] > min) {
                    profitFromLeft[j] = arr[j] - min;

                    if (profitFromLeft[j] < profitFromLeft[j - 1]) {
                        profitFromLeft[j] = profitFromLeft[j - 1];
                    }
                }

                if (arr[j] < min) {
                    min = arr[j];
                }
            }

            // max profit from right to left, considering one buy-sell done
            profitFromRight[size - 1] = 0;
            int max = arr[size - 1];
            for (int j = size - 2; j >= 0; j--) {
                if (arr[j] < max) {
                    profitFromRight[j] = max - arr[j];

                    if (profitFromRight[j] < profitFromRight[j + 1]) {
                        profitFromRight[j] = profitFromRight[j + 1];
                    }
                }

                if (arr[j] > max) {
                    max = arr[j];
                }
            }

            // calculating max profit using both arrays
            for (int i = 0; i < size - 2; i++) {
                int currentProfit = profitFromLeft[i] + profitFromRight[i + 1];
                if (currentProfit > maxProfit) {
                    maxProfit = currentProfit;
                }
            }
        }

        if (print) {
            System.out.println("------------> Max profit: " + maxProfit);
            System.out.println();
        }

        return maxProfit;
    }

    // https://www.geeksforgeeks.org/maximum-profit-by-buying-and-selling-a-share-at-most-k-times/
    // https://www.youtube.com/watch?v=oDhu5uGq_ic
    private static int buySellStockUptoKTimesToMaximizeProfit(int[] arr, int k, boolean print) {
        int maxProfit = 0;
        return maxProfit;
    }
}
