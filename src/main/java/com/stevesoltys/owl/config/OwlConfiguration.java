package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @author Steve Soltys
 */
@Component
public class OwlConfiguration {

    @Autowired
    private OwlComponentTypeConfiguration componentTypeConfiguration;

    @Autowired
    private OwlComponentConfiguration componentConfiguration;

    @Autowired
    private OwlAgentConfiguration agentConfiguration;

    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {
        componentTypeConfiguration.initialize(configuration);
        componentConfiguration.initialize(configuration);
        agentConfiguration.initialize(configuration);
    }
}
