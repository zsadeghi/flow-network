package me.theyinspire.projects.flow.search;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.GraphUtils;
import me.theyinspire.projects.flow.graph.impl.Vertex;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class DepthFirstGraphVisitor<E extends EdgeDetails, V extends VertexDetails> implements GraphVisitor<E, V> {

    private final Comparator<Vertex<V>> comparator;

    public DepthFirstGraphVisitor(Comparator<Vertex<V>> comparator) {
        this.comparator = comparator;
    }

    public DepthFirstGraphVisitor() {
        this(null);
    }

    @Override
    public Graph<E, V> visit(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        if (comparator != null) {
            Collections.sort(vertices, comparator);
        }
        for (Vertex<V> vertex : vertices) {
            vertex.setProperty("color", GraphColor.WHITE);
            vertex.setProperty("parent", null);
            vertex.setProperty("distance", 0);
        }
        final AtomicInteger time = new AtomicInteger(0);
        for (Vertex<V> vertex : vertices) {
            if (GraphColor.WHITE.equals(vertex.getProperty("color", GraphColor.class))) {
                visitSubGraph(copy, vertex, time, visitor);
            }
        }
        return copy;
    }

    @Override
    public Graph<E, V> visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor) {
        final Graph<E, V> copy = GraphUtils.copy(graph);
        final List<Vertex<V>> vertices = copy.getVertices();
        if (comparator != null) {
            Collections.sort(vertices, comparator);
        }
        for (Vertex<V> vertex : vertices) {
            vertex.setProperty("color", GraphColor.WHITE);
            vertex.setProperty("parent", null);
            vertex.setProperty("distance", 0);
        }
        final AtomicInteger time = new AtomicInteger(0);
        visitSubGraph(copy, copy.get(start), time, visitor);
        return copy;
    }

    private void visitSubGraph(Graph<E, V> graph, Vertex<V> vertex, AtomicInteger time, GraphVertexVisitor<E, V> visitor) {
        vertex.setProperty("discovery", time.incrementAndGet());
        vertex.setProperty("color", GraphColor.GREY);
        visitor.beforeExploration(graph, vertex);
        final List<Vertex<V>> neighbors = graph.getNeighbors(vertex.getIndex());
        if (comparator != null) {
            Collections.sort(neighbors, comparator);
        }
        for (Vertex<V> neighbor : neighbors) {
            visitor.visitEdge(graph, graph.edge(vertex.getIndex(), neighbor.getIndex()));
            if (GraphColor.WHITE.equals(neighbor.getProperty("color", GraphColor.class))) {
                neighbor.setProperty("distance", vertex.getProperty("distance", Integer.class) + 1);
                neighbor.setProperty("parent", vertex);
                visitSubGraph(graph, neighbor, time, visitor);
            }
        }
        vertex.setProperty("color", GraphColor.BLACK);
        vertex.setProperty("finish", time.incrementAndGet());
        visitor.afterExploration(graph, vertex);
    }

}
