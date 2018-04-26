package com.prince.algo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.mutable.MutableInt;

/**
 * Given a string S, find the longest substring in S with k distinct characters in complexity O(n).
 *
 * Input: S = "AABACDBBA"
 *
 * Output: "AABA"
 *
 * @author Prince Raj
 */
public class LongestSubstringKDistinct {

    public static void main(String[] args) {
        printLongestSubstringKDistinctWithRepeating();

        printLongestSubstringKDistinctWithoutRepeating();
    }

    private static void printLongestSubstringKDistinctWithRepeating() {
        String s = "AABACDBBA";
        int k = 2; // k distinct characters in substring
        printLongestSubstringKDistinctWithRepeating(s, k);

        s = "AABACDBBBBA";
        k = 2;
        printLongestSubstringKDistinctWithRepeating(s, k);

        s = "AABABCDBBBBA";
        k = 3;
        printLongestSubstringKDistinctWithRepeating(s, k);

        s = "AABABCDBBBBA";
        k = 1;
        printLongestSubstringKDistinctWithRepeating(s, k);

        s = "AABABCDBBBBA";
        k = 5;
        printLongestSubstringKDistinctWithRepeating(s, k);
    }

    private static void printLongestSubstringKDistinctWithoutRepeating() {
        String s = "AABACDBBA";
        int k = 3; // k distinct characters in substring but non repeated
        printLongestSubstringKDistinctWithoutRepeating(s, k);

        s = "AABABCDBBBBA";
        k = 5;
        printLongestSubstringKDistinctWithoutRepeating(s, k);
    }

    private static void printLongestSubstringKDistinctWithRepeating(String s, int k) {
        int begin = 0;
        int end = 0;
        int head = 0;
        int d = Integer.MIN_VALUE;

        Map<Character, MutableInt> map = new HashMap<>();

        while (end < s.length()) {
            char c = s.charAt(end++);
            MutableInt count = map.get(c);
            if (count == null) {
                count = new MutableInt(0);
                map.put(c, count);
            }
            count.increment();

            int size = map.size();
            if (size >= k) {
                if (size == k) {
                    if (d < end - begin) {
                        d = end - begin;
                        head = begin;
                    }
                } else {
                    while (size != k) {
                        c = s.charAt(begin++);
                        count = map.get(c);
                        count.decrement();
                        if (count.intValue() == 0) {
                            map.remove(c);
                            size = map.size();
                        }
                    }
                }
            }
        }

        String longestSubstring = (d == Integer.MIN_VALUE) ? "" : s.substring(head, head + d);
        System.out.println("(Repeated) Longest Substring with At Most "
                + k
                + " Distinct Characters for "
                + s
                + ": "
                + longestSubstring);
    }

    private static void printLongestSubstringKDistinctWithoutRepeating(String s, int k) {
        int begin = 0;
        int end = 0;
        int head = 0;
        int d = Integer.MIN_VALUE;

        Set<Character> set = new HashSet<>();

        while (end < s.length()) {
            char c = s.charAt(end++);
            if (set.contains(c)) {
                begin++;
            } else {
                set.add(c);
            }

            int size = set.size();
            if (size >= k) {
                if (size == k) {
                    if (d < end - begin) {
                        d = end - begin;
                        head = begin;
                    }
                } else {
                    c = s.charAt(begin++);
                    set.remove(c);
                }
            }
        }

        String longestSubstring = (d == Integer.MIN_VALUE) ? "" : s.substring(head, head + d);
        System.out.println("(Non Repeated) Longest Substring with At Most "
                + k
                + " Distinct Characters for "
                + s
                + ": "
                + longestSubstring);
    }
}
