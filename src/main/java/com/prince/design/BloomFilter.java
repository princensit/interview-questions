package com.prince.design;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

/**
 * Bloom filters are a probabilistic data structure for set membership testing that may yield false
 * positives.
 * <p>
 * A large bit vector represents the set. An element is added to the set by computing 'n' hash
 * functions of the element and setting the corresponding bits. An element is deemed to be in the
 * set if the bits at all 'n' of the element's hash locations are set. Hence, a document may
 * incorrectly be deemed to be in the set, but false negatives are not possible.
 * <p>
 * A bloom filter doesn't store the elements themselves, this is the crucial point. You don't use a
 * bloom filter to test if an element is present, you use it to test whether it's certainly not
 * present, since it guarantees no false negatives. This lets you not do extra work for elements
 * that don't exist in a set (such as disk IO to look them up).
 * <p>
 * The basic bloom filter supports two operations: test and add.
 *
 * <pre>
 * Test is used to check whether a given element is in the set or not. If it returns:
 * 1. false then the element is definitely not in the set.
 * 2. true then the element is probably in the set. The false positive rate is a function of the bloom filter's size
 *    and the number and independence of the hash functions used.
 * </pre>
 * <p>
 * Add simply adds an element to the set. Removal is impossible without introducing false negatives,
 * but extensions to the bloom filter are possible that allow removal e.g. counting filters.
 * <p>
 * The classic example is using bloom filters to reduce expensive disk (or network) lookups for
 * non-existent keys.
 * <p>
 * The bloom filter essentially consists of a bit vector of length m, represented by the central
 * column. To add an item to the bloom filter, we feed it to k different hash functions and set the
 * bits at the resulting positions.
 * <p>
 * If at least one of these k indices in the bit array is set to zero then the element is a new
 * element else this could be an existing element in the set.
 *
 * @author Prince Raj
 */
public class BloomFilter {

  private BitSet hashes;
  private int capacity;
  private Set<Object> dictionary;
  private MessageDigest md;

  /**
   * create a new bloom filter.
   *
   * @param capacity a desired size of container in bits
   */
  private BloomFilter(int capacity) {
    this.capacity = capacity;
    this.hashes = new BitSet(capacity);
    this.dictionary = new HashSet<>();
    try {
      this.md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException("Error : MD5 Hash not found");
    }
  }

  /**
   * Entry point for this class.
   *
   * @param args arguments
   */
  public static void main(String[] args) {
    int capacity = 20;
    BloomFilter bloomFilter = new BloomFilter(capacity);

    String[] words = {"hello", "world", "go", "name", "duck"};
    for (String word : words) {
      bloomFilter.add(word);
    }

    String[] wordsToBeChecked = {"hello", "gone", "duck123", "qwqw"};
    for (String word : wordsToBeChecked) {
      if (bloomFilter.contains(word)) {
        System.out
            .println(word + " -> may be in the dictionary, so check it for final confirmation.");
      } else {
        System.out.println(word
            + " -> definitely not in the dictionary, so no need to do expensive lookup.");
      }
    }

    System.out.println("\nDisplaying items in dictionary:");
    bloomFilter.printDictionary();
  }

  private void add(Object obj) {
    int[] tempArr = getSetArray(obj);
    for (int i : tempArr) {
      hashes.set(i);
    }

    dictionary.add(obj);
  }

  private boolean contains(Object obj) {
    int[] tempArr = getSetArray(obj);
    for (int i : tempArr) {
      if (!hashes.get(i)) {
        return false;
      }
    }
    return true;
  }

  private void printDictionary() {
    System.out.println(dictionary);
  }

  private int[] getSetArray(Object obj) {
    int[] tempArr = new int[capacity];
    tempArr[0] = getHash(obj.hashCode());
    for (int i = 1; i < capacity; i++) {
      tempArr[i] = getHash(tempArr[i - 1]);
    }

    for (int i = 0; i < capacity; i++) {
      tempArr[i] = tempArr[i] % capacity;
    }

    return tempArr;
  }

  private int getHash(int i) {
    md.reset();
    byte[] bytes = ByteBuffer.allocate(4).putInt(i).array();
    md.update(bytes, 0, bytes.length);
    return Math.abs(new BigInteger(1, md.digest()).intValue()) % (capacity * 2 - 1);
  }
}
