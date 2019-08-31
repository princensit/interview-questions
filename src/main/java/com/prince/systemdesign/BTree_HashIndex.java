package com.prince.systemdesign;

/**
 * <pre>
 * - Hash indexes don’t support partial key matching, if you have an index on (A,B) and your query’s WHERE clause refers
 *   only to A, the index won’t help
 * - MySQL can’t use hash indexes for sorting because hash index don’t store rows in sorted order.
 * - Hash indexes support only equality comparisons, they can’t speed up range queries, such as WHERE price > 100
 * - Hash index's performance depends upon how good is the hash function, when there is a collision, the entire linked
 *   list is traversed.
 * - In case of collisions, deletion and updation operation is expensive as entire linked list is traversed and each
 *   node is compared.
 *
 * - Hash index works better than B-Tree index in case of equality comparison
 * - Hash index works better in data-warehousing applications where a classic “star” schema requires many joins to
 *   lookup tables
 * </pre>
 *
 * @author Prince Raj
 */
public class BTree_HashIndex {
}
