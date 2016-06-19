package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.OwlComponent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * A {@link Repository} which stores component attributes for {@link OwlComponent} types.
 *
 * @author Steve Soltys
 */
@Repository
public class OwlComponentAttributeRepository {

    /**
     * A map from {@link OwlComponent} type identifiers to their attribute sets.
     */
    private final Map<String, Map<String, Object>> componentAttributes = new HashMap<>();

    /**
     * Registers an attribute set, given the {@link OwlComponent} identifier.
     *
     * @param identifier The component identifier.
     * @param attributes The component attributes.
     * @throws OwlComponentException If an attribute set already exists with the given identifier.
     */
    public void registerComponentType(String identifier, Map<String, Object> attributes) throws OwlComponentException {

        if (componentAttributes.containsKey(identifier)) {
            throw new OwlComponentException("An attribute set already exists for identifier: " + identifier);
        }

        componentAttributes.put(identifier, attributes);
    }

    /**
     * Gets an attribute set, given its identifier.
     *
     * @param identifier The identifier.
     * @return An optional, possibly containing an attribute set.
     */
    public Optional<Map> getComponentAttributes(String identifier) {

        if (!componentAttributes.containsKey(identifier)) {
            return Optional.empty();
        }

        return Optional.of(componentAttributes.get(identifier));
    }

    /**
     * Gets the map of component attributes.
     *
     * @return The map of component attributes.
     */
    public Map<String, Map<String, Object>> getComponentAttributes() {
        return componentAttributes;
    }
}
