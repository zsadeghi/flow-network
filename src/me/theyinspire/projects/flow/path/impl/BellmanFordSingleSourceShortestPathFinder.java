package me.theyinspire.projects.flow.path.impl;

import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Edge;
import me.theyinspire.projects.flow.graph.impl.Vertex;
import me.theyinspire.projects.flow.path.WeightedEdgeDetails;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class BellmanFordSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractSingleSourceShortestPathFinder<E, V> {

    @Override
    public Graph<E, V> find(Graph<E, V> graph, int start) {
        final Graph<E, V> result = initialize(graph, start);
        final List<Edge<E, V>> edges = result.getEdges();
        for (int i = 0; i < graph.size() - 1; i++) {
            for (Edge<E, V> edge : edges) {
                relax(edge);
            }
        }
        for (Edge<E, V> edge : edges) {
            final Vertex<V> midpoint = edge.getFrom();
            final Vertex<V> destination = edge.getTo();
            final int currentDistance = destination.getProperty("distance", Integer.class);
            final long detourDistance = (long) midpoint.getProperty("distance", Integer.class) + (long) weight(edge);
            if (currentDistance > detourDistance) {
                return null;
            }
        }
        return result;
    }

}
