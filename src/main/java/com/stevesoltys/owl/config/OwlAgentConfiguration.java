package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.agent.Agent;
import com.stevesoltys.owl.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * A configuration class used to initialize {@link Agent}s.
 *
 * @author Steve Soltys
 */
@Component
public class OwlAgentConfiguration {

    /**
     * The configuration key for the {@link Agent} flag.
     */
    private static final String AGENT_FLAG_KEY = "agent";

    /**
     * The configuration key for the list of remote {@link Agent}s.
     */
    private static final String AGENT_LIST_KEY = "agents";

    /**
     * The configuration key for the address of an {@link Agent}.
     */
    private static final String ADDRESS_KEY = "address";

    /**
     * The configuration key for the update interval of an {@link Agent}.
     */
    private static final String UPDATE_INTERVAL_KEY = "update_interval";

    /**
     * The agent repository.
     */
    private final AgentRepository agentRepository;

    /**
     * A flag that indicates whether or not the system running this application is an agent.
     */
    private boolean agent;

    @Autowired
    public OwlAgentConfiguration(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    /**
     * Initializes the application using the given {@link Agent} configuration map.
     *
     * @param configuration The configuration map.
     * @throws OwlConfigurationException If there is an error in the given configuration.
     */
    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {
        setAgentFlag(configuration);
        populateAgentRepository(configuration);
    }

    /**
     * Sets the agent flag, which indicating whether or not the system running this application is an agent.
     *
     * @param configuration The configuration map.
     * @throws OwlConfigurationException If there is an error in the given configuration.
     */
    private void setAgentFlag(Map<String, Object> configuration) throws OwlConfigurationException {

        if (!configuration.containsKey(AGENT_FLAG_KEY)) {
            throw new OwlConfigurationException("Agent flag not found.");
        }

        Object agentFlag = configuration.get(AGENT_FLAG_KEY);

        try {
            this.agent = (Boolean) agentFlag;
        } catch (ClassCastException e) {
            throw new OwlConfigurationException("Agent configuration flag must be a boolean value.");
        }

    }

    /**
     * Populates the {@link AgentRepository} using the given configuration map.
     *
     * @param configuration The configuration map.
     * @throws OwlConfigurationException If there is an error in the given configuration.
     */
    @SuppressWarnings("unchecked")
    private void populateAgentRepository(Map<String, Object> configuration) throws OwlConfigurationException {
        try {
            List<Map<String, String>> agents = (List<Map<String, String>>) configuration.get(AGENT_LIST_KEY);

            for (Map<String, String> agentConfiguration : agents) {

                if (!agentConfiguration.containsKey(ADDRESS_KEY)) {
                    throw new OwlConfigurationException("An agent in the configuration does not contain an address.");
                }

                String address = agentConfiguration.get(ADDRESS_KEY);

                if (!agentConfiguration.containsKey(UPDATE_INTERVAL_KEY)) {
                    throw new OwlConfigurationException("An agent in the configuration does not contain an address.");
                }

                long updateInterval = Long.parseLong(agentConfiguration.get(UPDATE_INTERVAL_KEY));

                Agent agent = new Agent(address, updateInterval);
                agentRepository.getAgents().add(agent);
            }
        } catch (NullPointerException | ClassCastException | NumberFormatException e) {
            throw new OwlConfigurationException("Invalid agent configuration.");
        }
    }

    /**
     * Gets a flag indicating whether the system running this application is an agent.
     *
     * @return Whether or not this system is an agent.
     */
    public boolean isAgent() {
        return agent;
    }
}
