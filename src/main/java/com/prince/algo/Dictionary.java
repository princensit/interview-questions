package com.prince.algo;

import java.util.Arrays;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class Dictionary {

    private static final int MAX_CHARS = 26;

    private final TrieNode root = new TrieNode();

    public static void main(String[] args) {
        Dictionary dict = new Dictionary();
        dict.addWord("word");
        dict.addWord("world");
        dict.addWord("hello");
        dict.addWord("hi");
        dict.addWord("hike");
        dict.printWords();

        System.out.println("########");
        System.out.println(dict.containsWord("hi"));
        System.out.println(dict.containsWord("hint"));

        dict.wordWithPrefix("hi");
        dict.wordWithPrefix("hil");
        dict.wordWithPrefix("hik");
    }

    public void addWord(String word) {
        word = word.toLowerCase();
        char[] arr = word.toCharArray();
        TrieNode node = root;
        for (char c : arr) {
            int index = getIndex(c);
            if (node.children[index] == null) {
                node.children[index] = new TrieNode();
            }
            node = node.children[index];
        }
        node.setEnd(true);
    }

    public void wordWithPrefix(String prefix) {
        prefix = prefix.toLowerCase();
        char[] arr = prefix.toCharArray();

        TrieNode node = root;
        for (char c : arr) {
            int index = getIndex(c);
            node = node.children[index];
            if (node == null) {
                break;
            }
        }

        if (node == null) {
            System.out.println("Word with prefix: " + prefix + " not found");
        } else {
            char[] nextWord = Arrays.copyOf(arr, MAX_CHARS);
            int index = arr.length;
            while (!node.isEnd) {
                for (int i = 0; i < MAX_CHARS; i++) {
                    TrieNode child = node.children[i];
                    if (child != null) {
                        nextWord[index++] = getChar(i);
                        node = child;
                        break;
                    }
                }
            }

            System.out.print("Word with prefix: " + prefix + " found is ");
            for (int i = 0; i < index; i++) {
                System.out.print(nextWord[i]);
            }
            System.out.println();
        }
    }

    public boolean containsWord(String word) {
        TrieNode node = getNode(word);
        return node != null;
    }

    public void printWords() {
        // Assuming max length of word is 26
        char[] arr = new char[MAX_CHARS];
        printWord(root, arr, 0);
    }

    private void printWord(TrieNode node, char[] arr, int index) {
        for (int i = 0; i < MAX_CHARS; i++) {
            TrieNode child = node.children[i];
            if (child != null) {
                arr[index] = getChar(i);
                if (child.isEnd) {
                    printWord(arr, index);
                    System.out.println();
                }
                printWord(child, arr, index + 1);
            }
        }
    }

    private void printWord(char[] arr, int index) {
        for (int i = 0; i <= index; i++) {
            System.out.print(arr[i]);
        }
    }

    private TrieNode getNode(String word) {
        word = word.toLowerCase();
        char[] arr = word.toCharArray();

        TrieNode node = root;
        for (char c : arr) {
            int index = getIndex(c);
            node = node.children[index];
            if (node == null) {
                break;
            }
        }

        return node;
    }

    private int getIndex(char c) {
        return c - 'a';
    }

    private char getChar(int index) {
        return (char) (index + 97);
    }

    private static String[] getWords() {
        String text =
                "Welcome to the world of Geeks This portal has been created to provide well written well thought and well explained solutions for selected questions If you like Geeks for Geeks and would like to contribute here is your chance You can write article and mail your article to contribute at geeksforgeeks org See your article appearing on the Geeks for Geeks main page and help thousands of other Geeks";
        return text.split("\\s+");
    }

    @Data
    private static final class TrieNode {

        private boolean isEnd;

        private TrieNode[] children = new TrieNode[MAX_CHARS];
    }
}
