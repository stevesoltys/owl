package com.stevesoltys.owl.controller;

import com.stevesoltys.owl.model.OwlComponentSet;
import com.stevesoltys.owl.repository.OwlComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Soltys
 */
@RestController
@RequestMapping
public class OwlComponentRestController {

    public static final String COMPONENTS_MAPPING = "/components";

    /**
     * The component repository.
     */
    private final OwlComponentRepository componentRepository;

    @Autowired
    public OwlComponentRestController(OwlComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    /**
     * Serves a component set.
     *
     * @return The set of components.
     */
    @RequestMapping(COMPONENTS_MAPPING)
    @ResponseBody
    public OwlComponentSet serveComponents() {
        return new OwlComponentSet(componentRepository);
    }

}
