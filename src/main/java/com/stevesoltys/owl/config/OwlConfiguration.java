package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * A class that is used to initialize the configuration modules.
 *
 * @author Steve Soltys
 */
@Component
public class OwlConfiguration {

    /**
     * The component type configuration.
     */
    private final OwlComponentTypeConfiguration componentTypeConfiguration;

    /**
     * The component configuration.
     */
    private final OwlComponentConfiguration componentConfiguration;

    /**
     * The agent configuration.
     */
    private final AgentConfiguration agentConfiguration;

    /**
     * The account configuration.
     */
    private final AccountConfiguration accountConfiguration;

    @Autowired
    public OwlConfiguration(OwlComponentTypeConfiguration componentTypeConfiguration,
                            OwlComponentConfiguration componentConfiguration,
                            AgentConfiguration agentConfiguration,
                            AccountConfiguration accountConfiguration) {

        this.componentTypeConfiguration = componentTypeConfiguration;
        this.componentConfiguration = componentConfiguration;
        this.agentConfiguration = agentConfiguration;
        this.accountConfiguration = accountConfiguration;
    }

    /**
     * Initializes the different configuration modules, given the configuration map.
     *
     * @param configuration The configuration map.
     * @throws OwlConfigurationException If an error occurs while parsing the configuration.
     */
    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {
        componentTypeConfiguration.initialize(configuration);
        componentConfiguration.initialize(configuration);
        agentConfiguration.initialize(configuration);
        accountConfiguration.initialize(configuration);
    }
}
