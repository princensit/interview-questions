package com.prince.design.database;

/**
 * In the context of databases, cardinality refers to the uniqueness of data values contained in a
 * column. High cardinality means that the column contains a large percentage of totally unique
 * values. Low cardinality means that the column contains a lot of “repeats” in its data range.
 *
 * Bitmap indexes have traditionally been considered to work well for low-cardinality columns, which
 * have a modest number of distinct values, either absolutely, or relative to the number of records
 * that contain the data. Ex - Boolean
 *
 * The most expensive operations involving hard disks are seeks. In order to improve overall
 * performance, related data should be stored in a fashion to minimize the number of seeks. This is
 * known as locality of reference, and the basic concept appears in a number of different contexts.
 *
 * <pre>
 * =======> Row-oriented systems
 * Row-oriented systems
 * 001:10,Smith,Joe,40000
 * 002:12,Jones,Mary,50000
 * 003:11,Johnson,Cathy,44000
 * 004:22,Jones,Bob,55000
 *
 * =======> Column-oriented systems
 * 10:001,12:002,11:003,22:004
 * Smith:001,Jones:002,Johnson:003,Jones:004
 * Joe:001,Mary:002,Cathy:003,Bob:004
 * 40000:001,50000:002,44000:003,55000:004
 * …;Smith:001;Jones:002,004;Johnson:003;…
 * </pre>
 *
 * A row-based system can retrieve the row in a single disk read, whereas numerous disk operations
 * to collect data from multiple columns are required from a columnar database.
 *
 * Partitioning, indexing, caching, views, OLAP cubes, and transactional systems such as write-ahead
 * logging or multiversion concurrency control all dramatically affect the physical organization of
 * either system. That said, online transaction processing (OLTP)-focused RDBMS systems are more
 * row-oriented, while online analytical processing (OLAP)-focused systems are a balance of
 * row-oriented and column-oriented.
 *
 * Serial ATA (SATA) hard drive has a maximum transfer rate of 600 MB/second (Megabytes per second)
 * while DDR3 SDRAM Memory can reach transfer rates of 17 GB/s (Gigabytes per second), nearly 30
 * times as fast.
 *
 * Columnar databases boost performance by reducing the amount of data that needs to be read from
 * disk, both by efficiently compressing the similar columnar data and by reading only the data
 * necessary to answer the query.
 *
 * In practice, columnar databases are well-suited for OLAP-like workloads (e.g., data warehouses)
 * which typically involve highly complex queries over all data (possibly petabytes)
 *
 * ----------------------------
 *
 * Thread Execution in MySQL
 *
 * Your SQL query will be place into a thread that will live the time of your connection into the
 * database server. That thread will parse the query and compute an execution plan (QP) and run it.
 * The QP ask statistics to the underlying tables and indexes to decide ( brut force) what plan cost
 * less.
 *
 * You can see the plan running : EXPLAIN SELECT col1,col2,col3,col4,...,coln FROM table1 WHERE
 * (condition) ..
 *
 * You need to take care that your conditions are covered by an index that will promote a plan that
 * using the index have direct access to those 1000 thousand records
 *
 * Without the index the plan will push all table records to the SQL thread that will reject records
 * that don't match your conditions. DBA are calling this a full table scan and try to limit this
 * with good indexing.
 *
 * The result will be put into a network buffer and send to your client. This is costly CPU as well
 * so thread does not really stop working until the plan is finished.
 *
 * In many case result set need to be sorted: ORDER BY, GROUP BY, DISTINCT, that are not covered by
 * an index. In that case the result will be fully BUFFERED into a memory table or a disk base table
 * (MyISAM engine type) when the result can't be put in memory : sizing or data type reasons (BLOB
 * does not like memory tables and vis versa)
 *
 * if you wan't to force the server to buffer the resultset into a temporary table just add
 * SQL_BUFFER_RESULT after the SELECT. This will free up a table lock while the resultset is being
 * sent to the client.
 *
 * Some API calls also enable this same server buffering feature.
 *
 * In MySQL your thread is living in what is call the SQL layer and your data and indexes are manage
 * by what is call the STORAGE ENGINE layer. Each storage engine use some memory buffers to cache
 * indexes and table data. so the first execution of your query will load data from disk and then
 * retrive it later from memory if the all dataset feat into those buffer. Monitoring the hit ratio
 * of those buffers is a requirement for the overall performance and setting them correctly is a
 * must do : read about innodb_buffer_pool_size , and key_buffer. There are mainly the only
 * variables that need to be change according to memory of your hardware.
 *
 * There is an other cache call the query cache (QC) this one stay in the SQL LAYER and will store
 * the resultset of your query until the undelying tables get some changes. If the query come again
 * the result will be served from the QC and not get executed again.
 *
 * All about indexes:
 * https://www.oreilly.com/library/view/high-performance-mysql/0596003064/ch04.html
 *
 * Query execution order: https://qxf2.com/blog/mysql-query-execution/
 *
 * ----------------------------
 *
 * Locking and Isolation Levels:
 *
 * Pessimistic Locking:
 *
 * This is a standard locking like an Exclusive lock or Shared lock. The reader is blocked by writer
 * and writer is blocked by the reader. SQL Server Isolation levels like Read Committed, Repeatable
 * Read, Serializable are mostly doing Pessimistic Locking. It locks the transaction whenever
 * something is going wrong and it puts the transaction into the blocking queue. Pessimistic locking
 * allows you to access live and committed data, there are no chances for accessing dirty data. The
 * big OLTP system like banking system or finance system are always preferred to use Pessimistic
 * Locking because data accuracy is required for both reader and writer. Yes, locking is an extra
 * overhead in a big system, but a few types of the application required for accuracy.
 *
 * Optimistic Locking:
 *
 * It is also called as row versioning, and it never blocks any transaction. The MVCC (multiple
 * version concurrency control) architecture is most popular now a day and it depends on Optimistic
 * Locking concept. The RDBMS like PostgreSQL and MySQL InnoDB is fully based on MVCC. Microsoft SQL
 * Server has also Snapshot Isolation which is one type of Optimistic Locking. The Reader never
 * blocks the writers and writers never blocks the readers. Internally, it is managing different row
 * versions, so if the reader is reading one version of data and at the same time writer can also
 * update the same data by creating a new version of that data, Next time readers can read new
 * version of data and old version of data is marked as dead tuple. There are 70% chance to get last
 * committed version of data while accessing data using Optimistic locking, but it gives fast access
 * because it never creates a dependency between transactions. Generally, most of the web and mobile
 * applications are fine with the last committed version of data.
 *
 * Isolation levels:
 *
 * 1. READ UNCOMMITTED: UserA will see the change made by UserB. This isolation level is called
 * dirty reads, which means that read data is not consistent with other parts of the table or the
 * query, and may not yet have been committed. This isolation level ensures the quickest
 * performance, as data is read directly from the table’s blocks with no further processing,
 * verifications or any other validation. The process is quick and the data is as dirty as it can
 * get.
 *
 * 2. READ COMMITTED: UserA will not see the change made by UserB. This is because in the READ
 * COMMITTED isolation level, the rows returned by a query are the rows that were committed when the
 * query was started. The change made by UserB was not present when the query started, and therefore
 * will not be included in the query result.
 *
 * 3. REPEATABLE READ: UserA will not see the change made by UserB. This is because in the
 * REPEATABLE READ isolation level, the rows returned by a query are the rows that were committed
 * when the transaction was started. The change made by UserB was not present when the transaction
 * was started, and therefore will not be included in the query result. This means that "All
 * consistent reads within the same transaction read the snapshot established by the first read"
 * (from MySQL documentation. See
 * http://dev.mysql.com/doc/refman/5.1/en/innodb-consistent-read.html).
 *
 * Note: Repeatable Read only applies for non-locking queries. If you do a locking SELECT statement
 * using FOR UPDATE or even FOR SHARE aka LOCK IN SHARE MODE, the query reads as though you were
 * using a Read Committed isolation level.
 *
 * 4. SERIALIZABLE: This isolation level specifies that all transactions occur in a completely
 * isolated fashion, meaning as if all transactions in the system were executed serially, one after
 * the other. The DBMS can execute two or more transactions at the same time only if the illusion of
 * serial execution can be maintained.
 *
 * In practice, SERIALIZABLE is similar to REPEATABLE READ, but uses a different implementation for
 * each database engine. In Oracle, the REPEATABLE READ level is not supported and SERIALIZABLE
 * provides the highest isolation level. This level is similar to REPEATABLE READ, but InnoDB
 * implicitly converts all plain SELECT statements to “SELECT … LOCK IN SHARE MODE.
 *
 * NOTE: If your queries and transactions are short : use rather the default REPEATABLE-READ mode!
 * If your queries are long and reading a lot of data which are likely to be modified by other
 * transactions in parallel : then use the READ-COMMITTED mode - this will allow you to read already
 * committed changes while your query is progressing.
 *
 * Consistent read views:
 *
 * In REPEATBLE READ, a ‘read view’ ( trx_no does not see trx_id >= ABC, sees < ABB ) is created at
 * the start of the transaction, and this read view (consistent snapshot in Oracle terms) is held
 * open for the duration of the transaction. If you execute a SELECT statement at 5AM, and come back
 * in an open transaction at 5PM, when you run the same SELECT, then you will see the exact same
 * resultset that you saw at 5AM. This is called MVCC (multiple version concurrency control) and it
 * is accomplished using row versioning and UNDO information.
 *
 * In REPEATABLE READ InnoDB also creates gap locks for range scans.
 *
 * select * from some_table where id > 100 FOR UPDATE;
 *
 * The above update will create a gap lock that will prevent any rows with id > 100 from being
 * inserted into the table until the transaction rolls back or commits.
 *
 * In the same transaction, if the SELECT … FOR UPDATE is run at 5AM, and an UPDATE is run at 5PM
 * (“UPDATE some_table where id > 100”) then the UPDATE will change the same rows that SELECT FOR
 * UPDATE locked at 5AM. There is no possibility of changing additional rows, because the gap after
 * 100 was previously locked.
 *
 * Non-repeatable reads (read committed) In READ COMMITTED, a read view is created at the start of
 * each statement. This means that a SELECT made at 5AM may show different results from the same
 * SELECT run at 5PM, even in the same transaction. This is because in READ COMMITTED the read view
 * for the transaction lasts only as long as each statement execution. As a result, consecutive
 * executions of the same statement may show different results.
 *
 * This is called the ‘phantom row’ problem.
 *
 * In addition, in READ COMMITTED gap locks are never created. Since there is no gap lock, the
 * example SELECT .. FOR UPDATE above will not prevent insertions of new rows into the table by
 * other transactions. Thus, locking rows with SELECT … FOR UPDATE (ie “where id> 100”) and
 * subsequently updating rows with “where id> 100” (even in the same transaction) may result in more
 * rows being updated than were earlier locked. This is because new rows may have been inserted in
 * the table between the statements since there was no gap lock created for the SELECT … FOR UPDATE.
 *
 * A non-repeatable read occurs, when during the course of a transaction, a row is retrieved twice
 * and the values within the row differ between reads. A phantom read occurs when, in the course of
 * a transaction, two identical queries are executed, and the collection of rows returned by the
 * second query is different from the first.
 *
 * https://www.percona.com/blog/2012/08/28/differences-between-read-committed-and-repeatable-read-transaction-isolation-levels/
 * http://highscalability.com/blog/2011/2/10/database-isolation-levels-and-their-effects-on-performance-a.html
 * https://blog.pythian.com/understanding-mysql-isolation-levels-repeatable-read/
 *
 * ----------------------------
 *
 * Crash Recovery:
 *
 * Rollback segment is responsible for keeping rollback data. The InnoDB UNDO tablespace(s) host
 * rollback segments that hold rollback information related to database changes. This information is
 * used to rollback a transaction and to retrieve the previous version of a record that has been
 * updated or deleted for multi-version concurrency control (MVCC). Once a transaction is committed,
 * InnoDB will discard the related UNDO log records. UNDO log records for updates or deletes will be
 * kept around as long as there exists an open transaction that may access older versions of the
 * records. When all such open transactions are committed then the associated UNDO log records can
 * be discarded. To provide consistent reads, InnoDB writes data modified by active transactions in
 * an area called the rollback segment. This single segment is protected by a single mutex, which is
 * a major cause of contention for write-intensive workloads.
 *
 * InnoDB Crash Recovery:
 *
 * To recover from a MySQL server crash, the only requirement is to restart the MySQL server. InnoDB
 * automatically checks the logs and performs a roll-forward of the database to the present. InnoDB
 * automatically rolls back uncommitted transactions that were present at the time of the crash.
 * During recovery, mysqld displays output similar to this:
 *
 * <pre>
 * InnoDB: Log scan progressed past the checkpoint lsn 452854464
 * InnoDB: Database was not shut down normally!
 * InnoDB: Starting crash recovery.
 * InnoDB: Reading tablespace information from the .ibd files...
 * InnoDB: Restoring possible half-written data pages from the doublewrite
 * InnoDB: buffer...
 * InnoDB: Doing recovery: scanned up to log sequence number 457028695
 * InnoDB: 1 transaction(s) which must be rolled back or cleaned up
 * InnoDB: in total 990682 row operations to undo
 * InnoDB: Trx id counter is 500
 * InnoDB: Starting an apply batch of log records to the database...
 * InnoDB: Progress in percents: 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21
 * 22 23 24 25 26 27 28 29 30 31 32 33 34 35 36 37 38 39 40 41 42 43 44 45
 * 46 47 48 49 50 51 52 53 54 55 56 57 58 59 60 61 62 63 64 65 66 67 68 69
 * 70 71 72 73 74 75 76 77 78 79 80 81 82 83 84 85 86 87 88 89 90 91 92 93
 * 94 95 96 97 98 99
 * InnoDB: Apply batch completed
 * InnoDB: Waiting for the background threads to start
 * InnoDB: Starting in background the rollback of uncommitted transactions
 * InnoDB: Rolling back trx with id 3B1, 990682 rows to undo
 * ...
 * InnoDB: 5.5.55 started; log sequence number 457028695
 * ...
 * ./mysqld: ready for connections.
 * </pre>
 *
 * The InnoDB crash recovery process consists of several steps:
 *
 * 1. Redo log application
 *
 * Redo log application is the first step and is performed during initialization, before accepting
 * any connections. If all changes are flushed from the buffer pool to the tablespaces (ibdata* and
 * *.ibd files) at the time of the shutdown or crash, redo log application is skipped. InnoDB also
 * skips redo log application if redo log files are missing at startup.
 *
 * Removing redo logs to speed up recovery is not recommended, even if some data loss is acceptable.
 * Removing redo logs should only be considered after a clean shutdown, with innodb_fast_shutdown
 * set to 0 or 1.
 *
 * 2. Roll back of incomplete transactions
 *
 * Incomplete transactions are any transactions that were active at the time of crash or fast
 * shutdown. The time it takes to roll back an incomplete transaction can be three or four times the
 * amount of time a transaction is active before it is interrupted, depending on server load.
 *
 * You cannot cancel transactions that are being rolled back. In extreme cases, when rolling back
 * transactions is expected to take an exceptionally long time, it may be faster to start InnoDB
 * with an innodb_force_recovery setting of 3 or greater. See Section 14.23.2, “Forcing InnoDB
 * Recovery”.
 *
 * 3. Change buffer merge
 *
 * Applying changes from the change buffer (part of the system tablespace) to leaf pages of
 * secondary indexes, as the index pages are read to the buffer pool.
 *
 * 4. Purge
 *
 * Deleting delete-marked records that are no longer visible to active transactions.
 *
 * The steps that follow redo log application do not depend on the redo log (other than for logging
 * the writes) and are performed in parallel with normal processing. Of these, only rollback of
 * incomplete transactions is special to crash recovery. The insert buffer merge and the purge are
 * performed during normal processing.
 *
 * After redo log application, InnoDB attempts to accept connections as early as possible, to reduce
 * downtime. As part of crash recovery, InnoDB rolls back transactions that were not committed or in
 * XA PREPARE state when the server crashed. The rollback is performed by a background thread,
 * executed in parallel with transactions from new connections. Until the rollback operation is
 * completed, new connections may encounter locking conflicts with recovered transactions.
 *
 * In most situations, even if the MySQL server was killed unexpectedly in the middle of heavy
 * activity, the recovery process happens automatically and no action is required of the DBA. If a
 * hardware failure or severe system error corrupted InnoDB data, MySQL might refuse to start. In
 * this case, see Section 14.23.2, “Forcing InnoDB Recovery”.
 *
 * https://dev.mysql.com/doc/refman/5.5/en/innodb-recovery.html
 *
 * Refreshing UNDO online:
 * https://mysqlserverteam.com/online-truncate-of-innodb-undo-tablespaces/#comments
 *
 * No. of concurrent transaction MySQL (InnoDB) can perform:
 * https://dev.mysql.com/doc/refman/5.7/en/innodb-undo-logs.html
 *
 * @author Prince Raj
 */
public class Database {
}
