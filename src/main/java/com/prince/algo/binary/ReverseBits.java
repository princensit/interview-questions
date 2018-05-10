package com.prince.algo.binary;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Prince Raj
 */
public class ReverseBits {

    private static final Map<Byte, Integer> CACHE = new HashMap<>();

    public static void main(String[] args) {
        int n = 43261596;

        System.out.println(reverseBits(n));

        // How to optimize if this function is called multiple times? We can divide an int into 4 bytes, and reverse
        // each byte then combine into an int. For each byte, we can use cache to improve performance.
        System.out.println(reverseBitsOptimized(n));
    }

    // Time Complexity: O(log n)
    private static int reverseBits(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            result += n & 1;
            n >>>= 1; // CATCH: must do unsigned shift
            if (i < 31) { // CATCH: for last digit, don't shift!
                result <<= 1;
            }
        }

        return result;
    }

    private static int reverseBitsOptimized(int n) {
        byte[] bytes = new byte[4];
        for (int i = 0; i < 4; i++) {
            // convert int into 4 bytes
            bytes[i] = (byte)((n >>> 8 * i) & 0xFF);
        }

        int result = 0;
        for (int i = 0; i < 4; i++) {
            result += reverseByte(bytes[i]); // reverse per byte
            if (i < 3) {
                result <<= 8;
            }
        }
        return result;
    }

    private static int reverseByte(byte b) {
        Integer value = CACHE.get(b); // first look up from cache
        if (value == null) {
            value = 0;
            // reverse by bit
            for (int i = 0; i < 8; i++) {
                value += ((b >>> i) & 1);
                if (i < 7) {
                    value <<= 1;
                }
            }

            CACHE.put(b, value);
        }

        return value;
    }
}
