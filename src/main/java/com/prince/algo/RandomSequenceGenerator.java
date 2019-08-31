package com.prince.algo;

import java.net.NetworkInterface;
import java.security.SecureRandom;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Random;

public class RandomSequenceGenerator {

    private static final int TOTAL_BITS = 64;
    private static final int EPOCH_BITS = 42;
    private static final int NODE_ID_BITS = 10;
    private static final int SEQUENCE_BITS = 12;
    private static final int MAX_NODE_ID = (int) (Math.pow(2, NODE_ID_BITS) - 1);
    private static final int MAX_SEQUENCE = (int) (Math.pow(2, SEQUENCE_BITS) - 1);
    private static final long CUSTOM_EPOCH = 1420070400000L;
    private final Random rn = new Random();
    private final int nodeId;
    private volatile long lastTimestamp = -1L;
    private volatile long sequence = 0L;

    public static void main(String[] args) {
        RandomSequenceGenerator generator = new RandomSequenceGenerator();
        System.out.println(generator.nextId());
    }

    private RandomSequenceGenerator() {
        this.nodeId = createNodeId();
    }

    private synchronized String nextId() {
        long currentTimestamp = timestamp();
        if (currentTimestamp < lastTimestamp) {
            throw new IllegalStateException("Invalid System Clock!");
        }

        if (currentTimestamp == lastTimestamp) {
            sequence = (sequence + 1) & MAX_SEQUENCE;
            if (sequence == 0) {
                // sequence exhausted, wait till next millisecond.
                currentTimestamp = waitNextMillis(currentTimestamp);
            }
        } else {
            // reset sequence to start with zero for the next millisecond
            sequence = 0;
        }

        lastTimestamp = currentTimestamp;

        long id = currentTimestamp << (TOTAL_BITS - EPOCH_BITS);
        id |= (nodeId << (TOTAL_BITS - EPOCH_BITS - NODE_ID_BITS));
        id |= sequence;

        return String.valueOf(shuffleBits(id));
    }

    // get current timestamp in milliseconds, adjust for the custom epoch
    private static long timestamp() {
        Calendar cal = Calendar.getInstance();
        return cal.getTimeInMillis() - CUSTOM_EPOCH;
    }

    // block and wait till next millisecond
    private long waitNextMillis(long currentTimestamp) {
        while (currentTimestamp == lastTimestamp) {
            currentTimestamp = timestamp();
        }

        return currentTimestamp;
    }

    // initialize node id
    private int createNodeId() {
        int nodeId;
        try {
            StringBuilder sb = new StringBuilder();
            Enumeration<NetworkInterface> networkInterfaces =
                    NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                byte[] mac = networkInterface.getHardwareAddress();
                if (mac != null) {
                    for (byte b : mac) {
                        sb.append(String.format("%02X", b));
                    }
                }
            }
            nodeId = sb.toString().hashCode();
        } catch (Exception ex) {
            nodeId = (new SecureRandom().nextInt());
        }

        nodeId = nodeId & MAX_NODE_ID;
        return nodeId;
    }

    private long shuffleBits(long number) {
        int k = Long.bitCount(number);
        int setBits = 0;
        for (int i = 0; i < TOTAL_BITS && setBits < k; i++) {
            int j = rn.nextInt(TOTAL_BITS - i) + i;

            if (bitsAreDifferent(number, i, j)) {
                number ^= (1 << i) | (1 << j);
            }

            if (((number >> i) & 1) == 1)
                setBits++;
        }

        return number;
    }

    private boolean bitsAreDifferent(long number, int i, int j) {
        return ((number >> i) & 1) != ((number >> j) & 1);
    }
}
