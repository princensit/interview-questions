package com.prince.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import lombok.Data;

/**
 * http://www.geeksforgeeks.org/minimum-number-platforms-required-railwaybus-station/
 *
 * @author Prince Raj
 */
public class MinimumPlatforms {

    public static void main(String[] args) {
        // {9:00, 9:40, 9:50, 11:00, 15:00, 18:00}
        int arr[] = {900, 940, 950, 1100, 1500, 1800};

        // {9:10, 12:00, 11:20, 11:30, 19:00, 20:00}
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};

        System.out.println(maxTrainsAtGivenTime(arr, dep));
    }

    private static int maxTrainsAtGivenTime(int arr[], int dep[]) {
        List<Time> list = new ArrayList<>();
        for (int a : arr) {
            Time time = new Time(a, true);
            list.add(time);
        }

        for (int a : dep) {
            Time time = new Time(a, false);
            list.add(time);
        }

        Collections.sort(list, TimeComparator.INSTANCE);

        int maxCount = 0;
        int count = 0;
        for (Time t : list) {
            boolean arrival = t.isArrival();
            if (arrival) {
                count++;

                if (count > maxCount) {
                    maxCount = count;
                }
            } else {
                count--;
            }
        }

        return maxCount;
    }

    @Data
    private static final class Time {

        private final Integer time;

        private final boolean arrival;
    }

    private static final class TimeComparator implements Comparator<Time> {

        private static final TimeComparator INSTANCE = new TimeComparator();

        @Override
        public int compare(Time o1, Time o2) {
            return o1.time.compareTo(o2.time);
        }
    }
}
