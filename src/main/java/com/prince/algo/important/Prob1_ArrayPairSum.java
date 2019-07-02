package com.prince.algo.important;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * http://www.ardendertat.com/2011/09/17/programming-interview-questions-1-array-pair-sum/
 *
 * Given an integer array, output all pairs that sum up to a specific value k.
 *
 * @author Prince Raj
 */
public class Prob1_ArrayPairSum {

    public static void main(String[] args) {
        int[] arr = {0, 1, 2, 5, 7, 6, 4, 3, 1};
        int k = 5;

        Set<Integer> seen = new HashSet<>();
        Set<Pair> output = new HashSet<>();
        for (int x : arr) {
            if (seen.contains(k - x)) {

                Pair pair = new Pair();
                pair.setVal1(Math.min(x, k - x));
                pair.setVal2(Math.max(x, k - x));

                output.add(pair);
            } else {
                seen.add(x);
            }
        }

        for (Pair p : output) {
            System.out.println(p.getVal1() + ", " + p.getVal2());
        }
    }

    @Data
    private static final class Pair {
        int val1;
        int val2;
    }
}
