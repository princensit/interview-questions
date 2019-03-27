package com.prince.design.connection_pool;

import java.sql.SQLException;

public interface ConnectionOperations {

    void execute(String query) throws SQLException, InterruptedException;

    void printStats();
}
