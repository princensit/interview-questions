package com.prince.stream;

import java.util.Arrays;
import java.util.Random;

/**
 * http://www.geeksforgeeks.org/reservoir-sampling/
 *
 * @author Prince Raj
 */
public class ReservoirSampling {

    private final Random random = new Random();

    private final int[] reservoir;

    private final int k;

    private int index = 0;

    private int count = 0;

    private ReservoirSampling(int k) {
        this.reservoir = new int[k];
        this.k = k;
    }

    private void selectKElements(int n) {
        count++;
        if (index < k) {
            reservoir[index++] = n;
            if (index == k) {
                System.out.println(Arrays.toString(reservoir));
            }
        } else {
            int rand = random.nextInt(count);
            if (rand < k) {
                reservoir[rand] = n;
            }

            System.out.println(Arrays.toString(reservoir));
        }
    }

    public static void main(String[] args) {
        int k = 5;
        ReservoirSampling sampling = new ReservoirSampling(5);

        int stream[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};

        for (int e : stream) {
            sampling.selectKElements(e);
        }
    }
}
