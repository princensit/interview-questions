package com.prince.algo;

import java.util.Arrays;

/**
 * https://www.youtube.com/watch?v=RORuwHiblPc
 *
 * Given a sequence of words, and a limit on the number of characters that can be put in one line (line width). Put line
 * breaks in the given sequence such that the lines are printed neatly
 *
 * @author Prince Raj
 */
public class TextJustification {

    public static void main(String[] args) {
        String[] words = {"prince", "raj", "likes", "to", "code"};
        int width = 10; // width of screen

        System.out.println("Text justification minimum cost:" + minimumCost(words, width));
    }

    /**
     * <pre>
     * M[i] = Min { M[j] + C[i][j-1]}
     *        i < j < len
     * </pre>
     *
     * @return min cost
     */
    private static int minimumCost(String[] words, int width) {
        int[][] cost = new int[words.length][words.length];
        for (int i = 0; i < words.length; i++) {
            for (int j = i; j < words.length; j++) {
                cost[i][j] = getCost(getRemainingSpaces(words, i, j, width));
            }
        }

        for (int[] aCost : cost) {
            System.out.println(Arrays.toString(aCost));
        }

        System.out.println();

        int[] minCost = new int[words.length];
        int[] result = new int[words.length];

        for (int i = words.length - 1; i >= 0; i--) {
            minCost[i] = cost[i][words.length - 1];
            result[i] = words.length;

            for (int j = words.length - 1; j > i; j--) {
                if (cost[i][j - 1] == Integer.MAX_VALUE) {
                    continue;
                }
                if (minCost[i] > minCost[j] + cost[i][j - 1]) {
                    minCost[i] = minCost[j] + cost[i][j - 1];
                    result[i] = j;
                }
            }
        }

        // print result
        int k = 0;
        while (k < words.length) {
            int toWordIndex = result[k];
            for (int i = k; i < toWordIndex; i++) {
                if (i != k) {
                    System.out.print(" ");
                }
                System.out.print(words[i]);
            }
            System.out.println();
            k = toWordIndex;
        }

        System.out.println();

        return minCost[0];
    }

    private static int getCost(int remainingSpaces) {
        return (remainingSpaces == Integer.MAX_VALUE) ? Integer.MAX_VALUE : (int)Math.pow(remainingSpaces, 2);
    }

    private static int getRemainingSpaces(String[] words, int i, int j, int width) {
        int totalLength = 0;
        for (int k = i; k <= j; k++) {
            totalLength += words[k].length();
        }

        totalLength += (j - i); // add spaces in between

        int remainingSpaces = width - totalLength;
        if (remainingSpaces < 0) {
            remainingSpaces = Integer.MAX_VALUE;
        }

        return remainingSpaces;
    }
}
