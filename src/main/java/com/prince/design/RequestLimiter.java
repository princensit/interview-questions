package com.prince.design;

import java.util.ArrayDeque;
import java.util.Date;
import java.util.Deque;

/**
 * Limit 3rd party api calls to x requests per second, say 20 requests / second
 *
 * @author Prince Raj
 */
public class RequestLimiter {

    private final int maxRequestsPerTimePeriod; // 20 requests

    private final long timePeriod; // 60000 milliseconds

    private final Deque<Long> requestTimes;

    public RequestLimiter(int maxRequestsPerTimePeriod, long timePeriod) {
        this.maxRequestsPerTimePeriod = maxRequestsPerTimePeriod;
        this.timePeriod = timePeriod;
        this.requestTimes = new ArrayDeque<>(maxRequestsPerTimePeriod);
    }

    public synchronized void limitRequests() {
        long now = System.currentTimeMillis();
        int requestsCount = requestTimes.size();
        if (requestsCount > maxRequestsPerTimePeriod) {
            long oldestRequestTime = requestTimes.removeLast();
            long elapsed = now - oldestRequestTime;
            if (elapsed <= timePeriod) {
                long sleepTime = timePeriod - elapsed;

                System.out.println("#### exceeded "
                        + maxRequestsPerTimePeriod
                        + " requests in "
                        + timePeriod
                        + "ms, sleeping for "
                        + sleepTime
                        + "ms");
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    // ignore
                }

                // update current time
                now = System.currentTimeMillis();
            }
        }

        if (requestsCount > 0) {
            long recentRequestTime = requestTimes.peekFirst();
            long elapsed = now - recentRequestTime;
            if (elapsed >= timePeriod) {
                requestTimes.clear();
            }
        }

        requestTimes.add(now);
    }

    public static void main(String[] args) {
        RequestLimiter limiter = new RequestLimiter(20, 60000);

        for (int i = 0; i < 100; i++) {
            limiter.limitRequests();
            System.out.println("####### " + i + " - " + new Date());
        }
    }
}
