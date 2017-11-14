package com.prince.design;

/**
 * @author Prince Raj
 */
public class JobScheduler {

    public static void main(String[] args) {
        gatherRequirements();
    }

    private static void gatherRequirements() {
        System.out
                .println("Disaster tolerance: if not all of the hosts are down, the job should be fired successfully.");
        System.out.println("Validity: only one host to fire next job run.");
    }
}
