package com.prince.design;

/**
 * @author Prince Raj
 */
public class Kafka_vs_Redis {

    public static void main(String[] args) {
        print();
    }

    private static void print() {
        System.out
                .println("Redis is an in-memory store. This means that it uses its primary memory for storage and processing which makes it much faster than the disk-based Kafka.");
        System.out
                .println("The only problem with Redis’ in-memory store is that we can’t store large amounts of data for long periods of time.");
        System.out
                .println("Since the primary in-memory is smaller than a disk, we have to clear it regularly by automatically moving data from in-memory to disks and making room for new data.");
        System.out
                .println("Redis doesn’t have the concept of parallelism like Kafka does, where multiple processes can consume the data at the same time.");
        System.out.println("Try Redis when dealing with real-time messages processing with a minimal latency.");
        System.out
                .println(" However, in case messages are large and data should be reused, you should first consider Kafka.");
    }
}
