package com.stevesoltys.owl.model.component;

import com.stevesoltys.owl.exception.OwlConfigurationException;

import java.util.Date;
import java.util.Map;

/**
 * @author Steve Soltys
 */
public abstract class OwlComponent {

    private long updateInterval;

    private OwlComponentState state = OwlComponentState.UNKNOWN;
    private Date lastUpdate = new Date();

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

    public long getUpdateInterval() {
        return updateInterval;
    }

    public OwlComponentState getState() {
        return state;
    }

    public void setState(OwlComponentState state) {
        this.state = state;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
