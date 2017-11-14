package com.prince.benchmark;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Prince Raj
 */
@Slf4j
public class MaskString {

    private static final String ID1 = "de77a30d-4c58-45e1-9fe1-f687103bebad";

    private static final String ID2 = "c2Q6djE6c2RNb25leToxNDc0MDA3NDo0dC01emlOVFJ1ZWFadFVwLUZUN2NR";

    private static final String ID3 =
            "YzJRNmRqRTZjMlJOYjI1bGVUb3hORGMwTURBM05EbzBkQzAxZW1sT1ZGSjFaV0ZhZEZWd0xVWlVOMk5SOjE";

    private static final String REGEX = "(?<!^.?.?.?).(?!.?.?.?$)";

    private static final int ITERATIONS1 = 100000;

    private static final int ITERATIONS2 = 10000000;

    public static void main(String[] args) {
        maskStringUsingRegex();
        maskStringUsingArrayFilling();
    }

    private static void maskStringUsingRegex() {
        regexBenchmark(ID1, ITERATIONS1);
        regexBenchmark(ID1, ITERATIONS2);

        regexBenchmark(ID2, ITERATIONS1);
        regexBenchmark(ID2, ITERATIONS2);

        regexBenchmark(ID3, ITERATIONS1);
        regexBenchmark(ID3, ITERATIONS2);
    }

    private static void maskStringUsingArrayFilling() {
        arrayFillingBenchmark(ID1, ITERATIONS1);
        arrayFillingBenchmark(ID1, ITERATIONS2);

        arrayFillingBenchmark(ID2, ITERATIONS1);
        arrayFillingBenchmark(ID2, ITERATIONS2);

        arrayFillingBenchmark(ID3, ITERATIONS1);
        arrayFillingBenchmark(ID3, ITERATIONS2);
    }

    private static void regexBenchmark(String id, int iterations) {
        long start;
        long end;

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            id.replaceAll(REGEX, "X");
        }
        end = System.currentTimeMillis();

        System.out.printf(id.replaceAll(REGEX, "X"));

        System.out.println("Regex benchmark => ID: "
                + id
                + ", iterations: "
                + iterations
                + ", time: "
                + (end - start)
                + " ms");
    }

    private static void arrayFillingBenchmark(String id, int iterations) {
        long start;
        long end;
        int length = id.length();
        char[] buff = new char[0];

        start = System.currentTimeMillis();
        for (int i = 0; i < iterations; i++) {
            if (length > 8) {
                buff = id.toCharArray();
                Arrays.fill(buff, 4, length - 4, 'X');
            }
        }
        end = System.currentTimeMillis();

        System.out.printf(String.valueOf(buff));

        System.out.println("Array filling benchmark => ID: "
                + id
                + ", iterations: "
                + iterations
                + ", time: "
                + (end - start)
                + " ms");
    }
}
