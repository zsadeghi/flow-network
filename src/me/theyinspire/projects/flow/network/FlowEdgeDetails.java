package me.theyinspire.projects.flow.network;

import me.theyinspire.projects.flow.graph.EdgeDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public interface FlowEdgeDetails extends EdgeDetails {

    int getCapacity();

    int getFlow();

}
