package com.stevesoltys.owl.model.component;

import com.stevesoltys.owl.model.component.OwlComponent;

/**
 * @author Steve Soltys
 */
public class CPULoadComponent extends OwlComponent {

    private double currentLoad = 0;

    public double getCurrentLoad() {
        return currentLoad;
    }

    public void setCurrentLoad(double currentLoad) {
        this.currentLoad = currentLoad;
    }
}
