package com.prince.algo;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/inplace-rotate-square-matrix-by-90-degrees/
 *
 * Given an square matrix, turn it by 90 degrees in anti-clockwise direction without using any extra space.
 *
 * @author Prince Raj
 */
public class RotateSquareMatrix {

    public static void main(String[] args) {
        int[][] matrix = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        rotate1(matrix, matrix.length);
        System.out.println();

        matrix = new int[][] { {1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        rotate2(matrix, matrix.length);
    }

    private static void rotate1(int[][] matrix, int n) {
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - i - 1; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[n - 1 - j][i];
                matrix[n - 1 - j][i] = temp;
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    // transpose and then reverse each column
    private static void rotate2(int[][] matrix, int n) {
        transpose(matrix, n);
        reverseColumns(matrix, n);

        for (int i = 0; i < n; i++) {
            System.out.println(Arrays.toString(matrix[i]));
        }
    }

    private static void transpose(int[][] matrix, int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private static void reverseColumns(int matrix[][], int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0, k = n - 1; j < k; j++, k--) {
                int temp = matrix[j][i];
                matrix[j][i] = matrix[k][i];
                matrix[k][i] = temp;
            }
        }
    }
}
