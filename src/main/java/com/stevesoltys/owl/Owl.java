package com.stevesoltys.owl;

import com.stevesoltys.owl.config.OwlConfigurationLoader;
import com.stevesoltys.owl.service.ComponentUpdateService;
import com.stevesoltys.owl.service.OwlServiceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author Steve Soltys
 */
@SpringBootApplication
@EnableScheduling
public class Owl implements CommandLineRunner {

    @Autowired
    private OwlConfigurationLoader configurationLoader;

    @Autowired
    private OwlServiceLoader serviceLoader;

    @Override
    public void run(String... args) throws Exception {
        configurationLoader.initialize();
        serviceLoader.initialize();
    }

    public static void main(String[] args) {
        SpringApplication.run(Owl.class, args);
    }
}
