package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.component.OwlComponent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A {@link Repository} which stores {@link OwlComponent} class types.
 *
 * @author Steve Soltys
 */
@Repository
public class OwlComponentTypeRepository {

    /**
     * A map from {@link OwlComponent} types identifiers to their {@link Class}.
     */
    private final Map<String, Class<? extends OwlComponent>> componentTypes = new HashMap<>();

    /**
     * Registers the given {@link OwlComponent} type.
     *
     * @param identifier The component identifier.
     * @param type       The component class instance.
     * @throws OwlComponentException If a component type already exists with the given identifier.
     */
    public void registerComponentType(String identifier, Class<? extends OwlComponent> type) throws OwlComponentException {
        if(componentTypes.containsKey(identifier)) {
            throw new OwlComponentException("A component type already exists with the identifier: " + identifier);
        }

        componentTypes.put(identifier, type);
    }

    /**
     * Gets a component class instance by its identifier.
     *
     * @param identifier The identifier.
     * @return An optional, possibly containing an {@link OwlComponent} class instance.
     */
    public Optional<Class<? extends OwlComponent>> getComponentClass(String identifier) {

        if(!componentTypes.containsKey(identifier)) {
            return Optional.empty();
        }

        return Optional.of(componentTypes.get(identifier));
    }

}
