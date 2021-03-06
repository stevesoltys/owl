package com.stevesoltys.owl.service;

import com.google.common.collect.Multimap;
import com.stevesoltys.owl.controller.OwlComponentController;
import com.stevesoltys.owl.model.OwlComponent;
import com.stevesoltys.owl.repository.OwlComponentControllerRepository;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private static final int THREAD_POOL_SIZE = Runtime.getRuntime().availableProcessors();

    /**
     * The scheduled task executor for this service.
     */
    private static final ScheduledThreadPoolExecutor taskExecutor = new ScheduledThreadPoolExecutor(THREAD_POOL_SIZE);

    /**
     * The component repository.
     */
    private final OwlComponentRepository componentRepository;

    /**
     * The component controller repository.
     */
    private final OwlComponentControllerRepository controllerRepository;

    @Autowired
    public ComponentUpdateService(OwlComponentRepository componentRepository, OwlComponentControllerRepository controllerRepository) {
        this.componentRepository = componentRepository;
        this.controllerRepository = controllerRepository;
    }

    /**
     * Iterates through each registered component and schedules an update task for that component at the given interval.
     */
    @SuppressWarnings("unchecked")
    public void initialize() {
        Multimap<String, OwlComponent> components = componentRepository.getComponentMap();

        components.keySet().stream().forEach(identifier -> components.get(identifier).stream().forEach(component -> {

            OwlComponentController controller = controllerRepository.getController(identifier);
            controller.init(component);

            taskExecutor.scheduleAtFixedRate(() -> {

                try {
                    controller.update(component);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }, 0, component.getUpdateInterval(), TimeUnit.SECONDS);
        }));
    }

}
