package me.theyinspire.projects.flow.graph.impl;

import me.theyinspire.projects.flow.common.ParameterizedTypeReference;
import me.theyinspire.projects.flow.graph.GraphDetails;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Zohreh Sadeghi (zsadeghi@uw.edu)
 * @since 1.0 (1/12/16)
 */
public class GraphElement<D extends GraphDetails> {

    private Map<String, Object> properties = new HashMap<>();
    private D details;

    public void setProperty(String name, Object value) {
        properties.put(name, value);
    }

    public Object getProperty(String name) {
        return properties.get(name);
    }

    public <E> E getProperty(String name, Class<E> type) {
        return properties.containsKey(name) ? type.cast(properties.get(name)) : null;
    }

    public <E> E getProperty(String name, ParameterizedTypeReference<E> typeReference) {
        //noinspection unchecked
        return properties.containsKey(name) ? (E) properties.get(name) : null;
    }

    public boolean hasProperty(String name) {
        return properties.containsKey(name);
    }

    public Set<String> getPropertyNames() {
        return properties.keySet();
    }

    public D getDetails() {
        return details;
    }

    public void setDetails(D details) {
        this.details = details;
    }

}
