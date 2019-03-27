package com.prince.design.connection_pool;

import java.sql.SQLException;

public class DriverManager {

    public static Connection getConnection(String url, String user, String password)
            throws SQLException {

        return new ConnectionImpl();
    }
}
