package com.prince.design.connection_pool;

/**
 * @author Prince Raj
 */
public class DriverManager {

    public static RemoteConnection getConnection(String hostname, String port) {

        return new RemoteConnectionImpl();
    }
}
