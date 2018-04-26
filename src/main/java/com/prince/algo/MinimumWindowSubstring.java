package com.prince.algo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.mutable.MutableInt;

/**
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in
 * complexity O(n).
 *
 * Input: S = "ADOBECODEBANC", T = "ABC"
 *
 * Output: "BANC"
 *
 * @author Prince Raj
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        printMinWindowSubstring(s, t);

        s = "ADOBECODEBANC";
        t = "AB";
        printMinWindowSubstring(s, t);

        s = "ADOBECODEBANC";
        t = "DB";
        printMinWindowSubstring(s, t);
    }

    private static void printMinWindowSubstring(String s, String t) {
        Map<Character, MutableInt> map = new HashMap<>();
        for (char c : t.toCharArray()) { // initialize the hashmap
            MutableInt count = map.get(c);
            if (count == null) {
                count = new MutableInt(0);
                map.put(c, count);
            }

            count.increment();
        }

        int counter = t.length(); // check whether the substring is valid
        int begin = 0;
        int end = 0;
        int head = 0;
        int d = Integer.MAX_VALUE; // the length of substring

        while (end < s.length()) {
            MutableInt count = map.get(s.charAt(end++));
            if (count != null) {
                if (count.intValue() > 0) {
                    counter--; // modify counter here
                }

                count.decrement();
            }

            while (counter == 0) { // match found
                if (end - begin < d) { // update d here if minimum found
                    d = end - begin;
                    head = begin;
                }

                count = map.get(s.charAt(begin++)); // increase begin to make it invalid/valid again
                if (count != null) {
                    if (count.intValue() == 0) {
                        counter++; // make it invalid
                    }

                    count.increment();
                }
            }
        }

        String minSubstring = (d == Integer.MAX_VALUE) ? "" : s.substring(head, head + d);
        System.out.println("Minimum window substring for " + t + " in " + s + ": " + minSubstring);
    }
}
