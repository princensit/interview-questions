package com.prince.algo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author Prince Raj
 */
public class DictionaryKMostFrequentWords {

    private final int k;

    private final Map<String, Integer> wordCountMap = new HashMap<>();

    private final Set<String> wordsInHeap = new HashSet<>();

    private final PriorityQueue<String> minHeap = new PriorityQueue<>(10, new Comparator<String>() {

        @Override
        public int compare(String o1, String o2) {
            Integer count1 = wordCountMap.get(o1);
            Integer count2 = wordCountMap.get(o2);
            return count1.compareTo(count2);
        }
    });

    private DictionaryKMostFrequentWords(int k) {
        this.k = k;
    }

    public static void main(String[] args) {
        int k = 5;
        DictionaryKMostFrequentWords dictionary = new DictionaryKMostFrequentWords(k);

        String[] words = getWords();

        for (String word : words) {
            dictionary.addWord(word);
        }

        Map<String, Integer> frequentWordCountMap = dictionary.getKMostFrequentWords();

        if (frequentWordCountMap == null) {
            System.out.println("# of words are less than " + k + " size");
        } else {
            for (Map.Entry<String, Integer> entry : frequentWordCountMap.entrySet()) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
        }
    }

    private void addWord(String word) {
        Integer count = wordCountMap.get(word);
        if (count == null) {
            count = 1;
        } else {
            count = count + 1;
        }
        wordCountMap.put(word, count);

        int size = minHeap.size();
        if (size == k) {
            if (!wordsInHeap.contains(word)) {
                String minWord = minHeap.peek();
                int minWordCount = wordCountMap.get(minWord);
                if (minWordCount < count) {
                    minHeap.remove(minWord);
                    minHeap.add(word);

                    wordsInHeap.remove(minWord);
                    wordsInHeap.add(word);
                }
            } else {
                minHeap.remove(word);
                minHeap.add(word);
            }
        } else {
            minHeap.add(word);
            wordsInHeap.add(word);
        }
    }

    private Map<String, Integer> getKMostFrequentWords() {
        int size = minHeap.size();

        final Map<String, Integer> frequentWordCountMap;
        if (size < k) {
            frequentWordCountMap = null;
        } else {
            String[] words = minHeap.toArray(new String[size]);
            frequentWordCountMap = new LinkedHashMap<>();
            for (String word : words) {
                frequentWordCountMap.put(word, wordCountMap.get(word));
            }
        }

        return frequentWordCountMap;
    }

    private static String[] getWords() {
        return new String[] {
                "Welcome",
                "to",
                "the",
                "world",
                "of",
                "Geeks",
                "This",
                "portal",
                "has",
                "been",
                "created",
                "to",
                "provide",
                "well",
                "written",
                "well",
                "thought",
                "and",
                "well",
                "explained",
                "solutions",
                "for",
                "selected",
                "questions",
                "If",
                "you",
                "like",
                "Geeks",
                "for",
                "Geeks",
                "and",
                "would",
                "like",
                "to",
                "contribute",
                "here",
                "is",
                "your",
                "chance",
                "You",
                "can",
                "write",
                "article",
                "and",
                "mail",
                "your",
                "article",
                "to",
                "contribute",
                "at",
                "geeksforgeeks",
                "org",
                "See",
                "your",
                "article",
                "appearing",
                "on",
                "the",
                "Geeks",
                "for",
                "Geeks",
                "main",
                "page",
                "and",
                "help",
                "thousands",
                "of",
                "other",
                "Geeks"};
    }
}
