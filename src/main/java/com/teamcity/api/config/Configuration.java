package com.teamcity.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private final static String CONFIG_PROPERTIES = "config.properties";
    private static Configuration configuration;
    private Properties properties;

    private Configuration() {
        properties = new Properties();
        loadProperties(CONFIG_PROPERTIES);
    }

    private static Configuration getConfiguration() {
        if (configuration == null) {
            configuration = new Configuration();
        }
        return configuration;
    }

    public void loadProperties(String fileName) {
        try (InputStream stream = Configuration.class.getClassLoader().getResourceAsStream(fileName)) {
            if (stream == null) {
                System.err.println("File not found: " + fileName);
            }
            properties.load(stream);
        } catch (IOException e) {
            System.err.println("Error during file reading: " + fileName);
            throw new RuntimeException(e);
        }
    }

    public static String getProperty(String key) {
        return getConfiguration().properties.getProperty(key);
    }
}
