package com.stevesoltys.owl.config;

import com.stevesoltys.owl.controller.component.OwlComponentController;
import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.agent.Agent;
import com.stevesoltys.owl.model.component.OwlComponent;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Steve Soltys
 */
@Component
public class OwlConfiguration {

    private final Map<OwlComponent, OwlComponentController> components = new HashMap<>();

    private final Set<Agent> agents = new HashSet<>();

    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {

    }

    public Set<Agent> getAgents() {
        return agents;
    }
}
