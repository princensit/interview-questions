package com.prince.design.connection_pool;

import java.sql.SQLException;

public interface Connection extends AutoCloseable {

    void execute(String query) throws SQLException;

    void close() throws SQLException;

    boolean isClosed() throws SQLException;

    void setReadOnly(boolean readOnly) throws SQLException;

    boolean isReadOnly() throws SQLException;
}
