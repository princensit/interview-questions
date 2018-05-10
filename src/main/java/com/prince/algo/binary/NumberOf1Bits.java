package com.prince.algo.binary;

/**
 * @author Prince Raj
 */
public class NumberOf1Bits {

    public static void main(String[] args) {
        System.out.println(getNumberOf1Bits(1));
        System.out.println(getNumberOf1Bits(2));
        System.out.println(getNumberOf1Bits(3));
        System.out.println(getNumberOf1Bits(5));

        System.out.println(getNumberOf1BitsApproach2(1));
        System.out.println(getNumberOf1BitsApproach2(2));
        System.out.println(getNumberOf1BitsApproach2(3));
        System.out.println(getNumberOf1BitsApproach2(5));
    }

    // Time Complexity - O(logn)
    private static int getNumberOf1Bits(int n) {
        int count = 0;

        while (n != 0) {
            if ((n & 1) == 1) {
                count++;
            }

            n >>= 1;
        }

        return count;
    }

    // Brian Kernighan’s Algorithm (Time Complexity - O(logn))
    private static int getNumberOf1BitsApproach2(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }

        return count;
    }
}
