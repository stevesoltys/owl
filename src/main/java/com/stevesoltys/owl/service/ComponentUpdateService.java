package com.stevesoltys.owl.service;

import com.stevesoltys.owl.controller.component.OwlComponentController;
import com.stevesoltys.owl.model.component.CPULoadComponent;
import com.stevesoltys.owl.model.component.OwlComponent;
import com.stevesoltys.owl.repository.OwlComponentControllerRepository;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A {@link Service} which schedules update tasks for the currently loaded components.
 *
 * @author Steve Soltys
 */
@Service
public class ComponentUpdateService {

    /**
     * The size of the thread pool. Set to the number of available processors.
     */
    private final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * The scheduled task executor for this service.
     */
    private final ScheduledThreadPoolExecutor taskExecutor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);

    @Autowired
    private OwlComponentRepository componentRepository;

    @Autowired
    private OwlComponentControllerRepository controllerRepository;

    /**
     * Iterates through each registered component and schedules an update task for that component at the given interval.
     */
    @SuppressWarnings("unchecked")
    public void initialize() {

        for (OwlComponent component : componentRepository.getComponents()) {
            OwlComponentController controller = controllerRepository.getController(component);

            taskExecutor.scheduleAtFixedRate((Runnable) () -> controller.update(component), 0,
                    component.getUpdateInterval(), TimeUnit.SECONDS);
        }
    }

}