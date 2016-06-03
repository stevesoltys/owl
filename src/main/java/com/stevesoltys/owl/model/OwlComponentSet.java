package com.stevesoltys.owl.model;

import com.stevesoltys.owl.repository.OwlComponentRepository;

import java.util.*;

/**
 * A set of {@link OwlComponent}s. Utilized for wrapping a set of components to be sent remotely.
 *
 * @author Steve Soltys
 */
public class OwlComponentSet {

    /**
     * The set of {@link OwlComponent}s.
     */
    private final Set<OwlComponent> components = new HashSet<>();

    public OwlComponentSet(OwlComponentRepository repository) {
        components.addAll(repository.getComponents());
    }

    /**
     * Private constructor, used for deserialization.
     */
    private OwlComponentSet() {
    }

    /**
     * Gets the set of components.
     *
     * @return The components.
     */
    public Set<OwlComponent> getComponents() {
        return components;
    }
}
