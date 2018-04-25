package com.prince.design;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import lombok.Data;

/**
 * @author Prince Raj
 */

// For simplicity, we’re represented inners and outers as an Edge array. This is actually not a great choice, since we
// need to add and remove elements from it frequently. If we were writing a real code, we would probably want to
// implement these variables as linked lists.
public class JigsawPuzzle {

    private static final Edge[] EMPTY_EDGE_ARRAY = new Edge[0];

    // remaining pieces left to put away
    Piece[] pieces;

    Piece[][] solution;

    Edge[] inners;

    Edge[] outers;

    Edge[] flats;

    Piece[] corners;

    /**
     * <pre>
     * for each Piece p in pieces {
     *     if (p has two flat edges) then add p to corners
     *
     *     for each edge in p.edges {
     *         if edge is inner then add to inners
     *         if edge is outer then add to outers
     *     }
     * }
     * </pre>
     **/
    public void sort() {
        // TODO Implement me!
    }

    public void solve() {
        /* Pick any corner to start with */
        Piece cornerPiece = new Piece();
        Edge currentEdge = getExposedEdge(cornerPiece);

        /*
         * Loop will iterate in a spiral like fashion until the puzzle is full.
         */
        while (currentEdge != null) {
            /* Match with opposite edges. Inners with outers, etc. */
            Edge[] opposites = currentEdge.getType() == EdgeType.INNER ? outers : inners;
            for (Edge fittingEdge : opposites) {
                if (currentEdge.fitsWith(fittingEdge)) {
                    attachEdges(currentEdge, fittingEdge); // attach edge
                    removeFromList(currentEdge);
                    removeFromList(fittingEdge);

                    /* get next edge */
                    currentEdge = nextExposedEdge(fittingEdge);

                    break; // Break out of inner loop. Continue in outer.
                }
            }
        }
    }

    public void removeFromList(Edge edge) {
        if (edge.getType() == EdgeType.FLAT) {
            return;
        }

        Piece cornerPiece = new Piece();
        Edge currentEdge = getExposedEdge(cornerPiece);
        Edge[] array = currentEdge.getType() == EdgeType.INNER ? inners : outers;

        // remove edge from array
        List<Edge> list = new ArrayList<>();
        Collections.addAll(list, array);
        list.removeAll(Collections.singletonList(edge));
        array = list.toArray(EMPTY_EDGE_ARRAY);
    }

    /*
     * Return the opposite edge if possible. Else, return any exposed edge.
     */
    public Edge nextExposedEdge(Edge edge) {
        int nextIndex = (edge.index + 2) % 4; // Opposite edge
        Edge nextEdge = edge.getParent().getEdges()[nextIndex];
        if (isExposed(nextEdge)) {
            return nextEdge;
        }
        return getExposedEdge(edge.getParent());
    }

    public void attachEdges(Edge e1, Edge e2) {
        e1.setAttachedTo(e2);
        e2.setAttachedTo(e1);
    }

    public boolean isExposed(Edge edge) {
        return (edge.getType() != EdgeType.FLAT) && (edge.getAttachedTo() == null);
    }

    public Edge getExposedEdge(Piece p) {
        for (Edge edge : p.getEdges()) {
            if (isExposed(edge)) {
                return edge;
            }
        }

        return null;
    }
}

// Absolute Position: "This piece is located at position (12, 23)." Absolute position would belong to the Piece class
// itself and would include an orientation as well.
//
// Relative Position: "I don’t know where this piece is actually located, but I know that it is next to this
// other piece". The relative position would belong to the Edge class.
//
// For this solution, we are using only the relative position, by adjoining edges to neighboring edges.
@Data
class Edge {

    private Piece parent;

    private EdgeType type;

    // index into Piece.edges
    int index;

    // relative position
    Edge attachedTo;

    // Returns true if the two pieces should be attached to each other.
    public boolean fitsWith(Edge edge) {
        return true;
    };
}

@Data
class Piece {

    private Edge[] edges;

    public boolean isCorner() {
        // TODO Implement me!
        return false;
    }
}

// flat - corner pieces with 2 flat edges
enum EdgeType {
    INNER, OUTER, FLAT
}