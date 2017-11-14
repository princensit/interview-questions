package com.prince.algo.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/**
 * Find the smallest window in a string containing all characters of another string
 *
 * Input : string = "this is a test string", pattern = "tist"
 *
 * Output : Minimum window is "t stri"
 *
 * @author Prince Raj
 */
public class SmallestWindowForGivenElements {

    private static Map<Character, Integer> patternMap = new HashMap<>();

    public static void main(String[] args) {
        char[] arr = "hello this is a test string".toCharArray();
        char[] patternArr = "tist".toCharArray();

        for (char c : patternArr) {
            Integer count = patternMap.get(c);
            if (count == null) {
                count = 0;
            }
            count++;

            patternMap.put(c, count);
        }

        int smallestWindow = Integer.MAX_VALUE;

        for (int i = 0; i < arr.length; i++) {
            while (i < arr.length && !patternMap.containsKey(arr[i])) {
                i++;
            }

            Map<Character, Integer> tempPatternMap = new HashMap<>(patternMap);

            for (int j = i; j < arr.length; j++) {
                char c = arr[j];
                Integer count = tempPatternMap.get(c);
                if (count != null) {
                    count--;

                    if (count == 0) {
                        tempPatternMap.remove(c);
                    } else {
                        tempPatternMap.put(c, count);
                    }
                }

                if (tempPatternMap.isEmpty()) {
                    if (smallestWindow > j - i + 1) {
                        smallestWindow = j - i + 1;
                    }
                }
            }
        }

        System.out.println("####### smallest window: " + smallestWindow);
    }
}
