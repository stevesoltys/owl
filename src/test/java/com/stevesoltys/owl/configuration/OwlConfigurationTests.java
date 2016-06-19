package com.stevesoltys.owl.configuration;

import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.repository.OwlComponentAttributeRepository;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Tests the {@link OwlConfiguration} modules.
 *
 * @author Steve Soltys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = OwlConfigurationTestsContext.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OwlConfigurationTests {

    /**
     * The owl configuration.
     */
    @Autowired
    private OwlConfiguration owlConfiguration;

    /**
     * The mocked component attribute repository.
     */
    @Autowired
    private OwlComponentAttributeRepository componentAttributeRepository;

    /**
     * Sets up the mocked {@link OwlComponentRepository} and {@link OwlComponentAttributeRepository} instances.
     */
    @Before
    public void initialize() throws OwlComponentException {
        componentAttributeRepository.registerComponentType("mock_component", new HashMap<>());
    }

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

        // Component instances
        Map<String, Object> component = new HashMap<>();
        component.put("identifier", "mock_component");
        component.put("update_interval", 1.0);

        configuration.put("components", Collections.singletonList(component));

        // Agent instances
        Map<String, Object> agent = new HashMap<>();
        agent.put("address", "localhost:8080");
        agent.put("update_interval", 1.0);
        agent.put("username", "username");
        agent.put("password", "password");

        configuration.put("agents", Collections.singletonList(agent));

        // Account instances
        Map<String, Object> account = new HashMap<>();
        account.put("username", "username");
        account.put("password", "password");

        configuration.put("accounts", Collections.singletonList(account));

        owlConfiguration.initialize(configuration);
    }

}
