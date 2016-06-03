package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.Account;
import com.stevesoltys.owl.model.Agent;
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
public class AgentConfiguration {

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
     * The configuration key for the username of the {@link Account} used to authenticate with this agent.
     */
    private static final String USERNAME_KEY = "username";

    /**
     * The configuration key for the password of the {@link Account} used to authenticate with this agent.
     */
    private static final String PASSWORD_KEY = "password";

    /**
     * The agent repository.
     */
    private final AgentRepository agentRepository;

    @Autowired
    public AgentConfiguration(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    /**
     * Initializes the application using the given {@link Agent} configuration map.
     *
     * @param configuration The configuration map.
     * @throws OwlConfigurationException If there is an error in the given configuration.
     */
    @SuppressWarnings("unchecked")
    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {

        try {
            List<Map<String, String>> agents = (List<Map<String, String>>) configuration.get(AGENT_LIST_KEY);

            for (Map<String, String> agentConfiguration : agents) {

                if (!agentConfiguration.containsKey(ADDRESS_KEY)) {
                    throw new OwlConfigurationException("An agent in the configuration does not contain an address.");
                } else if (!agentConfiguration.containsKey(USERNAME_KEY)) {
                    throw new OwlConfigurationException("An agent in the configuration does not contain an authentication username.");
                } else if (!agentConfiguration.containsKey(PASSWORD_KEY)) {
                    throw new OwlConfigurationException("An agent in the configuration does not contain an authentication password.");
                } else if (!agentConfiguration.containsKey(UPDATE_INTERVAL_KEY)) {
                    throw new OwlConfigurationException("An agent in the configuration does not contain an update interval.");
                }

                String address = agentConfiguration.get(ADDRESS_KEY);

                String username = agentConfiguration.get(USERNAME_KEY);
                String password = agentConfiguration.get(PASSWORD_KEY);
                Account account = new Account(username, password);

                long updateInterval = Long.parseLong(agentConfiguration.get(UPDATE_INTERVAL_KEY));

                Agent agent = new Agent(address, account, updateInterval);
                agentRepository.getAgents().add(agent);
            }
        } catch (NullPointerException | ClassCastException | NumberFormatException e) {
            throw new OwlConfigurationException("Invalid agent configuration.");
        }

    }

}
