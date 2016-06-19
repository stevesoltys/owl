package com.stevesoltys.owl.repository;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.OwlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

/**
 * A {@link Repository} which stores {@link OwlComponent}s.
 *
 * @author Steve Soltys
 */
@Repository
public class OwlComponentRepository {

    /**
     * The component type repository.
     */
    private final OwlComponentAttributeRepository componentAttributeRepository;

    /**
     * The set of components currently in the repository.
     */
    private final Multimap<String, OwlComponent> components = HashMultimap.create();

    @Autowired
    public OwlComponentRepository(OwlComponentAttributeRepository componentAttributeRepository) {
        this.componentAttributeRepository = componentAttributeRepository;
    }

    /**
     * Registers a component, given its identifier.
     *
     * @param identifier The identifier.
     * @return The component instance.
     * @throws OwlComponentException If a component with the identifier could not be found, or could not be initialized.
     */
    @SuppressWarnings("unchecked")
    public OwlComponent registerComponent(String identifier, Map<String, Object> configuration)
            throws OwlComponentException, OwlConfigurationException {

        Optional<Map> attributes = componentAttributeRepository.getComponentAttributes(identifier);

        if (!attributes.isPresent()) {
            throw new OwlComponentException("Could not find component type with identifier: " + identifier);
        }

        OwlComponent component = new OwlComponent(configuration, attributes.get());
        components.put(identifier, component);

        return component;
    }

    /**
     * Gets the map of components in this repository.
     *
     * @return The map of components.
     */
    public Multimap<String, OwlComponent> getComponentMap() {
        return components;
    }

    /**
     * Gets the set of components currently in the repository.
     *
     * @return The set of components.
     */
    public Collection<OwlComponent> getComponents() {
        return components.values();
    }
}
