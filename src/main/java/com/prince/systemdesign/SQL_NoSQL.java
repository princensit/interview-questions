package com.prince.systemdesign;

/**
 * <pre>
 * SQL:
 *
 * Relational databases are structured and have predefined schemas like phone books that store phone
 * numbers and addresses. Ex- MySQL, Postgres, MariaDB, SQLite, Oracle, MS SQL Server
 *
 * NoSQL:
 *
 * Non-relational databases are unstructured, distributed, and have a dynamic schema.
 * Common types of NoSQL:
 * 1. Key-Value Stores. Ex- Redis, Voldemort, Dynamo
 * 2. Document Databases: Ex- MongoDB, CouchDB
 * 3. Wide Column Databases: Columnar databases are best suited for analyzing large datasets. Ex- Cassandra, HBase
 * 4. Graph databases: Data is saved in graph structures with nodes (entities), properties (information about
 *    the entities), and lines (connections between the entities). Ex- Neo4j, InfiniteGraph
 *
 * Differences b/w SQL and NoSQL:
 * 1. SQL - rows and columns. NoSQL - difference data storage models like key-value, document, columnar and graph
 * 2. SQL - fixed schema, any change could cause offline. NoSQL - dynamic schema, columns can be added on the fly.
 * 3. SQL - foreign keys are there. NoSQL - no such constraints are there
 * 4. SQL - ACID. NoSQL - BASE (Basically Available, Soft State, Eventual Consistency)
 * 5. SQL - generally vertically scalable. NoSQL - horizontally scalable i.e. add more servers to serve lot of traffic
 * </pre>
 *
 * @author Prince Raj
 */
public class SQL_NoSQL {
}
