package com.prince.design.circuit_breaker;

/**
 * @author Prince Raj
 */
public class CheckIntervalData {

    // counter for events
    private final int eventCount;

    // start time of the current check interval
    private final long checkIntervalStart;

    public CheckIntervalData(int eventCount, long checkIntervalStart) {
        this.eventCount = eventCount;
        this.checkIntervalStart = checkIntervalStart;
    }

    public CheckIntervalData increment(int delta) {
        return (delta != 0) ? new CheckIntervalData(getEventCount() + delta, getCheckIntervalStart()) : this;
    }

    public int getEventCount() {
        return eventCount;
    }

    public long getCheckIntervalStart() {
        return checkIntervalStart;
    }
}
