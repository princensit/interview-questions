package com.prince.design;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Circle with 0-99 points
 *
 * @author Prince Raj
 */
public class ConsistentHashing {

    private final int replicas;

    // hash -> replica
    private final SortedMap<Integer, String> circle = new TreeMap<>();

    // replica -> node
    private final Map<String, String> replicaNodeMap = new HashMap<>();

    // node -> <hash, object> i.e. data in multiple shards
    private final SortedMap<String, SortedMap<Integer, Object>> nodeObjectMap = new TreeMap<>();

    private ConsistentHashing(Collection<String> nodes, int replicas) {
        this.replicas = replicas;

        for (String node : nodes) {
            setupNode(node);
        }
        System.out.println("circle: " + circle);
    }

    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("A", "B", "C", "D", "E");
        int replicas = 3;

        ConsistentHashing ch = new ConsistentHashing(nodes, replicas);

        String[] objects = getObjects();
        for (String obj : objects) {
            ch.addObject(obj);
        }

        ch.printObjects();

        String key = "basic";
        String node = ch.getNode(key);
        System.out.println("\"" + key + "\" object is present in node: " + node);

        // one node down
        System.out.println("Next, node: " + node + " is down");
        ch.removeNode(node);
        // TODO if node is down, then return 503 (service unavailable) http status code for
        // concurrent requests

        // old node data is present in new node
        node = ch.getNode(key);
        System.out.println("\"" + key + "\" object is present in node: " + node);

        // add new node; re-distribution of small set of data from old nodes to new node
        String newNode = "F";
        System.out.println("Next, new node: " + newNode + " is added");
        ch.addNode(newNode);

        // re-check if this key is moved to different node
        node = ch.getNode(key);
        System.out.println("\"" + key + "\" object is present in node: " + node);
    }

    private void setupNode(String node) {
        for (int j = 0; j < replicas; j++) {
            String replica = getReplicaName(node, j);
            int hash = getHash(replica);
            circle.put(hash, replica);
            replicaNodeMap.put(replica, node);
        }
    }

    private void addObject(Object obj) {
        int hash = getHash(obj);
        String replica = circle.get(hash);
        if (replica == null) {
            SortedMap<Integer, String> tailMap = circle.tailMap(hash);
            int newHash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
            replica = circle.get(newHash);
        }

        String node = replicaNodeMap.get(replica);
        SortedMap<Integer, Object> sortedMap = nodeObjectMap.get(node);
        if (sortedMap == null) {
            sortedMap = new TreeMap<>();
            nodeObjectMap.put(node, sortedMap);
        }

        sortedMap.put(hash, obj);
    }

    private void addNode(String node) {
        for (int j = 0; j < replicas; j++) {
            String replica = getReplicaName(node, j);
            int hash = getHash(replica);

            // copying object from old to new node
            copyObjectFromOldToNewNode(node, hash);

            // cleanup object from old node
            cleanupObjectFromOldNode(hash);

            // TODO how to ensure that 2 replicas don't have same hash?
            circle.put(hash, replica);
            replicaNodeMap.put(replica, node);
        }

        System.out.println("circle: " + circle);
        System.out.println("nodeObjectMap: " + nodeObjectMap);
    }

    private void removeNode(String node) {
        for (int j = 0; j < replicas; j++) {
            String replica = getReplicaName(node, j);
            int hash = getHash(replica);

            circle.remove(hash);
            replicaNodeMap.remove(replica);
        }

        migrateDataFromOldToNewNode(node);

        notify(node);

        System.out.println("circle: " + circle);
        System.out.println("nodeObjectMap: " + nodeObjectMap);
    }

    private String getNode(Object key) {
        final String node;
        if (circle.isEmpty()) {
            node = null;
        } else {
            int hash = getHash(key);
            String replica = circle.get(hash);
            if (replica == null) {
                SortedMap<Integer, String> tailMap = circle.tailMap(hash);
                hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();
                replica = circle.get(hash);
            }
            node = replicaNodeMap.get(replica);
        }

        return node;
    }

    private void copyObjectFromOldToNewNode(String destNode, int replicaHash) {
        SortedMap<Integer, String> headMap = circle.headMap(replicaHash);
        int hash1 = headMap.isEmpty() ? circle.lastKey() : headMap.lastKey();

        String sourceNode = getNextNode(replicaHash);
        SortedMap<Integer, Object> sortedMap = nodeObjectMap.get(sourceNode);
        for (Map.Entry<Integer, Object> entry : sortedMap.entrySet()) {
            int hash = entry.getKey();
            Object value = entry.getValue();

            if ((hash > hash1 && hash <= replicaHash)
                    || (hash1 > replicaHash && (hash > hash1 || hash <= replicaHash))) {
                SortedMap<Integer, Object> destSortedMap = nodeObjectMap.get(destNode);
                if (destSortedMap == null) {
                    destSortedMap = new TreeMap<>();
                    nodeObjectMap.put(destNode, destSortedMap);
                }

                destSortedMap.put(hash, value);
            }
        }
    }

    private void cleanupObjectFromOldNode(int replicaHash) {
        SortedMap<Integer, String> headMap = circle.headMap(replicaHash);
        int hash1 = headMap.isEmpty() ? circle.lastKey() : headMap.lastKey();

        String sourceNode = getNextNode(replicaHash);
        SortedMap<Integer, Object> sortedMap = nodeObjectMap.get(sourceNode);

        Iterator<Map.Entry<Integer, Object>> iterator = sortedMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Object> entry = iterator.next();
            int hash = entry.getKey();

            if ((hash > hash1 && hash <= replicaHash)
                    || (hash1 > replicaHash && (hash > hash1 || hash <= replicaHash))) {
                iterator.remove();
            }
        }
    }

    private void migrateDataFromOldToNewNode(String sourceNode) {
        SortedMap<Integer, Object> sortedMap = nodeObjectMap.get(sourceNode);

        for (Map.Entry<Integer, Object> entry : sortedMap.entrySet()) {
            Integer hash = entry.getKey();
            Object value = entry.getValue();

            String destNode = getNextNode(hash);
            SortedMap<Integer, Object> destSortedMap = nodeObjectMap.get(destNode);
            if (destSortedMap == null) {
                destSortedMap = new TreeMap<>();
                nodeObjectMap.put(destNode, sortedMap);
            }
            destSortedMap.put(hash, value);
        }

        nodeObjectMap.remove(sourceNode);
    }

    private String getNextNode(int hash) {
        SortedMap<Integer, String> tailMap = circle.tailMap(hash);
        hash = tailMap.isEmpty() ? circle.firstKey() : tailMap.firstKey();

        String replica = circle.get(hash);
        return replicaNodeMap.get(replica);
    }

    private void printObjects() {
        System.out.println("nodeObjectMap: " + nodeObjectMap);
    }

    private void notify(String node) {
        System.out.println("Data is migrated from old node: " + node + " to respective nodes");
    }

    private String getReplicaName(String node, int j) {
        return node + j + 'A';
    }

    private int getHash(Object s) {
        return Math.abs((s.hashCode() ^ 11) % 100);
    }

    private static String[] getObjects() {
        return new String[] {"basic", "Being", "computer", "displays", "for", "hello", "illustrate",
                "in", "is", "it", "language", "languages", "most", "of", "often", "or", "outputs",
                "program", "programming", "simple", "syntax", "that", "the", "to", "used", "user",
                "very", "working", "World"};
    }
}
