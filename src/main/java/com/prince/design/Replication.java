package com.prince.design;

/**
 * https://engineering.linkedin.com/distributed-systems/log-what-every-software-engineer-should-know-about-real-time-
 * datas-unifying
 *
 * http://samza.apache.org/learn/documentation/0.7.0/introduction/architecture.html
 *
 * @author Prince Raj
 */
public class Replication {

    public static void main(String[] args) {

        // The distributed systems literature commonly distinguishes two broad approaches to processing and replication.
        //
        // 1. The "state machine model" usually refers to an active-active model where we keep a log of the incoming
        // requests and each replica processes each request.
        //
        // 2. A slight modification of this, called the "primary-backup model", is to elect one replica as the leader
        // and allow this leader to process requests in he order they arrive and log out the changes to its state from
        // processing the requests. The other replicas apply in order the state changes the leader makes so that they
        // will be in sync and ready to take over as leader should the leader fail.

        // see image for more details

        // To understand the difference between these two approaches, let's look at a toy problem. Consider a replicated
        // "arithmetic service" which maintains a single number as its state (initialized to zero) and applies additions
        // and multiplications to this value. The active-active approach might log out the transformations to apply, say
        // "+1", "*2", etc. Each replica would apply these transformations and hence go through the same set of values.
        // The "active-passive" approach would have a single master execute the transformations and log out the result,
        // say "1", "3", "6", etc. This example also makes it clear why ordering is key for ensuring consistency between
        // replicas: reordering an addition and multiplication will yield a different result.
    }
}
