package com.stevesoltys.owl.plugin;

import com.stevesoltys.owl.controller.OwlComponentController;
import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.repository.OwlComponentAttributeRepository;
import com.stevesoltys.owl.repository.OwlComponentControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Steve Soltys
 */
@Component
public class PluginContext {

    /**
     * The component type repository.
     */
    private final OwlComponentAttributeRepository componentAttributeRepository;

    /**
     * The component controller type repository.
     */
    private final OwlComponentControllerRepository componentControllerTypeRepository;

    @Autowired
    public PluginContext(OwlComponentAttributeRepository componentAttributeRepository,
                         OwlComponentControllerRepository componentControllerTypeRepository) {
        this.componentAttributeRepository = componentAttributeRepository;
        this.componentControllerTypeRepository = componentControllerTypeRepository;
    }

    /**
     * Registers a component type, given the identifier, controller, and attribute map.
     *
     * @param identifier The component's unique identifier.
     * @param controller The controller.
     * @param attributes The component attributes.
     * @throws OwlComponentException If an error occurs while registering the component and controller types.
     */
    public void registerComponentType(String identifier, OwlComponentController controller, Map<String, Object> attributes)
            throws OwlComponentException {

        componentControllerTypeRepository.registerController(identifier, controller);
        componentAttributeRepository.registerComponentType(identifier, attributes);
    }
}
