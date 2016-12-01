package me.theyinspire.projects.flow.path.impl;

import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Vertex;
import me.theyinspire.projects.flow.path.WeightedEdgeDetails;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class DijkstraSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractSingleSourceShortestPathFinder<E, V> {

    @Override
    public Graph<E, V> find(Graph<E, V> graph, int start) {
        final Graph<E, V> result = initialize(graph, start);
        final Queue<Vertex<V>> queue = new PriorityQueue<>(result.size(), new DijkstraVertexComparator<V>());
        for (Vertex<V> vertex : result) {
            queue.add(vertex);
        }
        while (!queue.isEmpty()) {
            final Vertex<V> vertex = queue.remove();
            final List<Vertex<V>> neighbors = result.getNeighbors(vertex);
            for (Vertex<V> neighbor : neighbors) {
                relax(result, vertex, neighbor);
            }
        }
        return result;
    }

    private static class DijkstraVertexComparator<V extends VertexDetails> implements Comparator<Vertex<V>> {

        @Override
        public int compare(Vertex<V> first, Vertex<V> second) {
            return Integer.compare(first.getProperty("distance", Integer.class), second.getProperty("distance", Integer.class));
        }

    }

}
