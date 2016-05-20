package com.stevesoltys.owl.service;

import com.stevesoltys.owl.controller.component.OwlComponentRestController;
import com.stevesoltys.owl.model.agent.Agent;
import com.stevesoltys.owl.model.component.OwlComponentSet;
import com.stevesoltys.owl.repository.AgentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A service which updates {@link Agent}s that are in the {@link AgentRepository}.
 *
 * @author Steve Soltys
 */
@Service
public class AgentUpdateService {

    /**
     * The size of the thread pool. Set to the number of available processors.
     */
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * The scheduled task executor for this service.
     */
    private static final ScheduledThreadPoolExecutor taskExecutor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);

    /**
     * The agent repository.
     */
    private final AgentRepository agentRepository;

    @Autowired
    public AgentUpdateService(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    /**
     * Iterates through each registered {@link Agent} and schedules an update task for that agent at the given interval.
     */
    public void initialize() {

        RestTemplate restTemplate = new RestTemplate();

        for (Agent agent : agentRepository.getAgents()) {

            taskExecutor.scheduleAtFixedRate(() -> {

                // Fetch and update the component set for the current agent.

                String address = agent.getAddress() + OwlComponentRestController.COMPONENTS_MAPPING;
                OwlComponentSet componentSet = restTemplate.getForObject(address, OwlComponentSet.class);

                agent.getComponents().clear();
                agent.getComponents().addAll(componentSet.getComponents());

            }, 0, agent.getUpdateInterval(), TimeUnit.SECONDS);
        }
    }

}
