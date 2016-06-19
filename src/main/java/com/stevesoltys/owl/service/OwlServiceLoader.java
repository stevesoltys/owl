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

    /**
     * The component update service.
     */
    private final ComponentUpdateService componentUpdateService;

    /**
     * The agent update service.
     */
    private final AgentUpdateService agentUpdateService;

    @Autowired
    public OwlServiceLoader(ComponentUpdateService componentUpdateService, AgentUpdateService agentUpdateService) {
        this.componentUpdateService = componentUpdateService;
        this.agentUpdateService = agentUpdateService;
    }

    /**
     * Initializes all necessary services.
     */
    public void initialize() {
        componentUpdateService.initialize();
        agentUpdateService.initialize();
    }

}
