package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.controller.OwlComponentController;
import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.OwlComponent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * A {@link Repository} which stores {@link OwlComponentController}s.
 *
 * @author Steve Soltys
 */
@Repository
public class OwlComponentControllerRepository {

    /**
     * A map of {@link OwlComponent} classes to their {@link OwlComponentController}s.
     */
    private final Map<String, OwlComponentController> controllers = new HashMap<>();

    /**
     * Registers a controller type to the given component type.
     *
     * @param identifier The component identifier.
     * @param controller The controller.
     * @throws OwlComponentException If there is already a component of that type or an instance could not be created.
     */
    public void registerController(String identifier, OwlComponentController controller) throws OwlComponentException {

        if (controllers.containsKey(identifier)) {
            throw new OwlComponentException("A controller for the given component type has already been registered.");
        }

        controllers.put(identifier, controller);
    }

    /**
     * Gets the controller for a component, given its instance.
     *
     * @param identifier The component identifier.
     * @return The controller for the given component.
     */
    public OwlComponentController getController(String identifier) {
        return controllers.get(identifier);
    }

}
