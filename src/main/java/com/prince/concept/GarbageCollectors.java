package com.prince.concept;

/**
 * <pre>
 *
 * The purpose of garbage collection is to identify and discard those objects that are no longer needed by the application,
 * in order for the resources to be reclaimed and reused.
 *
 * System.gc() and Runtime.gc() - An indication to JVM to start a garbage collection. However, this it is up to the JVM
 * to start the garbage collection immediately or later in time.
 *
 * The finalize() method is called by the garbage collector, just before releasing the object’s memory. It is normally
 * advised to release resources like I/O buffers, database connections, etc. held by the object inside the finalize method.
 *
 * If an object reference is set to null, then the object will be available for garbage collection in the next cycle of
 * the garbage collector.
 *
 * PermGen stands for permanent generation. It is a space on heap, that holds metadata describing user classes.
 * Applications with large code-base may cause java.lang.OutOfMemoryError: PermGen
 *
 * The throughput garbage collector uses a parallel version of the young generation collector and is meant to be used
 * with applications that have medium to large data sets. On the other hand, the serial collector is usually adequate
 * for most small applications (those requiring heaps of up to approximately 100MB on modern processors).
 *
 * A Java object is subject to garbage collection when it becomes unreachable to the program in which it is currently
 * used
 *
 * Garbage Collection does occur in PermGen space and if PermGen space is full or cross a threshold, it can trigger a
 * full garbage collection. If you look carefully at the output of the garbage collector, you will find that PermGen
 * space is also garbage collected. This is the reason why correct sizing of PermGen space is important to avoid frequent
 * full garbage collections.
 *
 * In JDK 8, Metaspace is introduced in replacement of PermGen. With this, there are no more java.lang.OutOfMemoryError
 * errors. Please keep in mind that this new feature does not magically eliminate class and classloader memory leaks.
 *
 * A new flag is available (MaxMetaspaceSize), allowing you to limit the amount of native memory used for class metadata.
 * If you don’t specify this flag, the Metaspace will dynamically re-size depending of the application demand at runtime.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class GarbageCollectors {

}
