package com.prince.algo;

/**
 * https://www.geeksforgeeks.org/find-the-minimum-cost-to-reach-a-destination-where-every-station-is-connected-in-one-
 * direction/
 *
 * @author Prince Raj
 */
public class MinCostTrain {

    private static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // There are 4 stations and cost[i][j] indicates cost to reach j from i. The entries where j < i are
        // meaningless.
        int[][] cost = { {0, 15, 80, 90}, {INF, 0, 40, 50}, {INF, INF, 0, 70}, {INF, INF, INF, 0}};

        System.out.println(minCostRecursive(cost, 0, cost[0].length - 1));
        System.out.println(minCostIterative(cost));
    }

    private static int minCostRecursive(int[][] cost, int s, int e) {
        if (s == e || s + 1 == e) {
            return cost[s][e];
        }

        int minCost = cost[s][e];
        for (int i = s + 1; i < e; i++) {
            minCost = Math.min(minCost, minCostRecursive(cost, s, i) + minCostRecursive(cost, i, e));
        }

        return minCost;
    }

    private static int minCostIterative(int[][] cost) {
        int n = cost[0].length;
        int[] dist = new int[n];

        for (int i = 1; i < n; i++) {
            dist[i] = INF;
        }

        dist[0] = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (dist[j] > dist[i] + cost[i][j]) {
                    dist[j] = dist[i] + cost[i][j];
                }
            }
        }

        return dist[n - 1];
    }
}
