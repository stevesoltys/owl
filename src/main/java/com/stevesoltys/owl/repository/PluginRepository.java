package com.stevesoltys.owl.repository;

import com.stevesoltys.owl.model.Plugin;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Steve Soltys
 */
@Repository
public class PluginRepository {

    /**
     * The set of plugins for this repository.
     */
    private final Set<Plugin> plugins = new HashSet<>();

    /**
     * Registers the given plugin in this repository.
     *
     * @param plugin The plugin to register.
     */
    public void registerPlugin(Plugin plugin) {
        plugins.add(plugin);
    }

    /**
     * Gets a subset of plugins from this repository, filtering by the given type.
     *
     * @param type The type.
     * @return The plugin set.
     */
    public Set<Plugin> getPluginsByType(String type) {
        return plugins.stream().filter(plugin -> plugin.getType().equals(type)).collect(Collectors.toSet());
    }

    /**
     * Gets the set of plugins in this repository.
     *
     * @return The plugin set.
     */
    public Set<Plugin> getPlugins() {
        return plugins;
    }
}
