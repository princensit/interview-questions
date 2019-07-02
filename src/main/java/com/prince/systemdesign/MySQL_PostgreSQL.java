package com.prince.systemdesign;

/**
 * https://eng.uber.com/mysql-migration/
 *
 * <pre>
 * Postgres limitations:
 *
 * 1. Inefficient architecture for writes
 * 2. Inefficient data replication
 * 3. Issues with table corruption
 * 4. Poor replica MVCC support
 * 5.Difficulty upgrading to newer releases
 *
 * RDBMS must perform a few key tasks:
 * 1. Provide insert/update/delete capabilities
 * 2. Provide capabilities for making schema changes
 * 3. Implement a multiversion concurrency control (MVCC) mechanism so that different connections have a transactional
 *    view of the data they work with
 * </pre>
 *
 * Postgres by design create immutable row data (tuple). These tuples are uniquely identified by
 * what Postgres calls a ctid. A ctid conceptually represents the on-disk location (i.e., physical
 * disk offset) for a tuple.
 *
 * Multiple ctids can potentially describe a single row (e.g., when multiple versions of the row
 * exist for MVCC purposes, or when old versions of a row have not yet been reclaimed by the
 * autovacuum process).
 *
 * When we insert a new row into a table, Postgres needs to replicate it if streaming replication is
 * enabled. For crash recovery purposes, the database already maintains a write-ahead log (WAL) and
 * uses it to implement two-phase commit. The database must maintain this WAL even when streaming
 * replication is not enabled because the WAL allows the atomicity and durability aspects of ACID.
 *
 * We can understand the WAL by considering what happens if the database crashes unexpectedly, like
 * during a sudden power loss. The WAL represents a ledger of the changes the database plans to make
 * to the on-disk contents of tables and indexes. When the Postgres daemon first starts up, the
 * process compares the data in this ledger with the actual data on disk. If the ledger contains
 * data that isn’t reflected on disk, the database corrects any tuple or index data to reflect the
 * data indicated by the WAL. It then rolls back any data that appears in the WAL but is from a
 * partially applied transaction (meaning that the transaction was never committed).
 *
 * Postgres implements streaming replication by sending the WAL on the master database to replicas.
 * Each replica database effectively acts as if it’s in crash recovery, constantly applying WAL
 * updates just as it would if it were starting up after a crash. The only difference between
 * streaming replication and actual crash recovery is that replicas in “hot standby” mode serve read
 * queries while applying the streaming WAL, whereas a Postgres database that’s actually in crash
 * recovery mode typically refuses to serve any queries until the database instance finishes the
 * crash recovery process.
 *
 * Because the WAL is actually designed for crash recovery purposes, it contains low-level
 * information about the on-disk updates. The content of the WAL is at the level of the actual
 * on-disk representation of row tuples and their disk offsets (i.e., the row ctids). If you pause a
 * Postgres master and replica when the replica is fully caught up, the actual on-disk content on
 * the replica exactly matches what’s on the master byte for byte. Therefore, tools like rsync can
 * fix a corrupted replica if it gets out of date with the master.
 *
 * Write Amplification The first problem with Postgres’s design is known in other contexts as write
 * amplification. Typically, write amplification refers to a problem with writing data to SSD disks:
 * a small logical update (say, writing a few bytes) becomes a much larger, costlier update when
 * translated to the physical layer. The same issue arises in Postgres. Let's say there are 4
 * indexes, then each update will result into four updates; each of these writes needs to be
 * reflected in the WAL as well, so the total number of writes on disk is even larger.
 *
 * This write amplification issue naturally translates into the replication layer as well because
 * replication occurs at the level of on-disk changes. Instead of replicating a small logical
 * record, such as “Change the birth year for ctid D to now be 770,” the database instead writes out
 * WAL entries for all four of the writes we just described, and all four of these WAL entries
 * propagate over the network. Thus, the write amplification problem also translates into a
 * replication amplification problem, and the Postgres replication data stream quickly becomes
 * extremely verbose, potentially occupying a large amount of bandwidth.
 *
 * Postgres 9.2 bug: Replicas followed timeline switches incorrectly, causing some of them to
 * misapply some WAL records. Because of this bug, some records that should have been marked as
 * inactive by the versioning mechanism weren’t actually marked inactive.
 *
 * SELECT * FROM users WHERE id = 4;
 *
 * This query would return two records: the original al-Khwārizmī row with the 780 CE birth year,
 * plus the new al-Khwārizmī row with the 770 CE birth year. If we were to add ctid to the WHERE
 * list, we would see different ctid values for the two returned records, as one would expect for
 * two distinct row tuples.
 *
 * Because the bug affected all of the servers, the corrupted rows were different on different
 * replica instances, meaning that on one replica row X might be bad and row Y would be good, but on
 * another replica row X might be good and row Y might be bad. In fact, we were unsure about the
 * number of replicas with corrupted data and about whether the problem had affected the master.
 *
 * From what we could tell, the problem only manifested on a few rows per database, but we were
 * extremely worried that, because replication happens at the physical level, we could end up
 * completely corrupting our database indexes. An essential aspect of B-trees are that they must be
 * periodically rebalanced, and these rebalancing operations can completely change the structure of
 * the tree as sub-trees are moved to new on-disk locations. If the wrong data is moved, this can
 * cause large parts of the tree to become completely invalid.
 *
 * Postgres Upgrades: Because replication records work at the physical level, it’s not possible to
 * replicate data between different general availability releases of Postgres. A master database
 * running Postgres 9.3 cannot replicate to a replica running Postgres 9.2, nor can a master running
 * 9.2 replicate to a replica running Postgres 9.3.
 *
 * The Architecture of MySQL:
 *
 * Like Postgres, InnoDB supports advanced features like MVCC and mutable data. The most important
 * architectural difference is that while Postgres directly maps index records to on-disk locations,
 * InnoDB maintains a secondary structure. Instead of holding a pointer to the on-disk row location
 * (like the ctid does in Postgres), InnoDB secondary index records hold a pointer to the primary
 * key value. Thus, a secondary index in MySQL associates index keys with primary keys:
 *
 * In order to perform an index lookup on the (first, last) index, we actually need to do two
 * lookups. The first lookup searches the table and finds the primary key for a record. Once the
 * primary key is found, a second lookup searches the primary key index to find the on-disk location
 * for the row.
 *
 * This design means that InnoDB is at a slight disadvantage to Postgres when doing a secondary key
 * lookup, since two indexes must be searched with InnoDB compared to just one for Postgres.
 * However, because the data is normalized, row updates only need to update index records that are
 * actually changed by the row update. Additionally, InnoDB typically does row updates in place. If
 * old transactions need to reference a row for the purposes of MVCC MySQL copies the old row into a
 * special area called the rollback segment.
 *
 * This design also makes vacuuming and compaction more efficient. All of the rows that are eligible
 * to be vacuumed are available directly in the rollback segment. By comparison, the Postgres
 * autovacuum process has to do full table scans to identify deleted rows.
 *
 * <pre>
 * MySQL supports multiple different replication modes:
 *
 * 1. Statement-based replication replicates logical SQL statements (e.g., it would literally replicate literal
 *    statements such as: UPDATE users SET birth_year=770 WHERE id = 4)
 * 2. Row-based replication replicates altered row records
 * 3. Mixed replication mixes these two modes
 * </pre>
 *
 * There are various tradeoffs to these modes. Statement-based replication is usually the most
 * compact but can require replicas to apply expensive statements to update small amounts of data.
 * On the other hand, row-based replication, akin to the Postgres WAL replication, is more verbose
 * but results in more predictable and efficient updates on the replicas.
 *
 * In MySQL, only the primary index has a pointer to the on-disk offsets of rows. This has an
 * important consequence when it comes to replication. The MySQL replication stream only needs to
 * contain information about logical updates to rows. The replication updates are of the variety
 * “Change the timestamp for row X from T_1 to T_2.” Replicas automatically infer any index changes
 * that need to be made as the result of these statements.
 *
 * This design difference means that the MySQL replication binary log is significantly more compact
 * than the PostgreSQL WAL stream.
 *
 * Since the MySQL replication stream has logical updates, replicas can have true MVCC semantics;
 * therefore read queries on replicas won’t block the replication stream. By contrast, the Postgres
 * WAL stream contains physical on-disk changes, so Postgres replicas cannot apply replication
 * updates that conflict with read queries, so they can’t implement MVCC.
 *
 * MySQL’s replication architecture means that if bugs do cause table corruption, the problem is
 * unlikely to cause a catastrophic failure. Replication happens at the logical layer, so an
 * operation like rebalancing a B-tree can never cause an index to become corrupted. A typical MySQL
 * replication issue is the case of a statement being skipped (or, less frequently, applied twice).
 * This may cause data to be missing or invalid, but it won’t cause a database outage.
 *
 * Finally, MySQL’s replication architecture makes it trivial to replicate between different MySQL
 * releases. MySQL only increments its version if the replication format changes, which is unusual
 * between various MySQL releases. The typical way to do a MySQL upgrade is to apply the update to
 * one replica at a time, and once you update all replicas, you promote one of them to become the
 * new master. This can be done with almost zero downtime, and it simplifies keeping MySQL up to
 * date.
 *
 * Other MySQL Design Advantages:
 *
 * The Buffer Pool:
 *
 * First, caching works differently in the two databases. Postgres allocates some memory for
 * internal caches, but these caches are typically small compared to the total amount of memory on a
 * machine. To increase performance, Postgres allows the kernel to automatically cache recently
 * accessed disk data via the page cache. For instance, our largest Postgres replicas have 768 GB of
 * memory available, but only about 25 GB of that memory is actually RSS memory faulted in by
 * Postgres processes. This leaves more than 700 GB of memory free to the Linux page cache.
 *
 * The problem with this design is that accessing data via the page cache is actually somewhat
 * expensive compared to accessing RSS memory. To look up data from disk, the Postgres process
 * issues lseek(2) and read(2) system calls to locate the data. Each of these system calls incurs a
 * context switch, which is more expensive than accessing data from main memory. In fact, Postgres
 * isn’t even fully optimized in this regard: Postgres doesn’t make use of the pread(2) system call,
 * which coalesces seek + read operations into a single system call.
 *
 * By comparison, the InnoDB storage engine implements its own LRU in something it calls the InnoDB
 * buffer pool. This is logically similar to the Linux page cache but implemented in userspace.
 * While significantly more complicated than Postgres’s design, the InnoDB buffer pool design has
 * some huge upsides:
 *
 * 1. It makes it possible to implement a custom LRU design. For instance, it’s possible to detect
 * pathological access patterns that would blow out the LRU and prevent them from doing too much
 * damage.
 *
 * 2. It results in fewer context switches. Data accessed via the InnoDB buffer pool doesn’t require
 * any user/kernel context switches. The worst case behavior is the occurrence of a TLB miss, which
 * is relatively cheap and can be minimized by using huge pages.
 *
 * Connection Handling:
 *
 * MySQL implements concurrent connections by spawning a thread-per-connection. This is relatively
 * low overhead; each thread has some memory overhead for stack space, plus some memory allocated on
 * the heap for connection-specific buffers. It’s not uncommon to scale MySQL to 10,000 or so
 * concurrent connections, and in fact we are close to this connection count on some of our MySQL
 * instances today.
 *
 * Postgres, however, use a process-per-connection design. This is significantly more expensive than
 * a thread-per-connection design for a number of reasons. Forking a new process occupies more
 * memory than spawning a new thread. Additionally, IPC is much more expensive between processes
 * than between threads.
 *
 * @author Prince Raj
 */
public class MySQL_PostgreSQL {
}
