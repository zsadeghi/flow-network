package me.theyinspire.projects.flow.graph.impl;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.VertexDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class Edge<E extends EdgeDetails, V extends VertexDetails> extends GraphElement<E> {

    private Vertex<V> from;
    private Vertex<V> to;

    public Vertex<V> getFrom() {
        return from;
    }

    public void setFrom(Vertex<V> from) {
        this.from = from;
    }

    public Vertex<V> getTo() {
        return to;
    }

    public void setTo(Vertex<V> to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return from.getIndex() + " -> " + to.getIndex();
    }

}
