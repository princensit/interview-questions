package com.prince.design;

/**
 * @author Prince Raj
 */
public class Cache {

    public static void main(String[] args) {
        // LRU

        // Eviction policy:
        // 1. LRU
        // 2. RR
        // 3. count based

        // ---- Concurrency -------
        // It falls into the classic reader-writer problem.
        // The common solution of course is using a lock. The downside is obvious – it affects the performance a lot.
        // One approach is to split the cache into multiple shards and have a lock for each of them so that clients
        // won’t wait for each other if they are updating cache from different shards. However, given that hot entries
        // are more likely to be visited, certain shards will be more often locked than others.

        // An alternative is to use commit logs. To update the cache, we can store all the mutations into logs rather
        // than update immediately. And then some background processes will execute all the logs asynchronously. This
        // strategy is commonly adopted in database design.
    }
}
