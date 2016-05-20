package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.component.OwlComponent;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * The {@link OwlComponent} configuration class.
 *
 * @author Steve Soltys
 */
@Component
public class OwlComponentConfiguration {

    /**
     * The configuration key for {@link OwlComponent} configuration.
     */
    private static final String CONFIGURATION_KEY = "components";

    /**
     * The component repository.
     */
    private OwlComponentRepository componentRepository;

    @Autowired
    public OwlComponentConfiguration(OwlComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /**
     * Initializes the instances of components from the given configuration.
     *
     * @param configuration The configuration.
     * @throws OwlConfigurationException If a component could not be registered.
     */
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
