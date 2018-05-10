package com.prince.algo.binary;

/**
 * @author Prince Raj
 */
public class SumOf2Integers {

    public static void main(String[] args) {
        System.out.println(getSum(2, 3));
        System.out.println(getSum(2, 13));
        System.out.println(getSum(12, 13));
    }

    private static int getSum(int a, int b) {
        while (b != 0) {
            int carry = a & b;

            a = a ^ b;

            b = carry << 1;
        }

        return a;
    }
}
