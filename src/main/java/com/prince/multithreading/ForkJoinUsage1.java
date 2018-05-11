package com.prince.multithreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * A RecursiveAction is a task which does not return any value. It just does some work, e.g. writing data to disk, and
 * then exits. A RecursiveAction may still need to break up its work into smaller chunks which can be executed by
 * independent threads or CPUs.
 *
 * @author Prince Raj
 */
public class ForkJoinUsage1 {

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(10);

        MyRecursiveAction task = new MyRecursiveAction(24);
        forkJoinPool.invoke(task);
    }

    private static final class MyRecursiveAction extends RecursiveAction {

        private final long workload;

        private MyRecursiveAction(long workload) {
            this.workload = workload;
        }

        @Override
        protected void compute() {
            if (workload > 10) {
                System.out.println("Splitting workload: " + workload);
                List<RecursiveAction> subTasks = createSubTasks(workload);

                for (RecursiveAction subTask : subTasks) {
                    subTask.fork();
                }
            } else {
                System.out.println("Doing workload by myself: " + workload);
            }
        }

        private List<RecursiveAction> createSubTasks(long workload) {
            List<RecursiveAction> subTasks = new ArrayList<>();

            RecursiveAction subTask1 = new MyRecursiveAction(workload / 2);
            RecursiveAction subTask2 = new MyRecursiveAction(workload / 2);

            subTasks.add(subTask1);
            subTasks.add(subTask2);

            return subTasks;
        }
    }
}
