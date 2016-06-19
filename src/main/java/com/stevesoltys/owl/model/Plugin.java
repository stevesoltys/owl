package com.stevesoltys.owl.model;

import java.util.List;

/**
 * A plugin.
 *
 * @author Steve Soltys
 */
public class Plugin {

    /**
     * The name of this plugin.
     */
    private final String name;

    /**
     * A description of this plugin.
     */
    private final String description;

    /**
     * The type for this plugin.
     */
    private final String type;

    /**
     * The list of scripts for this plugin.
     */
    private final List<String> scripts;

    public Plugin(String name, String description, String type, List<String> scripts) {
        this.name = name;
        this.description = description;
        this.type = type;
        this.scripts = scripts;
    }

    /**
     * Gets the name of this plugin.
     *
     * @return The name.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the description for this plugin.
     *
     * @return The description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Gets the type for this plugin.
     *
     * @return The type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the list of scripts for this plugin.
     *
     * @return The list of scripts.
     */
    public List<String> getScripts() {
        return scripts;
    }
}
