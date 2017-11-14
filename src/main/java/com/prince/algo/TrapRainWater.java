package com.prince.algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Prince Raj
 */
public class TrapRainWater {

    private static final int[][] SAMPLE_MATRIX = {
            {2, 0, 2},
            {6, 9, 9},
            {7, 4, 0, 9},
            {3, 0, 0, 2, 0, 4},
            {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}};

    public static void main(String[] args) throws Exception {

        int[][] matrix = getMatrix(true);

        for (int k = 0; k < matrix.length; k++) {
            int[] arr = matrix[k];

            int length = arr.length;
            int waterTrapped = 0;

            // eliminate starting 0s
            int startPosition = 0;
            while (arr[startPosition] == 0) {
                startPosition++;
            }

            for (int i = startPosition; i < length - 1; i++) {
                int reference = arr[i];
                int localMaxima = getLocalMaxima(arr, i);
                // System.out.println("localMaxima: " + localMaxima + " reference: " + reference);
                reference = Math.min(reference, localMaxima);

                for (int j = i + 1; j < length; j++) {
                    if (arr[j] != localMaxima) {
                        waterTrapped += (reference - arr[j]);
                        // System.out.println("waterTrapped: " + waterTrapped);
                    } else {
                        i = j - 1;
                        break;
                    }
                }
            }

            // System.out.println("Units of water trapped: " + waterTrapped);
            System.out.println(waterTrapped);
        }
    }

    private static int getLocalMaxima(int[] arr, int index) {
        int reference = arr[index];

        int max = 0;
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] > reference) {
                max = arr[i];
                break;
            }

            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    private static int[][] getMatrix(boolean stdIn) throws IOException {
        int[][] matrix;

        if (stdIn) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            int noOfCases = Integer.parseInt(br.readLine());
            matrix = new int[noOfCases][];
            for (int i = 0; i < noOfCases; i++) {
                int count = Integer.parseInt(br.readLine());
                String[] arrStr = br.readLine().split(" ");
                int[] arr = new int[count];

                for (int j = 0; j < arrStr.length; j++) {
                    arr[j] = Integer.parseInt(arrStr[j]);
                }

                matrix[i] = arr;
            }
        } else {
            matrix = SAMPLE_MATRIX;
        }

        return matrix;
    }
}
