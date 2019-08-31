package com.prince.algo;

import java.util.Arrays;

/**
 * TODO fix me!
 *
 * @author Prince Raj
 */
public class RollingAverage {
    private float average = 0.0f;
    private final int windowSize;
    private float[] numbers;
    private int count = 0;
    private float sum = 0;
    private int index = 0;
    private int k = 0;

    public RollingAverage(int windowSize) {
        this.windowSize = windowSize;
        this.numbers = new float[windowSize];
    }

    public float getRollingAverage() {
        return this.average;
    }

    public void next(float number) {
        if (count <= windowSize) {
            sum += number;
            count++;
            numbers[index++] = number;
            average = sum / count;
        } else {
            k = k % windowSize;
            float previousNumber = numbers[k];
            sum = sum - previousNumber + number;
            average = sum / count;
            numbers[k] = number;
            k++;
        }
    }

    static class RollingAverage2 {
        private float average = 0.0f;
        private final int windowSize;
        private float[] numbers;
        private int count = 0;
        private float sum = 0;
        private int index = 0;
        private int k = 0;
        private int lowerPercentage;
        private int higherPercentage;

        public RollingAverage2(int windowSize, int lowerPercentage, int higherPercentage) {
            // TODO add validations for numbers should >= 0
            this.windowSize = windowSize;
            this.lowerPercentage = lowerPercentage;
            this.higherPercentage = higherPercentage;

            // TODO add validations that sum of lowerPercentage and higherPercentage <= 100%
            this.numbers = new float[windowSize];
        }

        public float getRollingAverage() {
            return this.average;
        }

        public void next(float number) {
            if (count <= windowSize) {
                sum += number;
                count++;
                numbers[index++] = number;
                average = sum / count;
                reCalculateAverage(count);
            } else {
                k = k % windowSize;
                numbers[k] = number;
                k++;

                reCalculateAverage(windowSize);
            }
        }

        private void reCalculateAverage(int windowSize) {
            float[] copiedNumbers = numbers.clone();
            Arrays.sort(copiedNumbers);

            int lowerElementsToRemove = lowerPercentage * windowSize;
            int higherElementsToRemove = higherPercentage * windowSize;
            int upperIndex = windowSize - higherElementsToRemove;

            float sum = 0.0f;
            for (int i = lowerElementsToRemove; i < upperIndex; i++) {
                sum += copiedNumbers[i];
            }

            this.average = sum / (windowSize - lowerElementsToRemove - higherElementsToRemove);
        }
    }
}
