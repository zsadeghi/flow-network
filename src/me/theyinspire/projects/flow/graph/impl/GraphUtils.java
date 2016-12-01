package me.theyinspire.projects.flow.graph.impl;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.GraphDetails;
import me.theyinspire.projects.flow.graph.VertexDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public abstract class GraphUtils {

    public static <E extends EdgeDetails, V extends VertexDetails> Graph<E, V> copy(Graph<E, V> original) {
        final Graph<E, V> copy = new AdjacencyListGraph<>();
        for (Vertex<V> vertex : original) {
            final int index = copy.add();
            final Vertex<V> added = copy.get(index);
            copy(vertex, added);
        }
        for (Edge<E, V> edge : original.getEdges()) {
            final Edge<E, V> added = copy.connect(edge.getFrom().getIndex(), edge.getTo().getIndex());
            copy(edge, added);
        }
        return copy;
    }

    public static <E extends GraphElement<D>, D extends GraphDetails> void copy(E original, E copy) {
        copy.setDetails(original.getDetails());
        for (String property : original.getPropertyNames()) {
            copy.setProperty(property, original.getProperty(property));
        }
    }

}
