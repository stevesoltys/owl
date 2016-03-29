package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.component.OwlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A {@link Repository} which stores {@link OwlComponent}s.
 *
 * @author Steve Soltys
 */
@Repository
public class OwlComponentRepository {

    @Autowired
    private OwlComponentTypeRepository componentTypeRepository;

    /**
     * The set of components currently in the repository.
     */
    private final Set<OwlComponent> components = new HashSet<>();

    /**
     * Registers a component, given its identifier.
     *
     * @param identifier The identifier.
     * @return The component instance.
     * @throws OwlComponentException If a component with the identifier could not be found, or could not be initialized.
     */
    public OwlComponent registerComponent(String identifier) throws OwlComponentException {

        Optional<Class<? extends OwlComponent>> componentType = componentTypeRepository.getComponentClass(identifier);

        if(!componentType.isPresent()) {
            throw new OwlComponentException("Could not find component type with identifier: " + identifier);
        }

        try {
            OwlComponent component = componentType.get().newInstance();
            components.add(component);

            return component;
        } catch (InstantiationException | IllegalAccessException e) {
            throw new OwlComponentException("Could not create new component instance: " + e.getMessage());
        }
    }

    /**
     * Gets the set of components currently in the repository.
     *
     * @return The set of components.
     */
    public Set<OwlComponent> getComponents() {
        return components;
    }
}
