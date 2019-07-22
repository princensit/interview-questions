package com.prince.design.connection_pool;

import java.util.Random;

import com.prince.design.connection_pool.exception.ConnectionInvalidException;

/**
 * @author Prince Raj
 */
public class RemoteConnectionImpl implements RemoteConnection {

    private static final int SEED = 3;

    private static final Random RANDOM = new Random(SEED);

    private boolean closed;

    @Override
    public void open() {
        System.out.println("Opening the connection");
        closed = false;
    }

    @Override
    public void execute(String message) {
        if (closed) {
            throw new ConnectionInvalidException("Connection is already closed");
        }

        System.out.println("Executing the connection with message: " + message);

        long randomMilliSeconds = (RANDOM.nextInt(SEED) + 1) * 1000;
        try {
            Thread.sleep(randomMilliSeconds);
        } catch (InterruptedException e) {
            // do nothing
        }
    }

    @Override
    public void close() {
        System.out.println("Closing the connection");
        closed = true;
    }

    @Override
    public boolean isClosed() {
        return closed;
    }
}
