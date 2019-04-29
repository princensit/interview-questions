package com.prince.design;

/**
 * @author Prince Raj
 */
public class DistributedKeyValueStore {

    public static void main(String[] args) {
        // Consistency: Add replica so that we have copy of another data.
        // 1. Keep a local copy in coordinator. Whenever updating a resource, the coordinator will
        // keep the copy of updated version. So in case the update fails, the coordinator is able to
        // re-do the operation.
        //
        // 2. commit log. Similar to concept of git commit log. Basically, for each node machine,
        // it’ll keep the commit log for each operation, which is like the history of all updates.
        // So when we want to update an entry in machine A, it will first store this request in
        // commit log. And then a separate program will process all the commit logs in order (in a
        // queue). Whenever an operation fails, we can easily recover as we can lookup the commit
        // log.
        //
        // 3. Resolve conflict in read. Suppose when the requested resource locates in A1, A2 and
        // A3, the coordinator can ask from all three machines. If by any chance the data is
        // different, the system can resolve the conflict on the fly.
    }
}
