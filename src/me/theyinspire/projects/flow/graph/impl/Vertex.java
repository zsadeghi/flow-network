package me.theyinspire.projects.flow.graph.impl;

import me.theyinspire.projects.flow.graph.VertexDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class Vertex<V extends VertexDetails> extends GraphElement<V> {

    private int index;

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return String.valueOf(index);
    }

}
