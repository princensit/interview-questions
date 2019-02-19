package com.prince.spring.database_isolation;

/**
 * @author Prince Raj
 */
public class Main {

    /**
     * <pre>
     * Read Phenomena:
     * 1. Dirty reads:
     *    Occurs when a txn is allowed to read a data from a row that has been modified from another txn and not yet
     *    committed.
     *
     *    Txn 1                       Txn 2
     *    select age from
     *    user where id = 1;
     *        --> 20
     *                                update user set age = 21
     *                                where id = 1;
     *    select age from
     *    user where id = 1;
     *        --> 21
     *                                ROLLBACK;
     *
     * 2. Non repeatable reads:
     *    A row is retrieved twice but the values within the row differ between reads.
     *
     *    Txn 1                       Txn 2
     *    select age from
     *    user where id = 1;
     *        --> 20
     *                                update user set age = 21
     *                                where id = 1;
     *                                COMMIT;
     *    select age from
     *    user where id = 1;
     *    COMMIT;
     *        --> 21
     *
     * 3. Phantom reads:
     *    2 identical queries are executed for range but both the results are different.
     *
     *    Txn 1                       Txn 2
     *    select * from
     *    user where age
     *    between 10 and 30;
     *                                insert into users(id, age)
     *                                values (3, 27);
     *                                COMMIT;
     *    select * from
     *    user where age
     *    between 10 and 30;
     *    COMMIT;
     *
     * Isolation levels:
     * 1. Serializable (highest isolation level):
     *    -> Read and write locks (acquired on selected data) to be released at the end of txn.
     *    -> Also, range locks must be acquired when a SELECT query uses ranges WHERE clause.
     *    -> A serial execution is one in which each SQL txn executes to complete before next SQL txn begins.
     * 2. Repeatable reads:
     *    -> Read and write locks (acquired on selected data) until the end of txn.
     *    -> But range locks are not managed so phantom reads can occur.
     *    -> Write skew possible at this isolation level.
     *    -> A phenomena where 2 writes are allowed to same columns in a row by 2 writers (who have previously read the
     *       columns they are updating), resulting in a row having data that is a mix of 2 txns.
     * 3. Read committed:
     *    -> Write locks (acquired on selected data) until the end of txn, but read locks are released as soon as SELECT
     *       operation is performed. So, non-repeatable reads can occur.
     *    -> But range locks are not managed so phantom reads can occur.
     *    -> It guarantees that any data read is committed at the moment is read. It simply restricts the reader from
     *       seeing any intermediate/uncommitted data. It makes no promise that if the txn re-issues the read, it will
     *       find the same data, data is free to change after read.
     * 4. Read uncommitted (lowest isolation level):
     *    -> Dirty reads are allowed.
     *
     * SELECT ... FOR UPDATE to acquire exclusive write locks on accessed rows.
     *
     * Isolation level vs Read phenomena:
     * | Isolation level   | Dirty Reads | Non Repeatable reads | Phantom Reads |
     * | Read Uncommitted  |   Yes       |    Yes               |    Yes        |
     * | Read Committed    |   No        |    Yes               |    Yes        |
     * | Repeatable Read   |   No        |    No                |    Yes        |
     * | Serializable      |   No        |    No                |    No         |
     *
     * Isolation level vs lock duration:
     * In lock based concurrency control, isolation level determines the duration of locks that are held.
     * Locks are held until txn commits.
     * Locks are held only during the currently executing statement. Note that if locks are release after a statement,
     * the underlying data could be changed by another txn before the current txn commits, thus creating violation.
     *
     *                         (Locks are held...)
     * | Isolation level   | Write operation     | Read operation        | Range operation       |
     * | Read Uncommitted  |   during statement  |    during statement   |    during statement   |
     * | Read Committed    |   until commit      |    during statement   |    during statement   |
     * | Repeatable Read   |   until commit      |    until commit       |    during statement   |
     * | Serializable      |   until commit      |    until commit       |    until commit       |
     *
     * </pre>
     *
     * @param args
     */
    public static void main(String[] args) {

    }
}
