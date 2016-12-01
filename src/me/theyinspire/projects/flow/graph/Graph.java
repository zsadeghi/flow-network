package me.theyinspire.projects.flow.graph;

import me.theyinspire.projects.flow.graph.impl.Edge;
import me.theyinspire.projects.flow.graph.impl.Vertex;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface Graph<E extends EdgeDetails, V extends VertexDetails> extends Iterable<Vertex<V>> {

    int size();

    boolean isEmpty();

    V delete(int index);

    int add();

    int add(V details);

    Vertex<V> get(int index);

    Edge<E, V> edge(int from, int to);

    boolean connected(int from, int to);

    Edge<E, V> connect(int from, int to);

    Edge<E, V> connect(int from, int to, E details);

    E disconnect(int from, int to);

    Graph<E, V> inverse();

    Graph<E, V> transpose();

    List<Vertex<V>> getVertices();

    List<Vertex<V>> getNeighbors(int index);

    List<Vertex<V>> getNeighbors(Vertex<V> vertex);

    List<Edge<E, V>> getEdges();

}
