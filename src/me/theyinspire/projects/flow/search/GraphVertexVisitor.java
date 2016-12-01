package me.theyinspire.projects.flow.search;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Edge;
import me.theyinspire.projects.flow.graph.impl.Vertex;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface GraphVertexVisitor<E extends EdgeDetails, V extends VertexDetails> {

    void beforeExploration(Graph<E, V> graph, Vertex<V> vertex);

    void afterExploration(Graph<E, V> graph, Vertex<V> vertex);

    void visitEdge(Graph<E, V> graph, Edge<E, V> edge);

}
