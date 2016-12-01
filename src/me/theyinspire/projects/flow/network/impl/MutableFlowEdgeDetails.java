package me.theyinspire.projects.flow.network.impl;

import me.theyinspire.projects.flow.network.FlowEdgeDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class MutableFlowEdgeDetails implements FlowEdgeDetails {

    private int capacity;
    private int flow;

    @Override
    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int getFlow() {
        return flow;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

}
