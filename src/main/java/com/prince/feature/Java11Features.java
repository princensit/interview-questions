package com.prince.feature;

/**
 * <pre>
 * 1. JDK11 New String Methods: strip() , stripLeading() , stripTrailing(), isBlank(),
 *    lines() (partitioned by line terminators), repeat(n)
 * 2. Files Utility Methods: writeString(), readString(), isSameFile()
 * 3. Pattern With Predicate Utility Methods
 * 4. destroy() and stop(Throwable obj) methods of Thread classes are removed, as the destroy() method has never done
 *    anything except throwing the NoSuchMethodError. The stop(Throwable) method hasn't done anything except throwing
 *    the  UnsupportedOperationException.
 * 5. Optional.isEmpty()
 * 6. JDK 11 removed the Java EE and CORBA modules from the Java SE Platform and the JDK. These modules were deprecated
 *    as part of Java SE 9, with the declared intent to remove them in a future release.
 * 7. Epsilon: A no-op Garbage Collector: This GC handles memory allocation but does not implement any actual memory
 *    reclamation mechanism. Once the available Java heap is exhausted, the JVM will shut down.
 * 8. TimeUnit convert(Duration duration): This method is used to convert the given time duration to this unit. This
 *    method differs from Duration.toNanos() in that it does not throw the ArithmeticException on numeric overflow.
 * </pre>
 *
 * @author Prince Raj
 */
public class Java11Features {
}
