package com.prince.custom;

import lombok.Data;

/**
 * @author Prince Raj
 */
public class HashMap<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    private static final int MAXIMUM_CAPACITY = 1 << 30;

    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    private static final char EQUAL = '=';

    private static final char COMMA = ',';

    private static final char SPACE = ' ';

    private Entry<K, V>[] table;

    private int size;

    private int threshold;

    private final float loadFactor;

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("hello", 1);
        map.put("hi", 2);
        map.put("glow", 5);

        System.out.println(map);

        map.put("hi", 3);

        System.out.println(map);
        System.out.println("size: " + map.size());
        System.out.println("Value for hi: " + map.get("hi"));
        System.out.println("Value for bye: " + map.get("bye"));

        map.remove("hi");

        System.out.println(map);
        System.out.println("size: " + map.size());
    }

    public HashMap() {
        this.table = new Entry[DEFAULT_INITIAL_CAPACITY];

        this.threshold = (int)(DEFAULT_INITIAL_CAPACITY * DEFAULT_LOAD_FACTOR);
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity");
        }

        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor");
        }

        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }

        this.table = new Entry[initialCapacity];
        this.threshold = (int)Math.min(initialCapacity * loadFactor, MAXIMUM_CAPACITY);
        this.loadFactor = loadFactor;
    }

    public V get(K key) {
        if (key == null) {
            throw new RuntimeException("Key can't be null");
        }

        Entry<K, V> entry = getEntry(key);
        return entry == null ? null : entry.getValue();
    }

    public V put(K key, V value) {
        if (key == null || value == null) {
            throw new RuntimeException("Key/Value can't be null");
        }

        V oldValue = null;
        Entry entry = getEntry(key);
        if (entry != null) {
            oldValue = (V)entry.value;
            entry.value = value;
        } else {
            int hash = hash(key);
            int index = indexFor(hash, table.length);
            addEntry(key, value, hash, index);
            size++;
        }

        return oldValue;
    }

    public V remove(Object key) {
        V value = null;

        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Entry<K, V> prev = null;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.hash == hash && (e.key == key || (key != null && key.equals(e.key)))) {
                value = e.value;

                if (prev == null) {
                    table[index] = e.next;
                } else {
                    prev.next = e.next;
                }

                size--;
                break;
            }

            prev = e;
        }

        return value;
    }

    public int size() {
        return size;
    }

    private void addEntry(K key, V value, int hash, int index) {
        if (size >= threshold) {
            resize(2 * table.length);
            index = indexFor(hash, table.length);
        }

        Entry<K, V> e = table[index];
        table[index] = new Entry<>(key, value, hash, e);
    }

    private void resize(int newCapacity) {
        Entry[] oldTable = table;
        int oldCapacity = oldTable.length;

        if (oldCapacity == MAXIMUM_CAPACITY) {
            threshold = Integer.MAX_VALUE;
            return;
        }

        Entry<K, V>[] newTable = new Entry[newCapacity];
        for (Entry<K, V> e : table) {
            while (e != null) {
                Entry<K, V> next = e.next;
                int index = indexFor(e.hash, newCapacity);
                e.next = newTable[index];
                newTable[index] = e;

                e = next;
            }
        }

        table = newTable;
        threshold = (int)Math.min(newCapacity * loadFactor, MAXIMUM_CAPACITY);
    }

    private Entry<K, V> getEntry(K key) {
        int hash = hash(key);
        int index = indexFor(hash, table.length);

        Entry<K, V> found = null;
        for (Entry<K, V> e = table[index]; e != null; e = e.next) {
            if (e.hash == hash && (e.key == key || (key != null && key.equals(e.key)))) {
                found = e;
                break;
            }
        }

        return found;
    }

    private int indexFor(int hash, int length) {
        return hash & (length - 1);
    }

    private int hash(Object key) {
        // TODO write better hash code
        return key.hashCode();
    }

    @Data
    private static final class Entry<K, V> {

        private final K key;

        private V value;

        private int hash;

        private Entry<K, V> next;

        private Entry(K key, V value, int hash, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.hash = hash;
            this.next = next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('{');

        boolean empty = true;
        int index = 0;
        while (index < table.length) {
            Entry e = table[index];
            if (e != null) {
                if (!empty) {
                    sb.append(COMMA).append(SPACE);
                }

                sb.append(e.key).append(EQUAL).append(e.value);
                empty = false;

                e = e.next;
            }

            if (e == null) {
                index++;
            }
        }

        sb.append('}');

        return sb.toString();
    }
}
