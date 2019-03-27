package com.prince.design.connection_pool;

import java.sql.SQLException;
import java.util.Random;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionImpl implements Connection {

    private static final int SEED = 3;

    private static final Random RANDOM = new Random(SEED);

    @Getter
    @Setter
    private boolean readOnly;

    @Override
    public void execute(String query) throws SQLException {
        log.info("Executing query: '{}'", query);

        long randomMilliSeconds = (RANDOM.nextInt(SEED) + 1) * 1000;
        try {
            Thread.sleep(randomMilliSeconds);
        } catch (InterruptedException e) {
            // do nothing
        }

        log.info("Executed query: '{}' with execution time: {}", query, randomMilliSeconds);
    }

    @Override
    public void close() throws SQLException {

    }

    @Override
    public boolean isClosed() throws SQLException {
        return false;
    }
}
