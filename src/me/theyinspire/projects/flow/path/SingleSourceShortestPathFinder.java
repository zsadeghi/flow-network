package me.theyinspire.projects.flow.path;

import me.theyinspire.projects.flow.graph.Graph;
import me.theyinspire.projects.flow.graph.VertexDetails;

public interface SingleSourceShortestPathFinder<E extends WeightedEdgeDetails, V extends VertexDetails> {

    Graph<E, V> find(Graph<E, V> graph, int start);

}