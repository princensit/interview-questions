package com.prince.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Prince Raj
 */
public class StreamMedian {

    private final PriorityQueue<Integer> maxHeap = new PriorityQueue<>(10, new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });

    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>(10, new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    });

    private float getMedian(int n) {
        int maxHeapSize = maxHeap.size();
        int minHeapSize = minHeap.size();

        final float median;
        if (maxHeapSize == 0 && minHeapSize == 0) {
            minHeap.add(n);
            median = n;
        } else {
            float effectiveMean;
            if (maxHeapSize == minHeapSize) {
                effectiveMean = (maxHeap.peek() + minHeap.peek()) / 2;
                if (n <= effectiveMean) {
                    maxHeap.add(n);
                    median = maxHeap.peek();
                } else {
                    minHeap.add(n);
                    median = minHeap.peek();
                }
            } else {
                if (maxHeapSize < minHeapSize) {
                    effectiveMean = minHeap.peek();
                    if (n <= effectiveMean) {
                        maxHeap.add(n);
                    } else {
                        int minHeapTop = minHeap.peek();
                        minHeap.remove(minHeapTop);
                        minHeap.add(n);

                        maxHeap.add(minHeapTop);
                    }
                } else {
                    effectiveMean = maxHeap.peek();
                    if (n <= effectiveMean) {
                        int maxHeapTop = maxHeap.peek();
                        maxHeap.remove(maxHeapTop);
                        maxHeap.add(n);

                        minHeap.add(maxHeapTop);
                    } else {
                        minHeap.add(n);
                    }

                }
                median = (float)(maxHeap.peek() + minHeap.peek()) / 2;
            }
        }

        return median;
    }

    public static void main(String[] args) throws IOException {
        boolean stdIn = true;

        StreamMedian stream = new StreamMedian();

        if (stdIn) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while ((s = br.readLine()) != null) {
                if ("done".equals(s)) {
                    break;
                }
                System.out.println("Current median: " + stream.getMedian(Integer.parseInt(s)));
            }
        } else {
            int[] arr = {5, 1, 2, 3, 4, 5};

            for (int x : arr) {
                System.out.println("Current median: " + stream.getMedian(x));
            }
        }
    }
}
