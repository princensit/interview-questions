package com.prince.algo;

import java.util.Set;
import java.util.TreeSet;

import lombok.Data;

/**
 * Approach - Greedy algorithm
 *
 * http://www.geeksforgeeks.org/job-sequencing-problem-set-1-greedy-algorithm/
 *
 * @author Prince Raj
 */
public class JobSequencing1 {

    public static void main(String[] args) {
        Set<Job> jobs = getJobsInDescendingProfit();

        int jobsExecuted = 0;
        int totalProfit = 0;
        StringBuilder builder = new StringBuilder();
        for (Job job : jobs) {
            int deadline = job.getDeadline();
            if (deadline > jobsExecuted) {
                jobsExecuted += 1;
                totalProfit += job.getProfit();
                builder.append(job.getJobId()).append(" ");
            }
        }

        System.out.println("Jobs: " + builder);
        System.out.println("Total profit: " + totalProfit);
    }

    private static Set<Job> getJobsInDescendingProfit() {
        Set<Job> jobs = new TreeSet<>();

        Job job1 = new Job("a", 2, 100);
        jobs.add(job1);
        Job job2 = new Job("b", 1, 19);
        jobs.add(job2);
        Job job3 = new Job("c", 2, 27);
        jobs.add(job3);
        Job job4 = new Job("d", 1, 25);
        jobs.add(job4);
        Job job5 = new Job("e", 3, 15);
        jobs.add(job5);

        return jobs;
    }

    @Data
    private static final class Job implements Comparable<Job> {

        private final String jobId;

        private final Integer deadline;

        private final Integer profit;

        @Override
        public int compareTo(Job o) {
            return o.getProfit().compareTo(this.profit);
        }
    }
}
