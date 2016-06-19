package com.stevesoltys.owl;

import com.stevesoltys.owl.configuration.OwlConfigurationLoader;
import com.stevesoltys.owl.configuration.PluginConfigurationLoader;
import com.stevesoltys.owl.plugin.PluginEnvironmentLoader;
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
    private PluginConfigurationLoader pluginConfigurationLoader;

    @Autowired
    private PluginEnvironmentLoader pluginEnvironmentLoader;

    @Autowired
    private OwlServiceLoader serviceLoader;

    @Override
    public void run(String... args) throws Exception {
        pluginConfigurationLoader.initialize();
        pluginEnvironmentLoader.initialize();
        configurationLoader.initialize();
        serviceLoader.initialize();
    }

    public static void main(String[] args) {
        SpringApplication.run(Owl.class, args);
    }
}
