package com.prince.design.connection_pool;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lombok.Getter;

public class ConnectionPoolFactory implements ConnectionPool {

    private static final int MAX_POOL_SIZE = 20;

    private String url;

    private String username;

    private String password;

    private int initialPoolSize;

    private int maxPoolSize;

    @Getter
    private int currentPoolSize;

    private BlockingQueue<Connection> connectionPool;

    private List<Connection> usedConnections = new ArrayList<>();

    private static ConnectionPool pool;

    // singleton class
    public static ConnectionPool getInstance(DataSource dataSource) {
        if (pool == null) {
            synchronized (ConnectionPoolFactory.class) {
                if (pool == null) {
                    pool = new ConnectionPoolFactory(dataSource);
                }
            }
        }

        return pool;
    }

    private ConnectionPoolFactory(DataSource dataSource) {
        this.url = dataSource.getUrl();
        this.username = dataSource.getUsername();
        this.password = dataSource.getPassword();
        this.initialPoolSize = dataSource.getInitialPoolSize();
        this.maxPoolSize = dataSource.getMaxPoolSize() > MAX_POOL_SIZE ? MAX_POOL_SIZE
                : dataSource.getMaxPoolSize();

        if (initialPoolSize > maxPoolSize || initialPoolSize < 1) {
            throw new IllegalArgumentException("Invalid pool size parameters");
        }

        this.connectionPool = new LinkedBlockingQueue<>(maxPoolSize);

        try {
            String driverClassName = dataSource.getDriverClassName();
            initPooledConnections(driverClassName);
        } catch (Exception ex) {
            throw new RuntimeException(
                    "Got exception in setting up the connection pool: " + ex.getMessage());
        }
    }

    @Override
    public Connection getConnection() throws SQLException, InterruptedException {
        checkAndCreateConnection();

        Connection conn = connectionPool.take();
        usedConnections.add(conn);

        return conn;
    }

    @Override
    public boolean releaseConnection(Connection conn) {
        boolean status = connectionPool.offer(conn);
        if (status) {
            usedConnections.remove(conn);
        }

        return status;
    }

    @Override
    public void shutdown() throws SQLException {
        for (Connection conn : usedConnections) {
            if (!conn.isClosed()) {
                conn.close();
                releaseConnection(conn);
            }
        }
    }

    private void initPooledConnections(String driverClassName)
            throws ClassNotFoundException, SQLException {

        // attempt to load the driver class
        Class.forName(driverClassName);

        // create the connection and add to pool
        for (int i = 0; i < initialPoolSize; i++) {
            checkAndCreateConnection();
        }
    }

    private synchronized void checkAndCreateConnection() throws SQLException {
        if (currentPoolSize < maxPoolSize) {
            Connection conn = DriverManager.getConnection(url, username, password);
            connectionPool.offer(conn);

            currentPoolSize++;
        }
    }
}
