package com.prince.algo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Prince Raj
 */
public class MatrixSpiral {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int noOfCases = Integer.parseInt(br.readLine());

        for (int i = 0; i < noOfCases; i++) {
            String[] items = br.readLine().split(" ");
            int row = Integer.parseInt(items[0]);
            int col = Integer.parseInt(items[1]);
            int[][] matrix = new int[row][col];

            int m = 0;
            while (m < row) {
                items = br.readLine().split(" ");
                int n = 0;
                for (String item : items) {
                    matrix[m][n] = Integer.parseInt(item);
                    n++;
                }

                m++;
            }

            print(matrix, row, col);
            System.out.println();
        }
    }

    private static void print(int[][] matrix, int row, int col) {
        int lr = 0;
        int ur = row;
        int lc = 0;
        int uc = col;

        while (lr < ur && lc < uc) {
            // 0 row
            for (int j = lc; j < uc; j++) {
                System.out.print(matrix[lr][j] + " ");
            }

            // n-1 col
            for (int i = lr + 1; i < ur; i++) {
                System.out.print(matrix[i][uc - 1] + " ");
            }

            // m-1 row
            for (int j = uc - 2; j >= lc; j--) {
                System.out.print(matrix[ur - 1][j] + " ");
            }

            // 0 col
            for (int i = ur - 2; i > lr; i--) {
                System.out.print(matrix[i][lc] + " ");
            }

            lr++;
            uc--;
            ur--;
            lc++;
        }
    }
}
