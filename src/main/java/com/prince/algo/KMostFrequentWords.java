package com.prince.algo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import lombok.Data;

/**
 * http://www.geeksforgeeks.org/find-the-k-most-frequent-words-from-a-file/
 *
 * @author Prince Raj
 */
public class KMostFrequentWords {

    private static Map<String, Word> wordFrequency = new HashMap<>();

    private static PriorityQueue<Word> minHeap = new PriorityQueue<>(10, new Comparator<Word>() {

        @Override
        public int compare(Word o1, Word o2) {
            return o1.frequency.compareTo(o2.frequency);
        }
    });

    public static void main(String[] args) {
        String[] words = getWords();
        int k = 5;

        for (String word : words) {
            Word nw = wordFrequency.get(word);
            if (nw == null) {
                nw = new Word(word);
                nw.setFrequency(0);
                wordFrequency.put(word, nw);
            }
            nw.setFrequency(nw.getFrequency() + 1);

            if (minHeap.contains(nw)) {
                minHeap.remove(nw);
                minHeap.add(nw);
            } else {
                int size = minHeap.size();
                if (size == k) {
                    Word w = minHeap.peek();
                    if (w.frequency < nw.getFrequency()) {
                        minHeap.remove(w);
                        minHeap.add(nw);
                    }
                } else {
                    minHeap.add(nw);
                }
            }
        }

        System.out.println(minHeap);
    }

    private static String[] getWords() {
        String text =
                "Welcome to the world of Geeks This portal has been created to provide well written well thought and well explained solutions for selected questions If you like Geeks for Geeks and would like to contribute here is your chance You can write article and mail your article to contribute at geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help thousands of other Geeks";
        return text.split("\\s+");
    }

    @Data
    private static final class Word {

        private final String word;

        private Integer frequency;

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (o == null || getClass() != o.getClass())
                return false;
            if (!super.equals(o))
                return false;

            Word word1 = (Word)o;

            return word != null ? word.equals(word1.word) : word1.word == null;
        }

        @Override
        public int hashCode() {
            int result = super.hashCode();
            result = 31 * result + (word != null ? word.hashCode() : 0);
            return result;
        }
    }
}
