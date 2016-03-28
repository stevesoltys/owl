package com.stevesoltys.owl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Steve Soltys
 */
@Component
public class OwlServiceLoader {

    @Autowired
    private ComponentUpdateService componentUpdateService;

    public void initialize() {
        componentUpdateService.initialize();
    }

}
