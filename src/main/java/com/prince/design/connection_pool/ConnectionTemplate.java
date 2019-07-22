package com.prince.design.connection_pool;

/**
 * @author Prince Raj
 */
public class ConnectionTemplate implements ConnectionOperations {

    private static ConnectionTemplate instance;

    private ConnectionPool pool;

    // singleton class
    public static ConnectionOperations getInstance(DataSource dataSource) {
        if (instance == null) {
            synchronized (ConnectionTemplate.class) {
                if (instance == null) {
                    instance = new ConnectionTemplate(dataSource);
                }
            }
        }

        return instance;
    }

    private ConnectionTemplate(DataSource dataSource) {
        pool = PoolFactory.getInstance(dataSource);
    }

    @Override
    public void execute(String message) {
        try (RemoteConnection conn = pool.getConnection()) {

            // opening the connection
            conn.open();

            // doing some operations
            conn.execute(message);

            // closing the connection once done
            conn.close();

            // release the connection to pool if client wishes to
            pool.releaseConnection(conn);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void printStats() {
        pool.printStats();
    }
}
