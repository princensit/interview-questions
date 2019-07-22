package com.prince.design.connection_pool;

/**
 * @author Prince Raj
 */
public interface RemoteConnection extends AutoCloseable {

    void open();

    void execute(String message);

    void close();

    boolean isClosed();
}
