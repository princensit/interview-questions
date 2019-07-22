package com.prince.design.connection_pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.prince.design.connection_pool.exception.ConnectionNotClosedException;

/**
 * @author Prince Raj
 */
public class PoolFactory implements ConnectionPool {

    private static final int MAX_POOL_SIZE = 200;

    private static PoolFactory instance;

    private String hostname;

    private String port;

    private int initialPoolSize;

    private int maxPoolSize;

    private int incrementStepPoolSize;

    private int currentPoolSize;

    private BlockingQueue<RemoteConnection> connectionPool;

    // TODO add a map b/w client id and connection to know who is holding which connection
    private List<RemoteConnection> usedConnections = new ArrayList<>();

    // singleton class
    public static PoolFactory getInstance(DataSource dataSource) {
        if (instance == null) {
            synchronized (PoolFactory.class) {
                if (instance == null) {
                    instance = new PoolFactory(dataSource);
                }
            }
        }

        return instance;
    }

    private PoolFactory(DataSource dataSource) {
        this.hostname = dataSource.getHostname();
        this.port = dataSource.getPort();
        this.initialPoolSize = dataSource.getInitialPoolSize();
        this.maxPoolSize = dataSource.getMaxPoolSize() > MAX_POOL_SIZE ? MAX_POOL_SIZE
                : dataSource.getMaxPoolSize();
        this.incrementStepPoolSize = dataSource.getIncrementStepPoolSize();

        if (initialPoolSize > maxPoolSize || initialPoolSize < 1) {
            throw new IllegalArgumentException("Invalid pool size parameters");
        }

        this.connectionPool = new LinkedBlockingQueue<>(maxPoolSize);

        initializePooledConnections(initialPoolSize);
    }

    @Override
    public synchronized RemoteConnection getConnection() throws InterruptedException {
        if (connectionPool.isEmpty()) {
            initializePooledConnections(incrementStepPoolSize);
        }

        System.out.println("Before take: " + System.currentTimeMillis());
        RemoteConnection conn = connectionPool.take();
        System.out.println("After take: " + System.currentTimeMillis());

        usedConnections.add(conn);

        return conn;
    }

    @Override
    public boolean releaseConnection(RemoteConnection connection) {
        if (!connection.isClosed()) {
            throw new ConnectionNotClosedException(
                    "Connection should be closed first before release it");
        }

        boolean status = connectionPool.offer(connection);
        if (status) {
            usedConnections.remove(connection);
        }

        return status;
    }

    @Override
    public void shutdown() {
        for (RemoteConnection conn : usedConnections) {
            if (!conn.isClosed()) {
                conn.close();
                releaseConnection(conn);
            }
        }
    }

    @Override
    public void printStats() {
        System.out.println("Total connections: " + currentPoolSize);
        System.out.println("Used connections: " + usedConnections.size());
        System.out.println("Free connections: " + connectionPool.size());
    }

    private void initializePooledConnections(int poolSize) {
        System.out.println("Adding " + poolSize + " many connections to the pool");
        // create the connection and add to pool
        for (int i = 0; i < poolSize; i++) {
            checkAndCreateConnection();
        }
    }

    private synchronized void checkAndCreateConnection() {
        if (currentPoolSize < maxPoolSize) {
            RemoteConnection conn = DriverManager.getConnection(hostname, port);
            connectionPool.offer(conn);

            currentPoolSize++;
            // } else {
            // throw new ConnectionPoolExhaustException("Max connections in pool reached");
        }
    }
}
