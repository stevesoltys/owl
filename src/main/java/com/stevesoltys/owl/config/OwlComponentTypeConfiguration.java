package com.stevesoltys.owl.config;

import com.stevesoltys.owl.controller.component.OwlComponentController;
import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.component.OwlComponent;
import com.stevesoltys.owl.repository.OwlComponentControllerRepository;
import com.stevesoltys.owl.repository.OwlComponentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Steve Soltys
 */
@Component
public class OwlComponentTypeConfiguration {

    private static final String CONFIGURATION_KEY = "component_types";

    @Autowired
    private OwlComponentTypeRepository componentTypeRepository;

    @Autowired
    private OwlComponentControllerRepository componentControllerRepository;

    @SuppressWarnings("unchecked")
    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {

        try {
            List<Map<String, String>> componentTypeList = (List<Map<String, String>>) configuration.get(CONFIGURATION_KEY);

            for(Map<String, String> componentTypeConfiguration : componentTypeList) {
                String identifier = componentTypeConfiguration.get("identifier");
                String classpath = componentTypeConfiguration.get("classpath");
                String controllerClasspath = componentTypeConfiguration.get("controller_classpath");

                Class<? extends OwlComponent> componentType = (Class<? extends OwlComponent>) Class.forName(classpath);
                componentTypeRepository.registerComponentType(identifier, componentType);

                Class<? extends OwlComponentController> controllerType = (Class<? extends OwlComponentController>)
                        Class.forName(controllerClasspath);
                componentControllerRepository.registerController(componentType, controllerType);
            }
        } catch (NullPointerException | ClassCastException e) {
            throw new OwlConfigurationException("Invalid component type configuration.");
        } catch (ClassNotFoundException e) {
            throw new OwlConfigurationException("Could not find component class: " + e.getMessage());
        } catch (OwlComponentException e) {
            throw new OwlConfigurationException("Error registering OwlComponent: " + e.getMessage());
        }
    }

}