package com.prince.algo;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-7-coin-change/
 *
 * Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm}
 * valued coins, how many ways can we make the change?
 *
 * @author Prince Raj
 */
public class CoinsChange {

    public static void main(String[] args) {
        int[] s = {1, 2, 3};
        int m = s.length;
        int n = 4;

        System.out.println(count(s, m, n));
    }

    private static int count(int[] s, int m, int n) {
        // If n is 0 then there is 1 solution (do not include any coin)
        if (n == 0) {
            return 1;
        }

        if (n < 0) {
            return 0;
        }

        if (m <= 0 && n > 0) {
            return 0;
        }

        return count(s, m, n - s[m - 1]) + count(s, m - 1, n);
    }
}
