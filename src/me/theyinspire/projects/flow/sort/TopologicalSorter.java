package me.theyinspire.projects.flow.sort;

import me.theyinspire.projects.flow.graph.EdgeDetails;
import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;
import me.theyinspire.projects.flow.graph.impl.Vertex;

import java.util.List;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface TopologicalSorter<E extends EdgeDetails, V extends VertexDetails> {

    List<Vertex<V>> sort(Graph<E, V> graph);

}
