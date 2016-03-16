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
     * Tests failing of {@link OwlComponentTypeConfiguration#initialize(Map)} when the given configuration is invalid.
     */
    @Test(expected = Exception.class)
    public void testInvalidComponentTypeConfiguration() throws Exception {
        Map<String, Object> configuration = new HashMap<>();
        configuration.put("wrong", "config");

        OwlComponentTypeConfiguration componentTypeConfiguration = new OwlComponentTypeConfiguration();
        componentTypeConfiguration.initialize(configuration);
    }

    /**
     * Tests {@link OwlConfiguration#initialize(Map)} when the configuration is valid.
     */
    @Test
    public void testValidComponentTypeConfiguration() throws Exception {

        Map<String, Object> configuration = new HashMap<>();

        configuration.put("agent", true);

        List<Map<String, String>> componentTypeList = new LinkedList<>();

        Map<String, String> componentType = new HashMap<>();
        componentType.put("identifier", "cpu_load");
        componentType.put("classpath", "com.stevesoltys.owl.model.component.CPULoadComponent");
        componentType.put("controller_classpath", "com.stevesoltys.owl.controller.component.CPULoadComponentController");

        componentTypeList.add(componentType);
        configuration.put("component_types", componentTypeList);

        owlConfiguration.initialize(configuration);
    }

}
