package com.stevesoltys.owl.plugin;

import com.stevesoltys.owl.model.Plugin;
import com.stevesoltys.owl.repository.PluginRepository;
import org.jruby.embed.ScriptingContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * A Ruby plugin environment.
 *
 * @author Steve Soltys
 */
@Component
public class RubyPluginEnvironment extends PluginEnvironment {

    /**
     * The name of the global {@link PluginContext} variable.
     */
    private static final String PLUGIN_CONTEXT_VARIABLE_NAME = "$application";

    /**
     * The type identifier for a JRuby plugin that is used for bootstrapping the environment.
     */
    private static final String RUBY_BOOTSTRAP_PLUGIN_TYPE = "ruby_bootstrap";

    /**
     * The type identifier for a JRuby plugin.
     */
    private static final String RUBY_PLUGIN_TYPE = "ruby";

    /**
     * The scripting container.
     */
    private final ScriptingContainer scriptingContainer = new ScriptingContainer();

    /**
     * The plugin context.
     */
    private final PluginContext pluginContext;

    /**
     * The plugin repository.
     */
    private final PluginRepository pluginRepository;

    @Autowired
    public RubyPluginEnvironment(PluginContext pluginContext, PluginRepository pluginRepository) {
        this.pluginContext = pluginContext;
        this.pluginRepository = pluginRepository;
    }

    @Override
    protected void initialize() {
        scriptingContainer.put(PLUGIN_CONTEXT_VARIABLE_NAME, pluginContext);

        Set<Plugin> rubyBootstrapPlugins = pluginRepository.getPluginsByType(RUBY_BOOTSTRAP_PLUGIN_TYPE);
        rubyBootstrapPlugins.forEach(this::run);

        Set<Plugin> rubyPlugins = pluginRepository.getPluginsByType(RUBY_PLUGIN_TYPE);
        rubyPlugins.forEach(this::run);
    }

    @Override
    protected void run(Plugin plugin) {
        plugin.getScripts().forEach(scriptingContainer::runScriptlet);
    }
}
