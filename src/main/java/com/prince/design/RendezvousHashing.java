package com.prince.design;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;

/**
 * Advantages of Rendezvous Hashing (Highest Random Weight) over Consistent Hashing
 * <ul>
 * <li>Consistent Hashing requires a persistent hashmap of the nodes and vnodes, however HRW doesn't as it is stateless.
 * Nothing needs to be modified when machines join or leave the cluster - there is no concurrency to worry about (except
 * that your clients have a good view of the state of the cluster which is the same in both cases)</li>
 * <li>HRW provides an even distribution when you lose or gain nodes (assuming you chose a sensible hash function).
 * Consistent Hashing doesn't guarantee that but with enough vnodes it's probably not going to be an issue</li>
 * </ul>
 *
 * @author Prince Raj
 */
public class RendezvousHashing {

    private Set<Node> nodes = new HashSet<>();

    public static void main(String[] args) {
        RendezvousHashing rh = new RendezvousHashing();

        // register nodes
        Node node1 = getNode("Node1", 10, 20);
        rh.addNode(node1);
        Node node2 = getNode("Node2", 5, 70);
        rh.addNode(node2);
        Node node3 = getNode("Node3", 15, 10);
        rh.addNode(node3);

        String key = "hello";
        Node node = rh.getNodeForKey(key);
        System.out.println("Node for key: " + key + " is " + node);

        // remove one node
        rh.removeNode(node1);

        node = rh.getNodeForKey(key);
        System.out.println("Node for key: " + key + " is " + node);
    }

    private static Node getNode(String name, int seed, int weight) {
        return new Node(name, seed, weight);
    }

    private Node getNodeForKey(String key) {
        int highestScore = Integer.MIN_VALUE;
        Node targetNode = null;
        for (Node node : nodes) {
            int score = node.computeWeightedScore(key);
            if (score > highestScore) {
                highestScore = score;
                targetNode = node;
            }
        }

        return targetNode;
    }

    private void addNode(Node node) {
        nodes.add(node);
    }

    private void removeNode(Node node) {
        nodes.remove(node);
    }

    @Data
    private static final class Node {

        private final String name;

        private final int seed;

        private final int weight;

        private Node(String name, int seed, int weight) {
            this.name = name;
            this.seed = seed;
            this.weight = weight;
        }

        private int computeWeightedScore(String key) {
            // some better hashing algo
            return (key.hashCode() ^ seed) * weight;
        }
    }
}
