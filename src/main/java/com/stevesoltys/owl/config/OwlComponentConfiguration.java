package com.stevesoltys.owl.config;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Steve Soltys
 */
@Component
public class OwlComponentConfiguration {

    @Autowired
    private OwlComponentRepository componentRepository;

    public void initialize(Map<String, Object> configuration) throws OwlConfigurationException {

    }

}
