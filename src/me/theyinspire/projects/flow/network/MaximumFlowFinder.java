package me.theyinspire.projects.flow.network;

import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface MaximumFlowFinder<E extends FlowEdgeDetails, V extends VertexDetails> {

    Graph<FlowEdgeDetails, V> find(Graph<E, V> network, int source, int destination);

}
