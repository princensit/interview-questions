package com.prince.algo;

/**
 * https://www.youtube.com/watch?v=3hcaVyX00_4
 *
 * Suppose a building has n floors. If we have m eggs, find the minimum number of drops needed to determine from which
 * floors it is safe to drop an egg.
 *
 * @author Prince Raj
 */
public class EggDropping {

    public static void main(String[] args) {

        System.out.println("Minimum attempts: " + calculateRecursive(3, 100));
    }

    /**
     * <pre>
     * i -> eggs
     * j -> floors
     *
     * if (i > j) {
     *   T[i][j] = T[i-1][j];
     * } else {
     *   T[i][j] = 1 + Min( Max(T[i-1][k-1], T[i][j-k]);
     *             where 1 < k < j
     * }
     * </pre>
     *
     * @param floors
     * @param eggs
     * @return
     */
    private static int calculateRecursive(int floors, int eggs) {
        if (eggs == 1) {
            return floors;
        }

        if (floors == 0) {
            return 0;
        }

        int min = 1000;
        for (int i = 1; i <= floors; i++) {
            int val = 1 + Math.max(calculateRecursive(eggs - 1, i - 1), calculateRecursive(eggs, floors - i));
            if (val < min) {
                min = val;
            }
        }

        return min;
    }
}
