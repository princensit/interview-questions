package com.prince.design.connection_pool;

import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {

    public static void main(String[] args) {
        DataSource dataSource = new DataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_db");
        dataSource.setUsername("root");
        dataSource.setPassword("groot");
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setInitialPoolSize(5);
        dataSource.setMaxPoolSize(10);

        // datasource once initialized can't be changed
        final ConnectionOperations operations = ConnectionTemplate.getInstance(dataSource);
        operations.printStats();

        // creating n threads and testing the behaviour of connection pool
        int n = 20;
        ExecutorService executor = Executors.newFixedThreadPool(n);

        for (int i = 0; i < n; i++) {
            final int fancyNumber = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    String query = "SELECT " + fancyNumber;
                    try {
                        operations.execute(query);
                    } catch (SQLException | InterruptedException ex) {
                        log.info("Got exception in executing the query: " + query, ex.getMessage());
                    }

                    operations.printStats();
                }
            });
        }

        executor.shutdown();
    }
}
