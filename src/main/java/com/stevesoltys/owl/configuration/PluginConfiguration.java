package com.stevesoltys.owl.configuration;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.stevesoltys.owl.exception.OwlConfigurationException;
import com.stevesoltys.owl.model.Plugin;
import com.stevesoltys.owl.repository.PluginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author Steve Soltys
 */
@Component
public class PluginConfiguration {

    /**
     * The configuration key for the name of a plugin.
     */
    private static final String NAME_KEY = "name";

    /**
     * The configuration key for the description of a plugin.
     */
    private static final String DESCRIPTION_KEY = "description";

    /**
     * The configuration key for the type of a plugin.
     */
    private static final String TYPE_KEY = "type";

    /**
     * The configuration key for the script list of a plugin.
     */
    private static final String SCRIPT_LIST_KEY = "scripts";

    /**
     * The plugin repository.
     */
    private final PluginRepository pluginRepository;

    @Autowired
    public PluginConfiguration(PluginRepository pluginRepository) {
        this.pluginRepository = pluginRepository;
    }

    /**
     * Loads a plugin, given the plugin directory and configuration map.
     *
     * @param pluginDirectory The plugin directory.
     * @param configuration   The plugin configuration.
     * @throws OwlConfigurationException If the plugin configuration is invalid.
     */
    @SuppressWarnings("unchecked")
    public void initialize(File pluginDirectory, Map<String, Object> configuration) throws OwlConfigurationException {

        try {
            String name = (String) configuration.get(NAME_KEY);
            String description = (String) configuration.get(DESCRIPTION_KEY);
            String type = (String) configuration.get(TYPE_KEY);

            List<String> scriptFiles = (List<String>) configuration.get(SCRIPT_LIST_KEY);
            List<String> scripts = loadScripts(pluginDirectory, scriptFiles);

            Plugin plugin = new Plugin(name, description, type, scripts);
            pluginRepository.registerPlugin(plugin);

        } catch (NullPointerException | ClassCastException e) {
            throw new OwlConfigurationException("Invalid plugin configuration.");
        }
    }

    /**
     * Loads scripts for the given plugin directory and returns them as a list.
     *
     * @param pluginDirectory The plugin directory.
     * @param scriptFiles     The list of script files within the directory.
     * @return A list containing the script files for the plugin.
     * @throws OwlConfigurationException If an error occurs while reading one of the script files.
     */
    private List<String> loadScripts(File pluginDirectory, List<String> scriptFiles) throws OwlConfigurationException {

        List<String> scripts = new LinkedList<>();

        for (String fileName : scriptFiles) {
            try {

                File scriptFile = new File(pluginDirectory, fileName);

                String script = Files.toString(scriptFile, Charsets.UTF_8);
                scripts.add(script);

            } catch (IOException e) {
                throw new OwlConfigurationException("Could not read plugin script: " + e.getMessage());
            }
        }

        return scripts;
    }
}
