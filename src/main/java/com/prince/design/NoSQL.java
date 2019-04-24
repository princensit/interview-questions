package com.prince.design;

/**
 * @author Prince Raj
 */
public class NoSQL {

    public static void main(String[] args) {
        print();
    }

    private static void print() {
        System.out.println("Key-Value Cache ==> Memcached, Terracotta, Velocity");
        System.out.println("Key-Value Store ==> Aerospike");
        System.out.println("Key-Value Store (Eventually-Consistent) ==> Dynamo, Riak");
        System.out.println("Key-Value Store (Ordered) ==> MemcacheDB");
        System.out.println("Data-Structures Server ==> Redis");
        System.out.println("Document Store ==> MongoDB, CouchDB");
        System.out.println("Wide Column Store (Columnar databases best suited for analyzing large datasets) ==> BigTable, Cassandra, HBase, Redshift");
        System.out.println("Graph Store ==> Neo4J, InfiniteGraph");
    }
}
