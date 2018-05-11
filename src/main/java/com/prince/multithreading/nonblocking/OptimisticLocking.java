package com.prince.multithreading.nonblocking;

/**
 * @author Prince Raj
 */
public class OptimisticLocking {

    public static void main(String[] args) {
        System.out
                .println("Optimistic locking allows all threads to create a copy of the shared memory without any blocking."
                        + " The threads may then make modifications to their copy, and attempt to write their modified"
                        + " version back into the shared memory. If no other thread has made any modifications to the"
                        + " shared memory, the compare-and-swap operation allows the thread to write its changes to shared"
                        + " memory. If another thread has already changed the shared memory, the thread will have to obtain"
                        + " a new copy, make its changes and attempt to write them to shared memory again.\n"
                        + "\n"
                        + "The reason this is called optimistic locking is that threads obtain a copy of the data they"
                        + " want to change and apply their changes, under the optimistic assumption that no other thread"
                        + " will have made changes to the shared memory in the meantime. When this optimistic assumption "
                        + "holds true, the thread just managed to update shared memory without locking. When this assumption"
                        + " is false, the work was wasted, but still no locking was applied.\n"
                        + "\n"
                        + "Optimistic locking tends to work best with low to medium contention on the shared memory. "
                        + "If the content is very high on the shared memory, threads will waste a lot of CPU cycles copying"
                        + " and modifying the shared memory only to fail writing the changes back to the shared memory."
                        + " But, if you have a lot of content on shared memory, you should anyways consider redesigning "
                        + "your code to lower the contention.");
    }
}
