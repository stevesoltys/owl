package com.stevesoltys.owl.config;

import com.stevesoltys.owl.controller.component.OwlComponentController;
import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.component.OwlComponent;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import com.stevesoltys.owl.repository.OwlComponentTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Steve Soltys
 */
@Component
public class OwlComponentConfiguration {

    private static final String CONFIGURATION_KEY = "components";

    @Autowired
    private OwlComponentRepository componentRepository;

    @SuppressWarnings("unchecked")
    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {

        List<Map<String, Object>> componentTypeList = (List<Map<String, Object>>) configuration.get(CONFIGURATION_KEY);

        for (Map<String, Object> componentConfiguration : componentTypeList) {
            try {
                String identifier = (String) componentConfiguration.get("identifier");

                componentRepository.registerComponent(identifier).init(componentConfiguration);
            } catch (ClassCastException | OwlComponentException e) {
                throw new OwlConfigurationException("Could not register component: " + e.getMessage());
            }

        }
    }

}
