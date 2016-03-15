package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Steve Soltys
 */
public class OwlConfigurationTests {

    /**
     * Tests failing of {@link OwlAgentConfiguration#initialize(Map)} when the given configuration is invalid.
     */
    @Test(expected = Exception.class)
    public void testInvalidAgentConfiguration() throws Exception {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("wrong", "config");

        OwlAgentConfiguration agentConfiguration = new OwlAgentConfiguration();
        agentConfiguration.initialize(configuration);
    }

    /**
     * Tests {@link OwlAgentConfiguration#initialize(Map)} when the configuration is valid.
     */
    @Test
    public void testValidAgentConfiguration() throws Exception {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("agent", true);

        OwlAgentConfiguration agentConfiguration = new OwlAgentConfiguration();
        agentConfiguration.initialize(configuration);
    }

}
