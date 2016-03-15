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
     * Tests failing of {@link OwlConfiguration#initialize(Map)} when the given configuration is invalid.
     */
    @Test(expected = Exception.class)
    public void testInvalidConfiguration() throws OwlConfigurationException {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("wrong", "config");

        OwlConfiguration owlConfiguration = new OwlConfiguration();
        owlConfiguration.initialize(configuration);
    }

    /**
     * Tests {@link OwlConfiguration#initialize(Map)} when the configuration is valid.
     */
    @Test
    public void testValidConfiguration() throws OwlConfigurationException {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("agent", true);

        OwlConfiguration owlConfiguration = new OwlConfiguration();
        owlConfiguration.initialize(configuration);
    }

}
