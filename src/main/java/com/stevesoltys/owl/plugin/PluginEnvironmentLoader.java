package com.stevesoltys.owl.plugin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Steve Soltys
 */
@Component
public class PluginEnvironmentLoader {

    /**
     * The Ruby plugin environment.
     */
    private final RubyPluginEnvironment rubyPluginEnvironment;

    /**
     * The Python plugin environment.
     */
    private final PythonPluginEnvironment pythonPluginEnvironment;

    @Autowired
    public PluginEnvironmentLoader(RubyPluginEnvironment rubyEnvironment, PythonPluginEnvironment pythonEnvironment) {
        this.rubyPluginEnvironment = rubyEnvironment;
        this.pythonPluginEnvironment = pythonEnvironment;
    }

    /**
     * Initializes the {@link PluginEnvironment}s.
     */
    public void initialize() {
        rubyPluginEnvironment.initialize();
        pythonPluginEnvironment.initialize();
    }
}
