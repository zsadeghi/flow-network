package me.theyinspire.projects.flow.search;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.GraphUtils;
import me.theyinspire.projects.flow.graph.impl.Vertex;

import java.util.*;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class BreadthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails> implements GraphVisitor<E, V> {

    private final Comparator<Vertex<V>> comparator;

    public BreadthFirstGraphVisitor() {
        this(null);
    }

    public BreadthFirstGraphVisitor(Comparator<Vertex<V>> comparator) {
        this.comparator = comparator;
    }

    @Override
    public Graph<E, V> visit(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        for (Vertex<V> vertex : vertices) {
            vertex.setProperty("color", GraphColor.WHITE);
            vertex.setProperty("distance", Integer.MAX_VALUE);
            vertex.setProperty("parent", null);
        }
        if (comparator != null) {
            Collections.sort(vertices, comparator);
        }
        for (Vertex<V> vertex : vertices) {
            if (GraphColor.WHITE.equals(vertex.getProperty("color", GraphColor.class))) {
                visitSubGraph(copy, visitor, vertex);
            }
        }
        return copy;
    }

    @Override
    public Graph<E, V> visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        for (Vertex<V> vertex : vertices) {
            if (vertex.getIndex() == start) {
                continue;
            }
            vertex.setProperty("color", GraphColor.WHITE);
            vertex.setProperty("distance", Integer.MAX_VALUE);
            vertex.setProperty("parent", null);
        }
        final Vertex<V> startingVertex = copy.get(start);
        visitSubGraph(copy, visitor, startingVertex);
        return copy;
    }

    private void visitSubGraph(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor, Vertex<V> startingVertex) {
        startingVertex.setProperty("color", GraphColor.GREY);
        startingVertex.setProperty("distance", 0);
        startingVertex.setProperty("parent", null);
        final Queue<Vertex<V>> queue = new ArrayDeque<>();
        queue.add(startingVertex);
        while (!queue.isEmpty()) {
            final Vertex<V> vertex = queue.poll();
            visitor.beforeExploration(graph, vertex);
            for (Vertex<V> neighbor : graph.getNeighbors(vertex)) {
                visitor.visitEdge(graph, graph.edge(vertex.getIndex(), neighbor.getIndex()));
                if (GraphColor.WHITE.equals(neighbor.getProperty("color", GraphColor.class))) {
                    neighbor.setProperty("color", GraphColor.GREY);
                    neighbor.setProperty("distance", vertex.getProperty("distance", Integer.class) + 1);
                    neighbor.setProperty("parent", vertex);
                    queue.add(neighbor);
                }
            }
            vertex.setProperty("color", GraphColor.BLACK);
            visitor.afterExploration(graph, vertex);
        }
    }

}
