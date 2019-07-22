package com.prince.design.connection_pool;

/**
 * @author Prince Raj
 */
public interface ConnectionPool {

    RemoteConnection getConnection() throws InterruptedException;

    boolean releaseConnection(RemoteConnection connection);

    void shutdown();

    void printStats();
}
