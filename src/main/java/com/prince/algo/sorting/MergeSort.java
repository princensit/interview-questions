package com.prince.algo.sorting;

import java.util.Arrays;

/**
 * Merge sort is another comparison based sort algorithm. It closely follows divide-and-conquer
 * paradigm, and provides O(n​lgn) run time complexity in worst and average cases, however, O(n) in
 * space complexity. Merge sort can be used for sorting huge data sets that can not fit into memory.
 * It is also a stable sort which preserves the order of equal elements.
 *
 * Merge sort is used when the data structure doesn’t support random access, since it works with
 * pure sequential access (forward iterators, rather than random access iterators). It’s also widely
 * used for external sorting, where random access can be very, very expensive compared to sequential
 * access. For ex - when sorting a file which doesn’t fit into memory, you might break it into
 * chunks which fit into memory, sort these using independently, writing each out to a file, then
 * merge sort the generated files.
 *
 * <pre>
 * Space complexity: O(n)
 *
 * Time complexity:
 *   Best: O(nlogn)
 *   Worst: O(nlogn)
 *   Average: O(nlogn)
 * </pre>
 *
 * @author Prince Raj
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] numbers = {3, 6, 1, 5, 8, 9, 1};

        MergeSort sort = new MergeSort();
        sort.mergeSort(numbers, 0, numbers.length - 1);

        System.out.println(Arrays.toString(numbers));
    }

    public void mergeSort(int[] numbers, int left, int right) {
        if (left < right) {
            // avoids overflow for large left and right
            int mid = left + (right - left) / 2;

            // divide array into two equal parts
            mergeSort(numbers, left, mid);
            mergeSort(numbers, mid + 1, right);

            // merge part
            merge(numbers, left, mid, right);
        }
    }

    private void merge(int[] numbers, int left, int mid, int right) {
        int[] leftArray = new int[mid - left + 1];
        int[] rightArray = new int[right - mid];

        // copy values into created arrays
        System.arraycopy(numbers, left, leftArray, 0, leftArray.length);
        System.arraycopy(numbers, mid + 1, rightArray, 0, rightArray.length);

        int leftIndex = 0;
        int rightIndex = 0;
        int k = left;

        while (leftIndex < leftArray.length && rightIndex < rightArray.length) {
            if (leftArray[leftIndex] <= rightArray[rightIndex]) {
                numbers[k] = leftArray[leftIndex];
                leftIndex++;
            } else {
                numbers[k] = rightArray[rightIndex];
                rightIndex++;
            }
            k++;
        }

        // copy rest of the left array
        while (leftIndex < leftArray.length) {
            numbers[k] = leftArray[leftIndex];
            leftIndex++;
            k++;
        }

        // copy rest of the right array
        while (rightIndex < rightArray.length) {
            numbers[k] = rightArray[rightIndex];
            rightIndex++;
            k++;
        }
    }
}
