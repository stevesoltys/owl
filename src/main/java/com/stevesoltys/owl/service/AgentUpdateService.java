package com.stevesoltys.owl.service;

import com.stevesoltys.owl.controller.OwlComponentRestController;
import com.stevesoltys.owl.exception.OwlAgentException;
import com.stevesoltys.owl.model.Account;
import com.stevesoltys.owl.model.Agent;
import com.stevesoltys.owl.model.OwlComponent;
import com.stevesoltys.owl.model.OwlComponentSet;
import com.stevesoltys.owl.repository.AgentRepository;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
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

        for (Agent agent : agentRepository.getAgents()) {

            taskExecutor.scheduleAtFixedRate(() -> {

                // Fetch and update the component set for the current agent.

                try {
                    Set<OwlComponent> owlComponents = requestComponents(agent);

                    agent.getComponents().clear();
                    agent.getComponents().addAll(owlComponents);

                } catch (Exception e) {
                    new OwlAgentException(e.getMessage()).printStackTrace();
                    e.printStackTrace();
                }

            }, 0, agent.getUpdateInterval(), TimeUnit.SECONDS);
        }
    }

    /**
     * Performs a GET request on the given agent using basic HTTP authentication and returns the {@link OwlComponent}s.
     *
     * @param agent The agent.
     * @return The set of owl components.
     */
    private Set<OwlComponent> requestComponents(Agent agent) {

        RestTemplate restTemplate = new RestTemplate();

        Account account = agent.getAccount();

        String credentials = account.getUsername() + ":" + account.getPassword();
        String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + encodedCredentials);
        HttpEntity<String> request = new HttpEntity<>(headers);

        String address = agent.getAddress() + OwlComponentRestController.COMPONENTS_MAPPING;

        ResponseEntity<OwlComponentSet> response = restTemplate.exchange(address, HttpMethod.GET, request, OwlComponentSet.class);

        return response.getBody().getComponents();
    }

}
