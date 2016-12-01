package me.theyinspire.projects.flow.network.impl;

import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.AdjacencyListGraph;
import me.theyinspire.projects.flow.graph.impl.Edge;
import me.theyinspire.projects.flow.graph.impl.Vertex;
import me.theyinspire.projects.flow.network.FlowEdgeDetails;
import me.theyinspire.projects.flow.network.MaximumFlowFinder;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public abstract class AbstractFordFulkersonMaximumFlowFinder<E extends FlowEdgeDetails, V extends VertexDetails> implements MaximumFlowFinder<E, V> {

    @Override
    public Graph<FlowEdgeDetails, V> find(Graph<E, V> network, int source, int destination) {
        final Graph<FlowEdgeDetails, V> result = initializeNetwork(network);
        while (true) {
            final Graph<FlowEdgeDetails, V> residualNetwork = getResidualNetwork(result);
            final List<Integer> augmentingPath = findAugmentingPath(residualNetwork, source, destination);
            if (augmentingPath == null || augmentingPath.isEmpty()) {
                break;
            }
            int residualCapacity = getResidualCapacity(residualNetwork, augmentingPath);
            for (int i = 0; i < augmentingPath.size() - 1; i++) {
                final Edge<FlowEdgeDetails, V> edge = result.edge(augmentingPath.get(i), augmentingPath.get(i + 1));
                if (edge == null) {
                    final Edge<FlowEdgeDetails, V> reverse = result.edge(augmentingPath.get(i), augmentingPath.get(i + 1));
                    ((MutableFlowEdgeDetails) reverse.getDetails()).setFlow(reverse.getDetails().getFlow() + residualCapacity);
                } else {
                    ((MutableFlowEdgeDetails) edge.getDetails()).setFlow(edge.getDetails().getFlow() + residualCapacity);
                }
            }
        }
        return result;
    }

    protected int getResidualCapacity(Graph<FlowEdgeDetails, V> residualNetwork, List<Integer> augmentingPath) {
        int residualCapacity = Integer.MAX_VALUE;
        for (int i = 0; i < augmentingPath.size() - 1; i++) {
            final Edge<FlowEdgeDetails, V> edge = residualNetwork.edge(augmentingPath.get(i), augmentingPath.get(i + 1));
            final int capacity = edge.getDetails() == null ? 0 : edge.getDetails().getCapacity();
            if (capacity < residualCapacity) {
                residualCapacity = capacity;
            }
        }
        return residualCapacity;
    }

    protected abstract List<Integer> findAugmentingPath(Graph<FlowEdgeDetails, V> residualNetwork, int source, int destination);

    private Graph<FlowEdgeDetails, V> getResidualNetwork(Graph<FlowEdgeDetails, V> network) {
        final Graph<FlowEdgeDetails, V> result = new AdjacencyListGraph<>();
        for (int i = 0; i < network.size(); i++) {
            result.add();
        }
        for (int i = 0; i < network.size(); i++) {
            for (int j = 0; j < network.size(); j++) {
                final Edge<FlowEdgeDetails, V> edge = network.edge(i, j);
                final Edge<FlowEdgeDetails, V> reverse = network.edge(j, i);
                final int capacity;
                if (edge == null) {
                    if (reverse != null) {
                        capacity = reverse.getDetails() == null ? 0 : reverse.getDetails().getFlow();
                    } else {
                        capacity = 0;
                    }
                } else {
                    capacity = edge.getDetails() == null ? 0 : (edge.getDetails().getCapacity() - edge.getDetails().getFlow());
                }
                if (capacity > 0) {
                    final MutableFlowEdgeDetails details = new MutableFlowEdgeDetails();
                    details.setCapacity(capacity);
                    result.connect(i, j, details);
                }
            }
        }
        return result;
    }

    protected Graph<FlowEdgeDetails, V> initializeNetwork(Graph<E, V> network) {
        final Graph<FlowEdgeDetails, V> result = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : network) {
            result.add(vertex.getDetails());
        }
        for (Edge<E, V> edge : network.getEdges()) {
            final E details = edge.getDetails();
            final int capacity = details == null ? 0 : details.getCapacity();
            final MutableFlowEdgeDetails edgeDetails = new MutableFlowEdgeDetails();
            edgeDetails.setCapacity(capacity);
            edgeDetails.setFlow(0);
            result.connect(edge.getFrom().getIndex(), edge.getTo().getIndex(), edgeDetails);
        }
        return result;
    }

}
