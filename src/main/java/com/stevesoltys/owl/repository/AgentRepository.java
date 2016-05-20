package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.model.agent.Agent;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * A repository which contains {@link Agent}s.
 *
 * @author Steve Soltys
 */
@Repository
public class AgentRepository {

    /**
     * The set of {@link Agent}s that this repository contains.
     */
    private final Set<Agent> agents = new HashSet<>();

    /**
     * Gets the set of {@link Agent}s that are in this repository.
     *
     * @return The agents.
     */
    public Set<Agent> getAgents() {
        return agents;
    }
}
