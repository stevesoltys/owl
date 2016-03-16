package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.component.OwlComponent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Steve Soltys
 */
@Repository
public class OwlComponentTypeRepository {

    private final Map<String, Class<? extends OwlComponent>> componentTypes = new HashMap<>();

    public void registerComponentType(String identifier, Class<? extends OwlComponent> type) throws OwlComponentException {
        if(componentTypes.containsKey(identifier)) {
            throw new OwlComponentException("A component type already exists with the identifier: " + identifier);
        }

        componentTypes.put(identifier, type);
    }

    public Optional<Class<? extends OwlComponent>> getComponentClass(String identifier) {

        if(!componentTypes.containsKey(identifier)) {
            return Optional.empty();
        }

        return Optional.of(componentTypes.get(identifier));
    }

}
