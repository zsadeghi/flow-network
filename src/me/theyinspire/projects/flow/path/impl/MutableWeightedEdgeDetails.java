package me.theyinspire.projects.flow.path.impl;

import me.theyinspire.projects.flow.path.WeightedEdgeDetails;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class MutableWeightedEdgeDetails implements WeightedEdgeDetails {

    private int weight;

    @Override
    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
