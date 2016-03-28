package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.controller.component.OwlComponentController;
import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.component.OwlComponent;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve Soltys
 */
@Repository
public class OwlComponentControllerRepository {

    private final Map<Class<? extends OwlComponent>, OwlComponentController> controllers = new HashMap<>();

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

    public OwlComponentController getController(OwlComponent component) {
        return controllers.get(component.getClass());
    }

}
