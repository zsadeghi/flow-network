package me.theyinspire.projects.flow.network.impl;

import me.theyinspire.projects.flow.common.ParameterizedTypeReference;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Vertex;
import me.theyinspire.projects.flow.network.FlowEdgeDetails;
import me.theyinspire.projects.flow.search.impl.BreadthFirstGraphVisitor;
import me.theyinspire.projects.flow.search.impl.GraphVertexVisitorAdapter;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class EdmondsKarpMaximumFlowFinder<E extends FlowEdgeDetails, V extends VertexDetails> extends AbstractFordFulkersonMaximumFlowFinder<E, V> {

    private final BreadthFirstGraphVisitor<FlowEdgeDetails, V> visitor = new BreadthFirstGraphVisitor<>();

    @Override
    protected List<Integer> findAugmentingPath(Graph<FlowEdgeDetails, V> residualNetwork, int source, int destination) {
        final Graph<FlowEdgeDetails, V> visitation = visitor.visit(residualNetwork, source, new GraphVertexVisitorAdapter<FlowEdgeDetails, V>() {
        });
        final List<Integer> path = new LinkedList<>();
        int vertex = destination;
        while (vertex != source) {
            final Vertex<V> parent = visitation.get(vertex).getProperty("parent", new ParameterizedTypeReference<Vertex<V>>() {
            });
            if (parent != null) {
                path.add(0, vertex);
                vertex = parent.getIndex();
            } else {
                return Collections.emptyList();
            }
        }
        path.add(0, source);
        return path;
    }

}
