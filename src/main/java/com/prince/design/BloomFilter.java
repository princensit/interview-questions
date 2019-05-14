package com.prince.design;

/**
 * Bloom filters are a probabilistic data structure for set membership testing that may yield false
 * positives.
 *
 * A large bit vector represents the set. An element is added to the set by computing 'n' hash
 * functions of the element and setting the corresponding bits. An element is deemed to be in the
 * set if the bits at all 'n' of the element's hash locations are set. Hence, a document may
 * incorrectly be deemed to be in the set, but false negatives are not possible.
 *
 * A bloom filter doesn't store the elements themselves, this is the crucial point. You don't use a
 * bloom filter to test if an element is present, you use it to test whether it's certainly not
 * present, since it guarantees no false negatives. This lets you not do extra work for elements
 * that don't exist in a set (such as disk IO to look them up).
 *
 * The basic bloom filter supports two operations: test and add.
 *
 * <pre>
 * Test is used to check whether a given element is in the set or not. If it returns:
 * 1. false then the element is definitely not in the set.
 * 2. true then the element is probably in the set. The false positive rate is a function of the bloom filter's size
 *    and the number and independence of the hash functions used.
 * </pre>
 *
 * Add simply adds an element to the set. Removal is impossible without introducing false negatives,
 * but extensions to the bloom filter are possible that allow removal e.g. counting filters.
 *
 * The classic example is using bloom filters to reduce expensive disk (or network) lookups for
 * non-existent keys.
 *
 * The bloom filter essentially consists of a bit vector of length m, represented by the central
 * column. To add an item to the bloom filter, we feed it to k different hash functions and set the
 * bits at the resulting positions.
 *
 * If at least one of these k indices in the bit array is set to zero then the element is a new
 * element else this could be an existing element in the set.
 *
 * @author Prince Raj
 */
public class BloomFilter {
}
