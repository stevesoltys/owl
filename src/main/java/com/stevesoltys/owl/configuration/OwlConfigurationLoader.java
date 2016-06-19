package com.stevesoltys.owl.configuration;

import com.stevesoltys.owl.exception.OwlConfigurationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.GsonJsonParser;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * @author Steve Soltys
 */
@Component
public class OwlConfigurationLoader {

    private static final String OWL_CONFIGURATION_DIRECTORY = System.getProperty("user.home") + "/.config/owl";

    @Autowired
    private OwlConfiguration configuration;

    public void initialize() throws OwlConfigurationException {

        File configurationDirectory = new File(OWL_CONFIGURATION_DIRECTORY);

        if (!configurationDirectory.exists() && !configurationDirectory.mkdirs()) {
            throw new OwlConfigurationException("Could not create Owl configuration directory.");
        }

        File configurationFile = new File(configurationDirectory, "config.json");

        StringBuilder builder = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(configurationFile))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }

        } catch (FileNotFoundException e) {
            throw new OwlConfigurationException("Could not find Owl configuration file.");
        } catch (IOException e) {
            throw new OwlConfigurationException("Could not read Owl configuration file: " + e.getMessage());
        }

        String configurationString = builder.toString();
        GsonJsonParser parser = new GsonJsonParser();

        configuration.initialize(parser.parseMap(configurationString));
    }
}
