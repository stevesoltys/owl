package com.stevesoltys.owl.controller.component;

import com.stevesoltys.owl.controller.component.OwlComponentController;
import com.stevesoltys.owl.model.component.CPULoadComponent;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

/**
 * @author Steve Soltys
 */
public class CPULoadComponentController extends OwlComponentController<CPULoadComponent> {

    @Override
    public void update(CPULoadComponent component) {
        OperatingSystemMXBean bean = ManagementFactory.getOperatingSystemMXBean();

        component.setCurrentLoad(bean.getSystemLoadAverage());
    }
}
