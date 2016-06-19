package com.stevesoltys.owl.plugin;

import com.stevesoltys.owl.model.Plugin;

/**
 * @author Steve Soltys
 */
public abstract class PluginEnvironment {

    /**
     * Initializes this plugin environment.
     */
    protected abstract void initialize();

    /**
     * Runs the given plugin.
     *
     * @param plugin The plugin.
     */
    protected abstract void run(Plugin plugin);
}
