package com.stevesoltys.owl.model.agent;

import com.stevesoltys.owl.model.component.OwlComponent;

import java.util.Set;

/**
 * @author Steve Soltys
 */
public class Agent {

    private Set<OwlComponent> components;

    private String address;

    public Agent(Set<OwlComponent> components, String address) {
        this.components = components;
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public Set<OwlComponent> getComponents() {
        return components;
    }
}
