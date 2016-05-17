package com.stevesoltys.owl.model.component;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.agent.Agent;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

/**
 * A component of an {@link Agent}. An example of an <code>OwlComponent</code> might be CPU usage, or a network service.
 *
 * @author Steve Soltys
 */
public abstract class OwlComponent {

    /**
     * The interval in seconds between updates for this component.
     */
    private long updateInterval;

    /**
     * The current state of this component.
     */
    private OwlComponentState state = OwlComponentState.UNKNOWN;

    /**
     * A date containing the last time that this component was updated.
     */
    private Date lastUpdate = Time.from(Instant.now());

    /**
     * Initializes this OwlComponent instance using the given configuration.
     *
     * @param configuration The configuration.
     * @throws OwlConfigurationException If an error occurs while initializing with the given configuration.
     */
    public void init(Map<String, Object> configuration) throws OwlConfigurationException {

        // TODO: Put this stuff somewhere else?

        if(!configuration.containsKey("update_interval")) {
            throw new OwlConfigurationException("Could not find update interval for component: " + this);
        }

        try {
            updateInterval = Long.parseLong(configuration.get("update_interval").toString());
        } catch(NumberFormatException ex) {
            throw new OwlConfigurationException("Could not cast update interval to long: " + ex.getMessage());
        }
    }

    /**
     * Gets the interval in seconds between updates for this component.
     *
     * @return The interval.
     */
    public long getUpdateInterval() {
        return updateInterval;
    }

    /**
     * Gets the current state of this component instance.
     *
     * @return The current state.
     */
    public OwlComponentState getState() {
        return state;
    }

    /**
     * Sets the current state of this component instance.
     *
     * @param state The current state.
     */
    public void setState(OwlComponentState state) {
        this.state = state;
    }

    /**
     * A date containing the last time that this component was updated.
     *
     * @return The date instance.
     */
    public Date getLastUpdate() {
        return lastUpdate;
    }

    /**
     * Sets a date containing the last time that this component was updated.
     *
     * @param lastUpdate The date instance.
     */
    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
