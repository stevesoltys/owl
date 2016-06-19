package com.stevesoltys.owl.model;

import com.stevesoltys.owl.exception.OwlConfigurationException;

import java.sql.Time;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A component of an {@link Agent}. An example of an <code>OwlComponent</code> might be CPU usage, or a network service.
 *
 * @author Steve Soltys
 */
public final class OwlComponent {

    /**
     * The configuration map for this component.
     */
    private final Map<String, Object> configuration = new HashMap<>();

    /**
     * The attributes map for this component.
     */
    private final Map<String, Object> attributes = new HashMap<>();

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
     * Empty constructor, for deserialization.
     */
    public OwlComponent() {
    }

    /**
     * Constructs an owl component.
     *
     * @param configuration The configuration map.
     * @param attributes The attributes map.
     * @throws OwlConfigurationException If an error occurs while initializing with the given configuration.
     */
    public OwlComponent(Map<String, Object> configuration, Map<String, Object> attributes) throws OwlConfigurationException {
        this.configuration.putAll(configuration);
        this.attributes.putAll(attributes);

        initializeConfiguration();
    }

    /**
     * Initializes this OwlComponent instance.
     *
     * @throws OwlConfigurationException If an error occurs while initializing with the given configuration.
     */
    private void initializeConfiguration() throws OwlConfigurationException {

        if(!configuration.containsKey("update_interval")) {
            throw new OwlConfigurationException("Could not find update interval for component: " + this);
        }

        try {
            updateInterval = ((Double) configuration.get("update_interval")).longValue();
        } catch(NumberFormatException ex) {
            throw new OwlConfigurationException("Could not cast update interval to long: " + ex.getMessage());
        }
    }

    /**
     * Gets the configuration map for this component.
     *
     * @return The configuration map.
     */
    public Map<String, Object> getConfiguration() {
        return configuration;
    }

    /**
     * Gets the attribute map for this component.
     *
     * @return The attribute map.
     */
    public Map<String, Object> getAttributes() {
        return attributes;
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
