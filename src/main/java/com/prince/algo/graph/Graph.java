package com.prince.algo.graph;

/**
 * <pre>
 * Graphs are data structures to represent road networks, the web, social networks etc.
 *
 * They have two main ingredient which are;
 * 1. Vertices(V) as known as nodes.
 * 2. Edges(E) : pair of vertices (directed or undirected)
 *
 * Graphs are commonly represented by two ways related to their structures. These are:
 * 1. Adjacency List Representation
 * 2. Adjacency Matrix Representation
 *
 * Adjacency list is preferred when the graph is sparse; which means |E| is much less than |V2|.
 * Adjacency matrix is preferred when the graph is dense; which means |E| is close to |V2|.
 *
 * 1. Adjacency List
 * Adjacency list representation of a graph G = (V , E) contains an array of vertices – lets call it Adj -, and for
 * each vertex u∈V, the adjacency list Adj[u] contains all adjacent vertices v such that there is an edge (u,v) ∈ E.
 * In other words, Adj[u] has an array which contains only adjacent vertices to it. Thus, we can briefly say the main
 * ingredients are;
 * 1. array (or list) of vertices.
 * 2. array (or list) of edges.
 *
 * 2. Adjacency Matrix
 * Adjacency matrix of a graph G=(V,E) consists of a |V|​x​|V| matrix A where;
 * Aij = 1 if G has an i-j edge.
 * Aij = 0 otherwise.
 *
 * Graph Search:
 * 1. Breadth first search
 * 1. Depth first search
 *
 * 1. Breadth First Search
 * Breadth first search explores graph level by level with using a queue data structure. By this search behavior
 * we are able to compute shortest paths in a given graph G=(V,E). The algorithm starts from a given source vertex s,
 * and it searches it’s adjacent level of vertices ,and goes on.
 *
 * - Initialize each vertex except the source.
 * - Take vertex v from the queue.
 * - Iterate each node u in adjacency list of v if it is unvisited.
 * - Put every node u in queue and mark it as visited.
 *
 * public class Vertex {

 *     private boolean           visited;
 *     private int               distance;
 *     private Vertex            parent;
 *     private ArrayList<Vertex> adjList;
 *     private final String      label;
 * }
 *
 * 2. Depth First Search
 * Depth first search explores vertices by going deeper and deeper in the graph.
 *
 * - Initialize each vertex as undiscovered and set their discovery and finishing times to 0.
 * - Take an undiscovered vertex v from the graph and apply a depth first search on it.
 * - Adjust discovery time of current vertex ,and continue to search from its adjacency list. Thus, take an adjacent
 *   undiscovered vertex u from current discovered vertex, and go deeper.
 * - If there is no any undiscovered vertex from last discovered vertex then mark that vertex as finished, and
 *   initialize its finishing time. Later on, backtrack while until you hit an undiscovered vertex.
 * - Do depth first search until every vertices is finished.
 *
 * public class Vertex {
 *
 *     private boolean           explored;
 *     private boolean           finished;
 *     private Vertex            parent;
 *     private ArrayList<Vertex> adjList;
 *     private final String      label;
 *     private int               discoveredTime;
 *     private int               finishedTime;
 * }
 * </pre>
 *
 * @author Prince Raj
 */
public class Graph {

}
