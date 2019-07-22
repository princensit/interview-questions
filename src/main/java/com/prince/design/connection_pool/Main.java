package com.prince.design.connection_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Prince Raj
 */
public class Main {

    public static void main(String[] args) throws Exception {
        DataSource dataSource = getDataSource();

        // approach 1
        // testUsingSingleClient(dataSource);

        // approach 2
        testUsingMultipleThreads(dataSource);
    }

    private static void testUsingSingleClient(DataSource dataSource) throws InterruptedException {
        ConnectionPool pool = PoolFactory.getInstance(dataSource);
        RemoteConnection connection = pool.getConnection();

        // TODO do some pre-validations if connection is valid or not

        // opening the connection
        connection.open();

        // doing some operations
        connection.execute("Hello World");

        // closing the connection once done
        connection.close();

        // throws exception if using already closed connection
        connection.execute("Hello World");

        // release the connection to pool if client wishes to
        boolean status = pool.releaseConnection(connection);
        System.out.println("Released connection successfully");

        pool.printStats();
    }

    private static void testUsingMultipleThreads(DataSource dataSource) {
        // datasource once initialized can't be changed
        final ConnectionOperations operations = ConnectionTemplate.getInstance(dataSource);
        operations.printStats();

        // creating n threads and testing the behaviour of connection pool
        int n = 5;
        ExecutorService executor = Executors.newFixedThreadPool(n);

        for (int i = 0; i < n; i++) {
            final int randomNumber = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String message = "Message " + randomNumber;
                    try {
                        operations.execute(message);
                    } catch (Exception ex) {
                        System.out.println("Got exception in executing using connection with error "
                                + ex.getMessage());
                    }

                    operations.printStats();
                }
            });
        }

        executor.shutdown();
    }

    private static DataSource getDataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setHostname("127.0.0.1");
        dataSource.setPort("8080");
        dataSource.setInitialPoolSize(1);
        dataSource.setMaxPoolSize(1);
        dataSource.setIncrementStepPoolSize(1);

        return dataSource;
    }
}
