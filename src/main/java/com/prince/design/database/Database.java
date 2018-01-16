package com.prince.design.database;

/**
 * @author Prince Raj
 */
public class Database {

    public static void main(String[] args) {
        System.out.println("In the context of databases, cardinality refers to the uniqueness of data values contained in a column. High cardinality means that the column contains a large percentage of totally unique values. Low cardinality means that the column contains a lot of “repeats” in its data range.");
        System.out.println("Bitmap indexes have traditionally been considered to work well for low-cardinality columns, which have a modest number of distinct values, either absolutely, or relative to the number of records that contain the data. Ex - Boolean");
        System.out
                .println("The most expensive operations involving hard disks are seeks. In order to improve overall performance, related data should be stored in a fashion to minimize the number of seeks. This is known as locality of reference, and the basic concept appears in a number of different contexts.");

        System.out.println("=======> Row-oriented systems");
        System.out.println("Row-oriented systems");
        System.out.println("001:10,Smith,Joe,40000;\n"
                + "002:12,Jones,Mary,50000;\n"
                + "003:11,Johnson,Cathy,44000;\n"
                + "004:22,Jones,Bob,55000;");

        System.out.println();

        System.out.println("\n=======> Column-oriented systems");
        System.out.println("10:001,12:002,11:003,22:004;\n"
                + "Smith:001,Jones:002,Johnson:003,Jones:004;\n"
                + "Joe:001,Mary:002,Cathy:003,Bob:004;\n"
                + "40000:001,50000:002,44000:003,55000:004;");
        System.out.println("…;Smith:001;Jones:002,004;Johnson:003;…");
        System.out
                .println("A row-based system can retrieve the row in a single disk read, whereas numerous disk operations to collect data from multiple columns are required from a columnar database.");
        System.out
                .println("Partitioning, indexing, caching, views, OLAP cubes, and transactional systems such as write-ahead logging or multiversion concurrency control all dramatically affect the physical organization of either system. That said, online transaction processing (OLTP)-focused RDBMS systems are more row-oriented, while online analytical processing (OLAP)-focused systems are a balance of row-oriented and column-oriented.");

        System.out.println();
        System.out
                .println("Serial ATA (SATA) hard drive has a maximum transfer rate of 600 MB/second (Megabytes per second) while DDR3 SDRAM Memory can reach transfer rates of 17 GB/s (Gigabytes per second), nearly 30 times as fast.");
        System.out
                .println("Columnar databases boost performance by reducing the amount of data that needs to be read from disk, both by efficiently compressing the similar columnar data and by reading only the data necessary to answer the query.");
        System.out.println("In practice, columnar databases are well-suited for OLAP-like workloads (e.g., data warehouses) which typically involve highly complex queries over all data (possibly petabytes)");
    }
}
