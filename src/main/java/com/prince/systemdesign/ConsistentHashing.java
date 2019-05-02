package com.prince.systemdesign;

/**
 * <pre>
 * Consistent Hashing:
 *
 * Distributed Hash Table (DHT) is one of the fundamental components used in distributed scalable
 * systems. Hash Tables need a key, a value, and a hash function where hash function maps the key to
 * a location where the value is stored.
 *
 * index = hash_function(key)
 *
 * Suppose we are designing a distributed caching system. Given ‘n’ cache servers, an intuitive hash
 * function would be ‘key % n’. It is simple and commonly used. But it has two major drawbacks:

 * 1. It is NOT horizontally scalable. Whenever a new cache host is added to the system, all
 * existing mappings are broken.
 * 2. It may NOT be load balanced, especially for non-uniformly distributed data.
 *
 * In such situations, consistent hashing is a good way to improve the caching system.
 *
 * Consistent hashing is a very useful strategy for distributed caching system and DHTs. It allows
 * us to distribute data across a cluster in such a way that will minimize reorganization when nodes
 * are added or removed. Hence, the caching system will be easier to scale up or scale down.
 *
 * See {@link com.prince.design.ConsistentHashing} for implementation.
 * </pre>
 *
 * @author Prince Raj
 */
public class ConsistentHashing {
}
