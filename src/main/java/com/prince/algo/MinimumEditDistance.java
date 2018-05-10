package com.prince.algo;

import java.util.Arrays;

/**
 * @author Prince Raj
 */
public class MinimumEditDistance {

    public static void main(String[] args) {
        char[] str1 = {'a', 'b', 'c', 'd', 'e', 'f'};
        char[] str2 = {'a', 'z', 'c', 'e', 'd'};

        System.out.println("Min edit distance: " + getMinEdit(str1, str2));
    }

    /**
     * <pre>
     * if (str1[i] == str2[j]) {
     *     T[i][j] = T[i - 1][j - 1];
     * } else {
     *     T[i][j] = Min(T[i - 1][j - 1], T[i - 1][j], T[i][j - 1]);
     * }
     * </pre>
     *
     * @param str1 str1
     * @param str2 str2
     * @return min edit distance
     */
    private static int getMinEdit(char[] str1, char[] str2) {
        int[][] matrix = new int[str1.length][str2.length];

        for (int i = 0; i < str1.length; i++) {
            for (int j = 0; j < str2.length; j++) {
                // initialize 1st row
                if (i == 0) {
                    if (str1[0] == str2[j]) {
                        if (j == 0) {
                            matrix[0][0] = 0;
                        } else {
                            matrix[0][j] = matrix[0][j - 1];
                        }
                    } else {
                        if (j == 0) {
                            matrix[0][0] = 1;
                        } else {
                            matrix[0][j] = matrix[0][j - 1] + 1;
                        }
                    }
                } else {
                    if (str1[i] == str2[j]) {
                        if (j > 0) {
                            matrix[i][j] = matrix[i - 1][j - 1];
                        } else {
                            matrix[i][0] = matrix[i - 1][0];
                        }
                    } else {
                        if (j > 0) {
                            matrix[i][j] = getMin(matrix[i - 1][j - 1], matrix[i - 1][j], matrix[i][j - 1]) + 1;
                        } else {
                            matrix[i][0] = getMin(matrix[i - 1][0]) + 1;
                        }
                    }
                }
            }
        }

        System.out.println(Arrays.toString(str1) + " -> " + Arrays.toString(str2));
        // str1 -> str2 min edit matrix
        for (int i = 0; i < str1.length; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }

        return matrix[str1.length - 1][str2.length - 1];
    }

    private static int getMin(int... args) {
        int min = args[0];
        for (int arg : args) {
            if (arg < min) {
                min = arg;
            }
        }

        return min;
    }
}
