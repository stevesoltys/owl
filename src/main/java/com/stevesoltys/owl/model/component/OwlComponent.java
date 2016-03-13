package com.stevesoltys.owl.model.component;

import java.util.Date;

/**
 * @author Steve Soltys
 */
public class OwlComponent {

    private OwlComponentState state = OwlComponentState.UNKNOWN;
    private Date lastUpdate = new Date();

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
