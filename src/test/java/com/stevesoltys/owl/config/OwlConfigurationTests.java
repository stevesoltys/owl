package com.stevesoltys.owl.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Steve Soltys
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration()
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

        // Agent flag
        configuration.put("agent", true);

        // Component types
        List<Map<String, String>> componentTypeList = new LinkedList<>();

        Map<String, String> componentType = new HashMap<>();
        componentType.put("identifier", "cpu_load");
        componentType.put("classpath", "com.stevesoltys.owl.model.component.CPULoadComponent");
        componentType.put("controller_classpath", "com.stevesoltys.owl.controller.component.CPULoadComponentController");

        componentTypeList.add(componentType);
        configuration.put("component_types", componentTypeList);

        // Component instances
        List<Map<String, String>> components = new LinkedList<>();
        Map<String, String> component = new HashMap<>();
        component.put("identifier", "cpu_load");
        component.put("update_interval", "1");
        components.add(component);

        configuration.put("components", components);

        owlConfiguration.initialize(configuration);
    }

}
