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

    private boolean agent;

    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {
        setComponentConfiguration(configuration);
        setAgentConfiguration(configuration);
    }

    private void setComponentConfiguration(Map<String, Object> configuration) throws OwlConfigurationException {
    }

    private void setAgentConfiguration(Map<String, Object> configuration) throws OwlConfigurationException {
        if (!configuration.containsKey("agent")) {
            throw new OwlConfigurationException("Agent flag not found.");
        }

        Object agentConfiguration = configuration.get("agent");

        if (!(agentConfiguration instanceof Boolean)) {
            throw new OwlConfigurationException("Agent configuration option must be a boolean value.");
        }

        this.agent = (Boolean) agentConfiguration;
    }

    public Set<Agent> getAgents() {
        return agents;
    }

    public boolean isAgent() {
        return agent;
    }
}
