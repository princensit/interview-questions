package com.prince.javaconcepts;

/**
 * <pre>
 * JDBC is an abstraction layer that allows users to choose between databases. JDBC enables developers to write database
 * applications in Java, without having to concern themselves with the underlying details of a particular database.
 *
 * The JDBC Driver provides vendor-specific implementations of the abstract classes provided by the JDBC API. Each driver
 * must provide implementations for the following classes of the java.sql package:Connection, Statement, PreparedStatement, CallableStatement, ResultSet and Driver.
 *
 * Class.forName() method is used to load the driver that will establish a connection to the database.
 *
 * PreparedStatements are precompiled and thus, their performance is much better when compared to Statement. Also,
 * PreparedStatement objects can be reused with different input values to their queries.
 *
 * A CallableStatement is used to execute stored procedures. Stored procedures are stored and offered by a database.
 * Stored procedures may take input values from the user and may return a result. The usage of stored procedures is highly
 * encouraged, because it offers security and modularity. The method that prepares a CallableStatement is the following:
 *    CallableStatement.prepareCall();
 *
 * Connection pooling -
 * The interaction with a database can be costly, regarding the opening and closing of database connections. Especially,
 * when the number of database clients increases, this cost is very high and a large number of resources is consumed.
 * A pool of database connections is obtained at start up by the application server and is maintained in a pool.
 * A request for a connection is served by a connection residing in the pool. In the end of the connection, the request
 * is returned to the pool and can be used to satisfy future requests.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Jdbc {

}
