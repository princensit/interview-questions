package com.prince.design;

/**
 * http://blog.gainlo.co/index.php/2016/09/12/dropbox-interview-design-hit-counter/
 *
 * @author Prince Raj
 */
public class DistributedCounter {

    /**
     * <pre>
     * How to get the number of visitors for the past 1 minute?
     *
     * Keep a queue/list containing only last minute timestamps. Remove the timestamp which has exceeded the 1 minute,
     * and then get the queue size.
     *
     * If we can compromise on accuracy, then we can keep 60 counters for each second.
     *
     * For distributed counter, if requests are concurrent then lock may be needed. To avoid this, we can keep multiple
     * machines with its own counter, which can be aggregated later.
     *
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
