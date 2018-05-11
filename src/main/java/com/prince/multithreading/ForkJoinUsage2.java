package com.prince.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author Prince Raj
 */
public class ForkJoinUsage2 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        MyRecursiveTask task = new MyRecursiveTask(24);
        long result = forkJoinPool.invoke(task);
        System.out.println("Result: " + result);
    }

    private static final class MyRecursiveTask extends RecursiveTask<Long> {

        private final long workload;

        private MyRecursiveTask(long workload) {
            this.workload = workload;
        }

        @Override
        protected Long compute() {
            if (workload > 10) {
                System.out.println("Splitting workload: " + workload);
                List<MyRecursiveTask> subTasks = createSubTasks(workload);

                // fork
                for (MyRecursiveTask subTask : subTasks) {
                    subTask.fork();
                }

                // join
                long result = 0;
                for (MyRecursiveTask subTask : subTasks) {
                    result += subTask.join();
                }

                return result;
            } else {
                System.out.println("Doing workload by myself: " + workload);
                return workload * 3;
            }
        }

        private List<MyRecursiveTask> createSubTasks(long workload) {
            List<MyRecursiveTask> subTasks = new ArrayList<>();

            MyRecursiveTask subTask1 = new MyRecursiveTask(workload / 2);
            MyRecursiveTask subTask2 = new MyRecursiveTask(workload / 2);

            subTasks.add(subTask1);
            subTasks.add(subTask2);

            return subTasks;
        }
    }
}
