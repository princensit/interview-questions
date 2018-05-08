package com.prince.javaconcepts;

/**
 * <pre>
 *  Java Collections Framework provides a well designed set of interfaces and classes that support operations on a
 *  collections of objects.
 *
 *  Collection, which represents a group of objects known as its elements.
 *  Set, which is a collection that cannot contain duplicate elements.
 *  List, which is an ordered collection and can contain duplicate elements.
 *  Map, which is an object that maps keys to values and cannot contain duplicate keys.
 *
 *  Collection doesn't extend Cloneable and Serializable interfaces because implications of either cloning or serialization
 *  come into play when dealing with actual implementations. Thus, the concrete implementations of collections should
 *  decide how they can be cloned or serialized.
 *
 *  The ListIterator implements the Iterator interface and contains extra functionality, such as adding an element,
 *  replacing an element, getting the index position for previous and next elements, etc.
 *
 *  The Iterator's fail-safe property works with the clone of the underlying collection and thus, it is not affected by
 *  any modification in the collection.
 *
 *  All the collection classes in java.util package are fail-fast, while the collection classes in java.util.concurrent
 *  are fail-safe. Fail-fast iterators throw a ConcurrentModificationException, while fail-safe iterator never throws
 *  such an exception.
 *
 *  HashMap vs HashTable:
 *  HashMap allows null keys and values but HashTable doesn't allow null keys or null values.
 *  HashMap is not synchronized but HashTable is synchronized.
 *  Both HashMap and HashTable are fail-fast.
 *
 *  Arrays have fixed size, while an ArrayList is dynamic.
 *  An ArrayList provides more methods and features, such as addAll, removeAll, iterator, etc.
 *  An ArrayList is an index based data structure backed by an Array. It provides random access to its elements with a
 *  performance equal to O(1). ArrayList implements RandomAccess interface.
 *  LinkedList is doubly linked list. The insertion, addition and removal operations of an element are faster in a
 *  LinkedList compared to an ArrayList, because there is no need of resizing an array or updating the index when an
 *  element is added in some arbitrary position inside the collection.
 *  A LinkedList consumes more memory than an ArrayList, because every node in a LinkedList stores two references, one
 *  for its previous element and one for its next element.
 *
 *  Comparable -> compareTo()
 *  Comparator -> compare() and equals()
 *
 *  The PriorityQueue is an unbounded queue. Comparator is provided for ordering the elements. It doesn't allow null
 *  values. It is not thread-safe and requires O(logn) complexity for enqueing and dequeing operations.
 *
 *  The Big-O notation simply describes how well an algorithm scales or performs in the worst case scenario as the number
 *  of elements in a data structure increases. The Big-O notation can also be used to describe other behavior such as
 *  memory consumption. Since the collection classes are actually data structures, we usually use the Big-O notation to
 *  chose the best implementation to use, based on time, memory and performance. Big-O notation can give a good indication
 *  about performance for large amounts of data.
 *
 *  Best practices relating to the Java Collection framework:
 *  1. Use Array instead of ArrayList if we know the fixed size.
 *  2. Use initial capacity for supported collections to avoid re-hashing.
 *  3. Always use Generics for type-safety, readability, and robustness. Also, by using Generics you avoid the
 *     ClassCastException during runtime.
 *  4. Use immutable classes provided by JDK as a key in a Map, in order to avoid the implementation of the hashCode and
 *     equals methods for our custom class.
 *  5. Program in terms of interface not implementation.
 *  6. Return zero-length collections or arrays as opposed to returning a null in case the underlying collection is actually
 *     empty.
 *
 *  Enumeration vs Iterator:
 *  Iterator - hasNext(), next() and remove()
 *  Enumeration - hasMoreElements(), nextElement()
 *
 *  Enumeration is twice as fast as compared to an Iterator and uses very less memory. With Enumeration, you can only
 *  traverse the collection, but with iterator you can also remove elements while iterating. Enumeration is fail-safe
 *  and Iterator is fail-fast.
 *
 *  Iterator is a fail-fast in nature. i.e it throws ConcurrentModificationException if a collection is modified while
 *  iterating other than it’s own remove() method, where as Enumeration is fail-safe in nature. It doesn’t throw any
 *  exceptions if a collection is modified while iterating.
 *
 *  As Iterator is fail-fast in nature and doesn’t allow modification of a collection by other threads while iterating,
 *  it is considered as safe and secure than Enumeration.
 *
 *  HashSet - add, remove, and contains methods have constant time complexity O(1)
 *  TreeSet - add, remove, and contains methods have constant time complexity O(logn), since elements are sorted.
 *
 * </pre>
 *
 * @author Prince Raj
 */
public class Collections {

}
