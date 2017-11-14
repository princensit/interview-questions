package com.prince.algo.graph;

import java.util.Set;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * http://www.oodesign.com/factory-pattern.html
 *
 * Topological sorting is linear ordering of vertices for DAG (Directed Acyclic Graph)
 *
 * http://www.geeksforgeeks.org/topological-sorting/
 *
 * @author Prince Raj
 */
public class TopologicalSorting {

    // sorting used to print only one output
    private final TreeMap<Integer, Set<Integer>> adjacencyLists = new TreeMap<>();

    private final int vertices;

    private final boolean visited[];

    private final Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        TopologicalSorting ts = new TopologicalSorting(5);
        ts.addEdge(0, 1);
        ts.addEdge(1, 2);
        ts.addEdge(3, 4);
        ts.addEdge(4, 0);
        ts.addEdge(4, 1);
        ts.addEdge(4, 2);

        System.out.println(ts.adjacencyLists);
        ts.topologicalSort();
    }

    private TopologicalSorting(int vertices) {
        this.vertices = vertices;
        this.visited = new boolean[vertices];
    }

    private void addEdge(int u, int v) {
        Set<Integer> mappedVertices = adjacencyLists.get(u);
        if (mappedVertices == null) {
            mappedVertices = new TreeSet<>();
            adjacencyLists.put(u, mappedVertices);
        }

        mappedVertices.add(v);
    }

    private void topologicalSort() {
        for (Integer vertex : adjacencyLists.keySet()) {
            if (!visited[vertex]) {
                func(vertex);
            }
        }

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }

        // print independent jobs
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                System.out.println(i);
            }
        }
    }

    private void func(Integer vertex) {
        visited[vertex] = true;
        Set<Integer> edges = adjacencyLists.get(vertex);
        if (edges != null) {
            for (Integer edge : edges) {
                if (!visited[edge]) {
                    func(edge);
                }
            }
        }
        stack.push(vertex);
    }
}
