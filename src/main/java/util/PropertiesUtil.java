package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class PropertiesUtil {

    private static final Logger logger = LogManager.getLogger(PropertiesUtil.class.getName());
    private static final Properties properties = new Properties();

    //set the environment and load the suitable properties
    static {
        String environment = System.getProperty("env", "staging");
        String configFilePath = String.format("src/main/resources/config_%s_test.properties", environment);

        // Load the properties file
        try (FileInputStream input = new FileInputStream(configFilePath)) {
            properties.load(input);
            logger.info("Loaded configuration for environment: " + environment);
        } catch (IOException e) {
            logger.error("Failed to load configuration file: " + configFilePath);
            throw new RuntimeException("Could not load configuration file", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

}

