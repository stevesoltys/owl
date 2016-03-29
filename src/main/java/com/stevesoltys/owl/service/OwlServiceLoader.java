package com.stevesoltys.owl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Loads all services during application initialization.
 *
 * @author Steve Soltys
 */
@Component
public class OwlServiceLoader {

    @Autowired
    private ComponentUpdateService componentUpdateService;

    /**
     * Initializes all necessary services.
     */
    public void initialize() {
        componentUpdateService.initialize();
    }

}
