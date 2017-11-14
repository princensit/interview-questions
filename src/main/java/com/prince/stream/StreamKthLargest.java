package com.prince.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Prince Raj
 */
public class StreamKthLargest {

    private final int k;

    private final PriorityQueue<Integer> minHeap = new PriorityQueue<>(10, new Comparator<Integer>() {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    });

    private StreamKthLargest(int k) {
        this.k = k;
    }

    private Integer getKthLargest(int n) {
        final int kthLargest;
        int size = minHeap.size();
        if (size < k - 1) {
            minHeap.add(n);
            kthLargest = -1;
        } else {
            if (size == k - 1) {
                minHeap.add(n);
            } else {
                int min = minHeap.peek();
                if (min < n) {
                    minHeap.remove(min);
                    minHeap.add(n);
                }
            }
            kthLargest = minHeap.peek();
        }

        return kthLargest;
    }

    public static void main(String[] args) throws IOException {
        boolean stdIn = false;
        int k = 3;

        StreamKthLargest stream = new StreamKthLargest(k);

        if (stdIn) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String s;
            while ((s = br.readLine()) != null) {
                if ("done".equals(s)) {
                    break;
                }
                System.out.println("Kth largest: " + stream.getKthLargest(Integer.parseInt(s)));
            }
        } else {
            int[] arr = {23, 10, 15, 70, 5, 80, 100};

            for (int x : arr) {
                System.out.println("Kth largest: " + stream.getKthLargest(x));
            }
        }
    }
}
