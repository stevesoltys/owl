package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Steve Soltys
 */
@Component
public class OwlAgentConfiguration {

    private boolean agent;

    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {

        if (!configuration.containsKey("agent")) {
            throw new OwlConfigurationException("Agent flag not found.");
        }

        Object agentConfiguration = configuration.get("agent");

        if (!(agentConfiguration instanceof Boolean)) {
            throw new OwlConfigurationException("Agent configuration option must be a boolean value.");
        }

        this.agent = (Boolean) agentConfiguration;
    }

    public boolean isAgent() {
        return agent;
    }
}
