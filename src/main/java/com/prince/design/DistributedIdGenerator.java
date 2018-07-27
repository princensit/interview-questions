package com.prince.design;

/**
 * @author Prince Raj
 */
public class DistributedIdGenerator {

    /**
     * <pre>
     * A unique ID is a very important business attribute in a distributed system, including some such as order ID,
     * message ID, session ID, and they all have some common features:
     *
     * 1. Globally unique.
     * 2. The trend is increasing.
     *
     * The only way to understand the whole world is to identify unique requests and services.
     *
     * Option 1: Database
     * Use auto_increment but this approach is dependent on DB. If DB hangs, then very prone to problems.
     *
     * Horizontal expansion i.e. split the DB horizaontally into multiple libraries say A, B. A is tracking odd and
     * B is tracking for even. This increases system availability and id is also trending. Challenges are that we need
     * to pre-define the capacity expansion. Further capacity expansion is difficult.
     *
     * Option 2: Local UUID generation
     *
     * Challenges:
     * The generated ID is disorderly and cannot be increased in trend.
     * Because it is a string and it is not incremented, it is not suitable for use as a primary key.
     *
     * Option 3: Adopt local time
     * Approach is milliseconds plus some business ID to generate a unique ID but uniqueness can't be guaranteed when
     * concurrency is very high.
     *
     * Option 4: Twitter snowflake algorithm
     * It is primarily an algorithm that divides the namespace and marks the generated ID according to the machine, time, and so on.
     *
     * Time sync across machines using NTP.
     * Fast generation, at least 1000 IDs/sec
     * IDs should be time-(k)-sortable
     *
     * Target is to create unique id of 48 bits.
     *
     * millis -> 36 bits
     * node id -> 4 bits
     * counter ->  8 bits that allow to keep 256 values for clock resolution.
     *
     * Clock Resolution
     * Since system clock is millis (may be microsecs), it is possible that in a high performance server, we'll get
     * several requests within a single clock. For this purpose, we keep a counter which we increase "manually".
     *
     * The next problem is being able to detecting multiple requests coming in on a single clock. That is done by keeping
     * the last clock value that the last request bumped into. If the next clock value is the same, then we're in a
     * multiple-requests-per-clock scenario we should then increase the internal counters. Sadly, we can only have
     * 8-bits for the counter which makes only 256 increments; if we bump into more requests per clock - we're going
     * to cancel the request.
     *
     * The above issue (Clock Resolution) clearly outlines that we can only do X requests per clock. From this we can
     * extract that we can only do K * X requests per seconds; where K is number of clocks/sec. It is important to see
     * that this has nothing to do with the application's ability to optimize code / serve requests better.
     *
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
