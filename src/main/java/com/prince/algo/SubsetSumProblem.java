package com.prince.algo;

/**
 * https://www.youtube.com/watch?v=s6FhG--P7z0
 *
 * @author Prince Raj
 */
public class SubsetSumProblem {

    public static void main(String[] args) {

    }

    /**
     * <pre>
     * i -> sets
     * j -> 1 to sum
     *
     * if (j < input[i]) {
     *   T[i][j] = T[i-1][j]
     * } else {
     *   T[i][j] = T[i-1][j] || T[i-1][j-input[i]]
     * }
     * </pre>
     *
     * @return
     */
    private static boolean isSubsetSum() {
        return true;
    }
}
