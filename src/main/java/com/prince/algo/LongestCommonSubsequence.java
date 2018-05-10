package com.prince.algo;

/**
 * @author Prince Raj
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {
        char[] str1 = {'a', 'b', 'c', 'd', 'a', 'f'};
        char[] str2 = {'a', 'c', 'b', 'c', 'f'};

        System.out.println("LCS (Recursive): " + getLCSRecursive(str1, str2, str1.length, str2.length));
        System.out.println("LCS (Optimal Substructure): " + getLCS(str1, str2));
        System.out.println();

        str1 = new char[] {'a', 'c', 'c'};
        str2 = new char[] {'a', 'c', 'c'};
        System.out.println("LCS (Recursive): " + getLCSRecursive(str1, str2, str1.length, str2.length));
        System.out.println("LCS (Optimal Substructure): " + getLCS(str1, str2));
        System.out.println();

        str1 = new char[] {'a', 'c'};
        str2 = new char[] {'a', 'c', 'c', 'd'};
        System.out.println("LCS (Recursive): " + getLCSRecursive(str1, str2, str1.length, str2.length));
        System.out.println("LCS (Optimal Substructure): " + getLCS(str1, str2));
    }

    private static int getLCSRecursive(char[] str1, char[] str2, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }

        if (str1[m - 1] == str2[n - 1]) {
            return 1 + getLCSRecursive(str1, str2, m - 1, n - 1);
        } else {
            return Math.max(getLCSRecursive(str1, str2, m, n - 1), getLCSRecursive(str1, str2, m - 1, n));
        }
    }

    /**
     * <pre>
     * if (str1[i] == str2[j]) {
     *     T[i][j] = T[i - 1][j - 1] + 1;
     * } else {
     *     T[i][j] = Max(T[i - 1][j], T[i][j - 1]);
     * }
     * </pre>
     *
     * This algo will work even if there are duplicates
     *
     * @param str1 str1
     * @param str2 str2
     */
    private static int getLCS(char[] str1, char[] str2) {
        int[][] matrix = new int[str1.length][str2.length];

        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str2.length; j++) {
                // initialize 1st row
                if (i == 0) {
                    if (str1[0] == str2[j]) {
                        if (j == 0) {
                            matrix[0][j] = 1;
                        } else {
                            matrix[0][j] = matrix[0][j - 1];
                        }
                    } else {
                        if (j == 0) {
                            matrix[0][0] = 0;
                        } else {
                            matrix[0][j] = matrix[0][j - 1];
                        }
                    }
                } else {
                    if (str1[i] == str2[j]) {
                        if (j > 0) {
                            matrix[i][j] = matrix[i - 1][j - 1] + 1;
                        } else {
                            matrix[i][j] = matrix[i - 1][0] + 1;
                        }
                    } else {
                        if (j > 0) {
                            matrix[i][j] = Math.max(matrix[i][j - 1], matrix[i - 1][j]);
                        } else {
                            matrix[i][j] = matrix[i - 1][0];
                        }
                    }
                }
            }
        }

        return matrix[str1.length - 1][str2.length - 1];
    }
}
