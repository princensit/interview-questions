package com.prince.feature;

/**
 * @author Prince Raj
 */
public class Java8Features {

    public static void main(String[] args) {
        System.out.println("1. forEach() method in Iterable interface");
        System.out.println("   Iterator<Integer> it = myList.iterator();\n"
                + "\t\twhile(it.hasNext()){\n"
                + "\t\t\tInteger i = it.next();\n"
                + "\t\t\tSystem.out.println(\"Iterator Value::\"+i);\n"
                + "\t\t}\n"
                + "\t\t\n"
                + "\t\t//traversing through forEach method of Iterable with anonymous class\n"
                + "\t\tmyList.forEach(new Consumer<Integer>() {\n"
                + "\n"
                + "\t\t\tpublic void accept(Integer t) {\n"
                + "\t\t\t\tSystem.out.println(\"forEach anonymous class Value::\"+t);\n"
                + "\t\t\t}\n"
                + "\t\t});");
        System.out.println("2. Default and static methods in Interfaces");
        System.out.println("   This feature enables us to add new functionality in the interfaces without breaking the existing contract of the implementing classes.");
        System.out.println("   Ex - list.sort(ordering); instead of Collections.sort(list, ordering);");
        System.out.println("3. Functional Interfaces and Lambda Expressions");
        System.out.println("   An interface with exactly one abstract method becomes Functional Interface. @FunctionalInterface annotation although not necessary but facility to avoid accidental addition of abstract methods.");
        System.out.println("   Runnable r1 = (s) -> System.out.println(s);");
        System.out
                .println("   Another benefit of using lambda expression is that we can benefit from the Stream API sequential and parallel operations support.");
        System.out.println("4. Java Stream API for bulk data operations on Collections");
        System.out
                .println("   A new java.util.stream has been added to perform filter/map/reduce like operations with the collection. Stream API will allow sequential as well as parallel execution.");
        System.out.println("5. Java time API");
        System.out.println("6. Collection API improvements");
        System.out
                .println("   Map replaceAll(), compute(), merge() methods.\n"
                        + "Performance Improvement for HashMap class with Key Collisions\n"
                        + "Iterator default method forEachRemaining(Consumer action) to perform the given action for each remaining element until all elements have been processed or the action throws an exception.\n"
                        + "Collection default method removeIf(Predicate filter) to remove all of the elements of this collection that satisfy the given predicate.\n"
                        + "Collection spliterator() method returning Spliterator instance that can be used to traverse elements sequentially or parallel.");
        System.out.println("7. Concurrency API improvements");
        System.out
                .println("   ConcurrentHashMap compute(), forEach(), forEachEntry(), forEachKey(), forEachValue(), merge(), reduce() and search() methods.\n"
                        + "CompletableFuture that may be explicitly completed (setting its value and status).\n"
                        + "Executors newWorkStealingPool() method to create a work-stealing thread pool using all available processors as its target parallelism level.");
        System.out.println("8. Java IO improvements");
        System.out
                .println("   Files.list(Path dir) that returns a lazily populated Stream, the elements of which are the entries in the directory.\n"
                        + "Files.lines(Path path) that reads all lines from a file as a Stream.\n"
                        + "Files.find() that returns a Stream that is lazily populated with Path by searching for files in a file tree rooted at a given starting file.\n"
                        + "BufferedReader.lines() that return a Stream, the elements of which are lines read from this BufferedReader.");
        System.out.println("9. Miscellaneous Core API improvements");
        System.out
                .println("   ThreadLocal static method withInitial(Supplier supplier) to create instance easily.\n"
                        + "Comparator interface has been extended with a lot of default and static methods for natural ordering, reverse order etc.\n"
                        + "min(), max() and sum() methods in Integer, Long and Double wrapper classes.\n"
                        + "logicalAnd(), logicalOr() and logicalXor() methods in Boolean class.\n"
                        + "ZipFile.stream() method to get an ordered Stream over the ZIP file entries. Entries appear in the Stream in the order they appear in the central directory of the ZIP file.\n"
                        + "Several utility methods in Math class.\n"
                        + "jjs command is added to invoke Nashorn Engine.\n"
                        + "jdeps command is added to analyze class files\n"
                        + "JDBC-ODBC Bridge has been removed.\n"
                        + "PermGen memory space has been removed");
    }
}
