package com.prince.design.connection_pool;

import java.sql.SQLException;

public interface ConnectionPool {

    Connection getConnection() throws SQLException, InterruptedException;

    boolean releaseConnection(Connection connection);

    int getCurrentPoolSize();

    void shutdown() throws SQLException;
}
