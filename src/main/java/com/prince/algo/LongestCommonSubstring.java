package com.prince.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Prince Raj
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        char[] str1 = {'a', 'k', 'p', 'b', 'c', 'd', 'a', 'f'};
        char[] str2 = {'z', 'q', 'b', 'c', 'd', 'f'};

        System.out.println("Longest Common Substring (Recursive) length: "
                + getLongestCommonSubstringRecursive(str1, str2, str1.length, str2.length));

        System.out.println("Longest Common Substring (Optimal Substructure) length: "
                + getLongestCommonSubstring(str1, str2));
    }

    /**
     * <pre>
     * if (str1[i] == str2[j]) {
     *     T[i][j] = T[i - 1][j - 1] + 1;
     * } else {
     *     T[i][j] = 0;
     * }
     * </pre>
     *
     * @param str1 str1
     * @param str2 str2
     * @return longest length
     */
    private static int getLongestCommonSubstringRecursive(char[] str1, char[] str2, int m, int n) {
        int max = 0;
        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str2.length; j++) {
                int val = getLongestCommonSuffix(str1, str2, i, j);
                if (val > max) {
                    max = val;
                }
            }
        }

        return max;
    }

    private static int getLongestCommonSuffix(char[] str1, char[] str2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (str1[m - 1] == str2[n - 1]) {
            return 1 + getLongestCommonSuffix(str1, str2, m - 1, n - 1);
        } else {
            return 0;
        }
    }

    /**
     * <pre>
     * if (str1[i] == str2[j]) {
     *     T[i][j] = T[i - 1][j - 1] + 1;
     * } else {
     *     T[i][j] = 0;
     * }
     * </pre>
     *
     * This algo will work even if there are duplicates
     *
     * @param str1 str1
     * @param str2 str2
     * @return longest length
     */
    private static int getLongestCommonSubstring(char[] str1, char[] str2) {
        int[][] matrix = new int[str1.length + 1][str2.length + 1];

        // initialize first row and column to 0
        for (int j = 0; j < str2.length + 1; j++) {
            matrix[0][j] = 0;
        }
        for (int i = 0; i < str1.length + 1; i++) {
            matrix[i][0] = 0;
        }

        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (str1[i] == str2[j]) {
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                } else {
                    matrix[i][j] = 0;
                }
            }
        }

        int max = 0;
        int x = 0;
        int y = 0;
        for (int i = 1; i < str1.length; i++) {
            for (int j = 1; j < str2.length; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    x = i;
                    y = j;
                }
            }
        }

        int longest = matrix[x][y];

        List<Character> path = new ArrayList<>();
        while (matrix[x][y] > 0) {
            path.add(str2[y]);
            x--;
            y--;
        }

        Collections.reverse(path);
        System.out.println("Longest Common Substring: " + path);

        return longest;
    }
}
