package lab5;

import java.util.*;

import org.graph4j.*;
import org.graph4j.alg.coloring.*;

public class BrownBacktrackColoring implements VertexColoringAlgorithm {
    private int chi; // chromatic number
    private BitSet[] allowedColors;
    private final List<Document> vertexList;
    private final Map<Document, Integer> indexMap;
    private final int[][] neighbors;
    private int[] partialColorAssignment;
    private int[] completeColorAssignment;
    Map<Document, Integer> vertexColoring;
    private int[] colorCount;
    public BrownBacktrackColoring(Graph graph) {
        Objects.requireNonNull(graph, "Graph cannot be null");
        final int numVertices = graph.numVertices();
        vertexList = new ArrayList<>();
        neighbors = new int[numVertices][];
        indexMap = new HashMap<>();

        for (int i = 0; i < graph.numVertices(); i++) {
            Object v = graph.getVertexLabel(i);
            Document vertex = new Document();
            if(v instanceof Document) {
                vertex = (Document) v;
            }
            neighbors[vertexList.size()] = new int[graph.edgesOf(i).length];
            indexMap.put(vertex, vertexList.size());
            vertexList.add(vertex);
        }
        for (int i = 0; i < numVertices; i++) {
            int nbIndex = 0;
            final Document vertex = vertexList.get(i);
            for (Edge e : graph.edgesOf(i)) {
                neighbors[i][nbIndex++] = nbIndex;
            }
        }
    }

    private void recursiveColor(int pos) {
        colorCount[pos] = colorCount[pos - 1];
        allowedColors[pos].set(0, colorCount[pos] + 1);
        // To color the i-th vertex, one can use the
        // number of colors needed to color the
        // i-1th vertex plus 1
        // Determine which colors have been used by the neighbors of the ith vertex
        for (int i = 0; i < neighbors[pos].length; i++) {
            final int nb = neighbors[pos][i];
            if (partialColorAssignment[nb] > 0) {
                allowedColors[pos].clear(partialColorAssignment[nb]);
            }
        }
        // Try to assign each of the already used colors to vertex i. Prune search if partial
        // coloring will never be better than chromatic number of best solution found thus far
        for (int i = 1; (i <= colorCount[pos]) && (colorCount[pos] < chi); i++) {
            if (allowedColors[pos].get(i)) { // Try all available colors for vertex i. A color is
                // available if its not used by its neighbor
                partialColorAssignment[pos] = i;
                if (pos < (neighbors.length - 1)) { // If not all vertices have been colored,
                    // proceed with the next uncolored vertex
                    recursiveColor(pos + 1);
                } else { // Otherwise we have found a feasible coloring
                    chi = colorCount[pos];
                    System
                            .arraycopy(
                                    partialColorAssignment, 0, completeColorAssignment, 0,
                                    partialColorAssignment.length);
                }
            }
        }
        // consider using a new color for vertex i
        if ((colorCount[pos] + 1) < chi) {
            colorCount[pos]++;
            partialColorAssignment[pos] = colorCount[pos];
            if (pos < (neighbors.length - 1)) {
                recursiveColor(pos + 1);
            } else {
                chi = colorCount[pos];
                System
                        .arraycopy(
                                partialColorAssignment, 0, completeColorAssignment, 0,
                                partialColorAssignment.length);
            }
        }
        partialColorAssignment[pos] = 0;
    }

    private void lazyComputeColoring() {
        if (vertexColoring != null)
            return;
        chi = neighbors.length + 1;
        partialColorAssignment = new int[neighbors.length];
        completeColorAssignment = new int[neighbors.length];
        partialColorAssignment[0] = 1; // Prefix color of first vertex. Optimization: Could prefix
        // all colors of largest clique
        colorCount = new int[neighbors.length];
        colorCount[0] = 1;
        allowedColors = new BitSet[neighbors.length];
        for (int i = 0; i < neighbors.length; i++) {
            allowedColors[i] = new BitSet(1);
        }
        recursiveColor(1);

        Map<Document, Integer> colorMap = new LinkedHashMap<>();
        for (int i = 0; i < vertexList.size(); i++)
            colorMap.put(vertexList.get(i), completeColorAssignment[i]);
        vertexColoring = colorMap;
    }

    public int getChi () {
        return chi;
    }

    public Map<Document, Integer> getColoring() {
        lazyComputeColoring();
        return vertexColoring;
    }
    @Override
    public VertexColoring findColoring() {
        return null;
    }

    @Override
    public VertexColoring findColoring(int i) {
        return null;
    }
}