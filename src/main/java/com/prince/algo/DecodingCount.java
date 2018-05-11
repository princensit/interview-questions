package com.prince.algo;

/**
 * @author Prince Raj
 */
public class DecodingCount {

    public static void main(String[] args) {
        char digits[] = {'1', '2', '3', '4'};
        int n = digits.length;
        System.out.println("Count is " + countDecoding(digits, n));
    }

    private static int countDecoding(char[] digits, int n) {
        if (n == 0 || n == 1) {
            return 1;
        }

        int count = 0;

        if (digits[n - 1] > '0') {
            count = countDecoding(digits, n - 1);
        }

        if (digits[n - 2] == '1' || (digits[n - 2] == '2' && digits[n - 1] < '7')) {
            count += countDecoding(digits, n - 2);
        }

        return count;
    }
}
