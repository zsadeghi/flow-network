package me.theyinspire.projects.flow.search;


import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface GraphVisitor<E extends EdgeDetails, V extends VertexDetails> {

    Graph<E, V> visit(Graph<E, V> graph, GraphVertexVisitor<E, V> visitor);

    Graph<E, V> visit(Graph<E, V> graph, int start, GraphVertexVisitor<E, V> visitor);

}
