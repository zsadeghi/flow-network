package me.theyinspire.projects.flow.sort.impl;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Vertex;
import me.theyinspire.projects.flow.search.GraphVisitor;
import me.theyinspire.projects.flow.search.impl.DepthFirstGraphVisitor;
import me.theyinspire.projects.flow.search.impl.GraphVertexVisitorAdapter;
import me.theyinspire.projects.flow.sort.TopologicalSorter;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class DFSTopologicalSorter<E extends EdgeDetails, V extends VertexDetails> implements TopologicalSorter<E, V> {

    final GraphVisitor<E, V> visitor = new DepthFirstGraphVisitor<>();

    @Override
    public List<Vertex<V>> sort(Graph<E, V> graph) {
        final LinkedList<Vertex<V>> sorted = new LinkedList<>();
        visitor.visit(graph, new GraphVertexVisitorAdapter<E, V>() {
            @Override
            public void afterExploration(Graph<E, V> graph, Vertex<V> vertex) {
                sorted.add(0, vertex);
            }
        });
        return sorted;
    }

}
