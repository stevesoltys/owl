package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.exception.OwlComponentException;
import com.stevesoltys.owl.model.component.OwlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author Steve Soltys
 */
@Repository
public class OwlComponentRepository {

    @Autowired
    private OwlComponentTypeRepository componentTypeRepository;

    private final Set<OwlComponent> components = new HashSet<>();

    public void registerComponent(String name) throws OwlComponentException {

        Optional<Class<? extends OwlComponent>> componentType = componentTypeRepository.getComponentClass(name);

        if(!componentType.isPresent()) {
            throw new OwlComponentException("Could not find component type named: " + name);
        }

        try {
            components.add(componentType.get().newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            throw new OwlComponentException("Could not create new component instance: " + e.getMessage());
        }
    }

}