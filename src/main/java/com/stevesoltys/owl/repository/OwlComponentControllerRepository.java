package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.controller.component.OwlComponentController;
import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.component.OwlComponent;
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
    private final Map<Class<? extends OwlComponent>, OwlComponentController> controllers = new HashMap<>();

    /**
     * Registers a controller type to the given component type.
     *
     * @param componentType The component type.
     * @param controllerType The controller type.
     * @throws OwlComponentException If there is already a component of that type or an instance could not be created.
     */
    public void registerController(Class<? extends OwlComponent> componentType,
                                   Class<? extends OwlComponentController> controllerType) throws OwlComponentException {

        if (controllers.containsKey(componentType)) {
            throw new OwlComponentException("A controller for the given component type has already been registered.");
        }

        try {
            OwlComponentController controller = controllerType.newInstance();

            controllers.put(componentType, controller);
        } catch (InstantiationException | IllegalAccessException e) {
            throw new OwlComponentException("Could not create new component controller instance: " + e.getMessage());
        }
    }

    /**
     * Gets the controller for a component, given its instance.
     *
     * @param component The component instance.
     * @return The controller for the given component.
     */
    public OwlComponentController getController(OwlComponent component) {
        return controllers.get(component.getClass());
    }

}
