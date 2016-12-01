package me.theyinspire.projects.flow.search.impl;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Edge;
import me.theyinspire.projects.flow.graph.impl.Vertex;
import me.theyinspire.projects.flow.search.GraphVertexVisitor;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public abstract class GraphVertexVisitorAdapter<E extends EdgeDetails, V extends VertexDetails> implements GraphVertexVisitor<E, V> {
    @Override
    public void beforeExploration(Graph<E, V> graph, Vertex<V> vertex) {

    }

    @Override
    public void afterExploration(Graph<E, V> graph, Vertex<V> vertex) {

    }

    @Override
    public void visitEdge(Graph<E, V> graph, Edge<E, V> edge) {

    }
}
