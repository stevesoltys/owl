package com.stevesoltys.owl.controller.component;

import com.stevesoltys.owl.controller.OwlComponentController;
import com.stevesoltys.owl.model.component.CPULoadComponent;
import com.stevesoltys.owl.model.OwlComponentState;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.sql.Time;
import java.time.Instant;

/**
 * A component controller for a {@link CPULoadComponent}.
 *
 * @author Steve Soltys
 */
public class CPULoadComponentController extends OwlComponentController<CPULoadComponent> {

    @Override
    public void initialize(CPULoadComponent component) {
        component.setState(OwlComponentState.OK);
    }

    @Override
    public void update(CPULoadComponent component) {
        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();

        component.setCurrentLoad(bean.getSystemLoadAverage());
        component.setLastUpdate(Time.from(Instant.now()));
    }
}
