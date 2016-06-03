package com.stevesoltys.owl.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Tests the {@link OwlConfiguration} modules.
 *
 * @author Steve Soltys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OwlConfigurationTestsContext.class)
public class OwlConfigurationTests {

    @Autowired
    private OwlConfiguration owlConfiguration;

    /**
     * Tests failing of {@link OwlConfiguration#initialize(Map)} when the given configuration is invalid.
     */
    @Test(expected = Exception.class)
    public void testInvalidConfiguration() throws Exception {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("wrong", "config");

        owlConfiguration.initialize(configuration);
    }

    /**
     * Tests {@link OwlConfiguration#initialize(Map)} when the configuration is valid.
     */
    @Test
    public void testValidConfiguration() throws Exception {

        Map<String, Object> configuration = new HashMap<>();

        // Component types
        Map<String, String> componentType = new HashMap<>();

        componentType.put("identifier", "cpu_load");
        componentType.put("classpath", "com.stevesoltys.owl.model.component.CPULoadComponent");
        componentType.put("controller_classpath", "com.stevesoltys.owl.controller.component.CPULoadComponentController");

        configuration.put("component_types", Collections.singletonList(componentType));

        // Component instances
        Map<String, String> component = new HashMap<>();
        component.put("identifier", "cpu_load");
        component.put("update_interval", "1");

        configuration.put("components", Collections.singletonList(component));

        // Agent instances
        Map<String, String> agent = new HashMap<>();
        agent.put("address", "localhost:8080");
        agent.put("update_interval", "1");
        agent.put("username", "username");
        agent.put("password", "password");

        configuration.put("agents", Collections.singletonList(agent));

        // Account instances
        Map<String, String> account = new HashMap<>();
        account.put("username", "username");
        account.put("password", "password");

        configuration.put("accounts", Collections.singletonList(account));

        owlConfiguration.initialize(configuration);
    }

}
