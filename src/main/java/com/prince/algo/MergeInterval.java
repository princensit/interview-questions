package com.prince.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class MergeInterval {

    public static void main(String[] args) {
        Interval i1 = new Interval(1, 3);
        Interval i2 = new Interval(2, 5);
        Interval i3 = new Interval(3, 9);
        Interval i4 = new Interval(5, 10);
        Interval i5 = new Interval(9, 12);
        Interval i6 = new Interval(10, 14);

        List<Interval> intervals = Arrays.asList(i1, i2, i3, i4, i5, i6);

        System.out.println("Before: " + intervals);

        Collections.sort(intervals, IntervalComparator.INSTANCE);

        countOverlappingIntervals(intervals);

        minMeetingRooms(intervals);

        mergeIntervals(intervals);
    }

    private static void countOverlappingIntervals(List<Interval> intervals) {
        int count = 0;
        for (int i = 0; i < intervals.size() - 2; i++) {
            Interval a = intervals.get(i);
            Interval b = intervals.get(i + 1);
            if (isOverlap(a, b)) {
                count++;
            }
        }

        System.out.println("Count of overlapping intervals: " + count);
    }

    private static void minMeetingRooms(List<Interval> intervals) {
        int count = 0;
        if (intervals != null && intervals.size() > 0) {
            Queue<Integer> queue = new PriorityQueue<>();
            queue.offer(intervals.get(0).getEnd());

            count = 1;

            for (int i = 1; i < intervals.size(); i++) {
                if (intervals.get(i).getStart() < queue.peek()) {
                    count++;
                } else {
                    queue.poll();
                }

                queue.offer(intervals.get(i).getEnd());
            }
        }

        System.out.println("Min meeting rooms: " + count);
    }

    private static void mergeIntervals(List<Interval> intervals) {
        final List<Interval> result = new ArrayList<>();
        result.add(intervals.get(0));

        for (Interval b : intervals) {
            int lastIndex = result.size() - 1;
            Interval a = result.get(lastIndex);
            if (isOverlap(a, b)) {
                Interval c = mergeIntervals(a, b);
                result.set(lastIndex, c);
            } else {
                result.add(b);
            }
        }

        System.out.println("After: " + result);
    }

    private static boolean isOverlap(Interval a, Interval b) {
        return a.getStart() < b.getEnd() && b.getStart() < a.getEnd();
    }

    private static Interval mergeIntervals(Interval a, Interval b) {
        return new Interval(Math.min(a.getStart(), b.getStart()), Math.max(a.getEnd(), b.getEnd()));
    }

    @Data
    private static class Interval {

        private final int start;

        private final int end;

        @Override
        public String toString() {
            return "(" + start + ", " + end + ")";
        }
    }

    private static class IntervalComparator implements Comparator<Interval> {

        private static final IntervalComparator INSTANCE = new IntervalComparator();

        @Override
        public int compare(Interval o1, Interval o2) {
            int val = Integer.compare(o1.getStart(), o2.getStart());
            if (val == 0) {
                val = Integer.compare(o1.getEnd(), o2.getEnd());
            }

            return val;
        }
    }
}
