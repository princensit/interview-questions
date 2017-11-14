package com.prince.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Prince Raj
 */
public class CombinationOfNumbers {

    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        lists.add(list1);
        List<Integer> list2 = Arrays.asList(4, 5, 6);
        lists.add(list2);
        List<Integer> list3 = Arrays.asList(7, 8);
        lists.add(list3);

        int[] arr = new int[lists.size()];
        print(lists, arr, 0);
    }

    private static void print(List<List<Integer>> lists, int[] arr, int listIndex) {
        int listSize = lists.size();
        if (listIndex == listSize) {
            return;
        }

        List<Integer> list = lists.get(listIndex);
        for (Integer item : list) {
            arr[listIndex] = item;
            if (listIndex == arr.length - 1) {
                System.out.println(Arrays.toString(arr));
            } else {
                print(lists, arr, listIndex + 1);
            }
        }
    }
}
