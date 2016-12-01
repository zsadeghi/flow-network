package me.theyinspire.projects.flow.path.impl;

import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Vertex;
import me.theyinspire.projects.flow.path.WeightedEdgeDetails;
import me.theyinspire.projects.flow.sort.impl.DFSTopologicalSorter;
import me.theyinspire.projects.flow.sort.TopologicalSorter;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class DagSingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> extends AbstractSingleSourceShortestPathFinder<E, V> {

    private final TopologicalSorter<E, V> sorter;

    public DagSingleSourceShortestPathFinder() {
        this(new DFSTopologicalSorter<E, V>());
    }

    public DagSingleSourceShortestPathFinder(TopologicalSorter<E, V> sorter) {
        this.sorter = sorter;
    }

    @Override
    public Graph<E, V> find(Graph<E, V> graph, int start) {
        final Graph<E, V> result = initialize(graph, start);
        final List<Vertex<V>> vertices = sorter.sort(result);
        for (Vertex<V> vertex : vertices) {
            final List<Vertex<V>> neighbors = result.getNeighbors(vertex);
            for (Vertex<V> neighbor : neighbors) {
                relax(result, vertex, neighbor);
            }
        }
        return result;
    }

}
