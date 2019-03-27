package com.prince.design.connection_pool;

import java.sql.SQLException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConnectionTemplate implements ConnectionOperations {

    private static ConnectionTemplate template;

    private ConnectionPool pool;

    // singleton class
    public static ConnectionOperations getInstance(DataSource dataSource) {
        if (template == null) {
            synchronized (ConnectionTemplate.class) {
                if (template == null) {
                    template = new ConnectionTemplate(dataSource);
                }
            }
        }

        return template;
    }

    private ConnectionTemplate(DataSource dataSource) {
        pool = ConnectionPoolFactory.getInstance(dataSource);
    }

    @Override
    public void execute(String query) throws SQLException, InterruptedException {
        try (Connection conn = pool.getConnection()) {
            conn.execute(query);
            pool.releaseConnection(conn);
        }
    }

    @Override
    public void printStats() {
        log.info("[Stats] Current pool size: {}", pool.getCurrentPoolSize());
    }
}
