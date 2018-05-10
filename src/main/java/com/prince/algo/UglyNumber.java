package com.prince.algo;

/**
 * The number that contains only factors 2, 3, and 5 is called an Ugly Number. For example, 6 and 8 are ugly numbers,
 * but 14 is not because it contains a factor of 7. Traditionally, we regard 1 as the first ugly number.
 *
 * @author Prince Raj
 */
public class UglyNumber {

    public static void main(String[] args) {
        int n = 150;

        System.out.println("Nth (" + n + ") ugly number (unoptimized): " + getNthUglyOptimized(n));
        System.out.println("Nth (" + n + ") ugly number (optimized): " + getNthUglyUnOptimized(n));

        // super ugly number (here prime factors can be anything)
        n = 9;
        int[] primes = {3, 5, 7, 11, 13}; // sorted
        System.out.println("Nth (" + n + ") super ugly number (optimized): " + getNthSuperUglyOptimized(n, primes));
    }

    /**
     * <pre>
     * initialize
     * ugly[] =  | 1 |
     * i2 =  i3 = i5 = 0;
     *
     * First iteration
     * ugly[1] = Min(ugly[i2]*2, ugly[i3]*3, ugly[i5]*5)
     * = Min(2, 3, 5)
     * = 2
     * ugly[] =  | 1 | 2 |
     * i2 = 1,  i3 = i5 = 0  (i2 got incremented )
     *
     * Second iteration
     * ugly[2] = Min(ugly[i2]*2, ugly[i3]*3, ugly[i5]*5)
     * = Min(4, 3, 5)
     * = 3
     * ugly[] =  | 1 | 2 | 3 |
     * i2 = 1,  i3 =  1, i5 = 0  (i3 got incremented )
     *
     * Third iteration
     * ugly[3] = Min(ugly[i2]*2, ugly[i3]*3, ugly[i5]*5)
     * = Min(4, 6, 5)
     * = 4
     * ugly[] =  | 1 | 2 | 3 |  4 |
     * i2 = 2,  i3 =  1, i5 = 0  (i2 got incremented )
     *
     * Fourth iteration
     * ugly[4] = Min(ugly[i2]*2, ugly[i3]*3, ugly[i5]*5)
     * = Min(6, 6, 5)
     * = 5
     * ugly[] =  | 1 | 2 | 3 |  4 | 5 |
     * i2 = 2,  i3 =  1, i5 = 1  (i5 got incremented )
     *
     * Fifth iteration
     * ugly[4] = Min(ugly[i2]*2, ugly[i3]*3, ugly[i5]*5)
     * = Min(6, 6, 10)
     * = 6
     * ugly[] =  | 1 | 2 | 3 |  4 | 5 | 6 |
     * i2 = 3,  i3 =  2, i5 = 1  (i2 and i3 got incremented )
     *
     * Will continue same way till I < 150
     * </pre>
     *
     * @param n nth ugly number
     * @return ugly number
     */
    private static int getNthUglyOptimized(int n) {
        int[] arr = new int[n];

        arr[0] = 1;

        int i2Index = 0;
        int i3Index = 0;
        int i5Index = 0;

        int i = 1;
        while (i < n) {
            int min = getMinimum(arr[i2Index] * 2, arr[i3Index] * 3, arr[i5Index] * 5);
            arr[i] = min;

            if (min == arr[i2Index] * 2) {
                i2Index++;
            }
            if (min == arr[i3Index] * 3) {
                i3Index++;
            }
            if (min == arr[i5Index] * 5) {
                i5Index++;
            }

            i++;
        }

        return arr[n - 1];
    }

    private static int getNthUglyUnOptimized(int n) {
        int count = 1;
        int i = 1;
        while (n > count) {
            i++;
            if (isUgly(i)) {
                count++;
            }
        }

        return i;
    }

    private static int getNthSuperUglyOptimized(int n, int[] primes) {
        int[] arr = new int[n];
        arr[0] = 1;

        int[] indexes = new int[primes.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = 0;
        }

        int i = 1;
        while (i < n) {
            int min = getMinimum(arr, indexes, primes);
            arr[i] = min;

            for (int j = 0; j < indexes.length; j++) {
                if (min == arr[indexes[j]] * primes[j]) {
                    indexes[j]++;
                }
            }

            i++;
        }

        return arr[n - 1];
    }

    private static boolean isUgly(int num) {
        num = getMaxDivide(num, 2);
        num = getMaxDivide(num, 3);
        num = getMaxDivide(num, 5);

        return num == 1;
    }

    private static int getMaxDivide(int a, int b) {
        while (a % b == 0) {
            a /= b;
        }

        return a;
    }

    private static int getMinimum(int... args) {
        int min = args[0];
        for (int arg : args) {
            if (arg < min) {
                min = arg;
            }
        }

        return min;
    }

    private static int getMinimum(int[] arr, int[] indexes, int[] primes) {
        int min = arr[indexes[0]] * primes[0];
        for (int j = 1; j < indexes.length; j++) {
            if (arr[indexes[j]] * primes[j] < min) {
                min = arr[indexes[j]] * primes[j];
            }
        }

        return min;
    }
}
