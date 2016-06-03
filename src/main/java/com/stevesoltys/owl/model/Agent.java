package com.stevesoltys.owl.model;

import java.util.HashSet;
import java.util.Set;

/**
 * A remote system that is being monitored.
 *
 * @author Steve Soltys
 */
public class Agent {

    /**
     * A set of {@link OwlComponent}s that this agent is currently monitoring.
     */
    private final Set<OwlComponent> components = new HashSet<>();

    /**
     * The address of this agent.
     */
    private final String address;

    /**
     * The account that is used to authenticate with this agent.
     */
    private final Account account;

    /**
     * The interval in seconds between updates for this agent.
     */
    private final long updateInterval;

    public Agent(String address, Account account, long updateInterval) {
        this.address = address;
        this.account = account;
        this.updateInterval = updateInterval;
    }

    /**
     * Gets the list of components that this agent is currently monitoring.
     *
     * @return The list of components.
     */
    public Set<OwlComponent> getComponents() {
        return components;
    }

    /**
     * Gets the address for this agent.
     *
     * @return The address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets the account that is used to authenticate with this agent.
     *
     * @return The account.
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Gets the interval in seconds between updates for this agent.
     *
     * @return The interval.
     */
    public long getUpdateInterval() {
        return updateInterval;
    }
}
