package com.stevesoltys.owl.configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import com.stevesoltys.owl.exception.OwlConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Steve Soltys
 */
@Component
public class PluginConfigurationLoader {

    /**
     * The resource directory for plugins.
     */
    public static final String RESOURCE_DIRECTORY = "plugins";

    /**
     * The name of the configuration file for a plugin.
     */
    private static final String CONFIGURATION_FILE = "plugin.json";

    /**
     * The resource loader.
     */
    private final ResourceLoader resourceLoader;

    /**
     * The plugin configuration.
     */
    private final PluginConfiguration pluginConfiguration;

    @Autowired
    public PluginConfigurationLoader(ResourceLoader resourceLoader, PluginConfiguration pluginConfiguration) {
        this.resourceLoader = resourceLoader;
        this.pluginConfiguration = pluginConfiguration;
    }

    public void initialize() throws OwlConfigurationException {

        URL resourceUrl = Resources.getResource(RESOURCE_DIRECTORY);
        File pluginDirectory = new File(resourceUrl.getFile());

        if(pluginDirectory.exists() && pluginDirectory.isDirectory()) {
            loadPlugins(pluginDirectory);
        } else {
            throw new OwlConfigurationException("Could not find plugin resource directory.");
        }

    }

    private void loadPlugins(File directory) throws OwlConfigurationException {
        for(File folder : directory.listFiles()) {

            if(!folder.isDirectory() || folder.getName().startsWith(".")) {
                continue;
            }

            File configurationFile = new File(folder, CONFIGURATION_FILE);

            if(configurationFile.exists()) {
                loadPlugin(folder, configurationFile);
            } else {
                loadPlugins(folder);
            }
        }
    }

    private void loadPlugin(File directory, File configurationFile) throws OwlConfigurationException {

        try {
            String configurationString = Files.toString(configurationFile, Charsets.UTF_8);
            GsonJsonParser parser = new GsonJsonParser();

            pluginConfiguration.initialize(directory, parser.parseMap(configurationString));
        } catch (IOException e) {
            throw new OwlConfigurationException("Could not read plugin configuration file: " + e.getMessage());
        }
    }

}
