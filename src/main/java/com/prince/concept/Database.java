package com.prince.concept;

/**
 * https://github.com/CyC2018/Interview-Notebook/blob/master/notes/MySQL.md
 *
 * <pre>
 * InnoDB vs MyISAM:
 * 1. Transactions: InnoDB is transactional.
 * 2. Backup: InnoDB supports online hot backup.
 * 3. Crash recovery: MyISAM is much more likely than InnoDB to crash after a crash, and recovery is slower.
 * 4. Concurrency: MyISAM only supports table-level locking, and InnoDB also supports row-level locking.
 * 5. Other features: MyISAM supports compressed tables and spatial data indexes.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Database {

}
