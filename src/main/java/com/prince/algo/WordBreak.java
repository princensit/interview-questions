package com.prince.algo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * https://www.youtube.com/watch?v=WepWFGxiwRs
 *
 * @author Prince Raj
 */
public class WordBreak {

    public static void main(String[] args) {
        Set<String> dictionary = getDictionary();
        String str = "iamace";

        System.out.println("Is word break: " + isWordsBreakInDictionary(str, dictionary));
    }

    private static int isWordsBreakInDictionary(String str, Set<String> dictionary) {
        int[][] matrix = new int[str.length()][str.length()];

        // check one character, then 2 characters, then 3 characters, and so on i a dictionary
        for (int k = 1; k <= str.length(); k++) {
            initializeMatrix(matrix, dictionary, str, k);
        }

        for (int[] aMatrix : matrix) {
            System.out.println(Arrays.toString(aMatrix));
        }

        return matrix[0][str.length() - 1];
    }

    private static void initializeMatrix(int[][] matrix, Set<String> dictionary, String str, int size) {
        for (int i = 0; i < str.length(); i++) {
            int effectiveEndIndex = (i + size) > str.length() ? str.length() : i + size;
            String subStr = str.substring(i, effectiveEndIndex);
            if (dictionary.contains(subStr)) {
                matrix[i][effectiveEndIndex - 1] = 1;
            } else {
                // split the string and check if any substring was in the dictionary
                int subStrLength = subStr.length();
                if (subStrLength >= 2) {
                    for (int k = 0; k < subStrLength - 1; k++) {
                        if ((matrix[i][i + k] == 1 && matrix[i + k + 1][effectiveEndIndex - 1] == 1)) {
                            matrix[i][effectiveEndIndex - 1] = 1;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static Set<String> getDictionary() {
        Set<String> dictionary = new HashSet<>();
        dictionary.add("i");
        dictionary.add("a");
        dictionary.add("am");
        dictionary.add("ace");

        return dictionary;
    }
}
