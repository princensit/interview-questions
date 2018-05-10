package com.prince.algo.binary;

import java.util.Arrays;

/**
 * @author Prince Raj
 */
public class CountingBits {

    // f[i] = f[i / 2] + i % 2
    public static void main(String[] args) {
        getCountingBits(1);
        getCountingBits(2);
        getCountingBits(3);
        getCountingBits(5);
    }

    private static void getCountingBits(int num) {
        int[] f = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            f[i] = f[i >> 1] + (i & 1);
        }

        System.out.println(Arrays.toString(f));
    }
}
