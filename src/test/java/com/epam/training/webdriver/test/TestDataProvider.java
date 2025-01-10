package com.epam.training.webdriver.test;

import org.testng.annotations.DataProvider;
import java.util.logging.Logger;

public class TestDataProvider {
    private static final Logger logger = Logger.getLogger(TestDataProvider.class.getName());

    // Helper method to mask sensitive information for logs
    static String maskSensitiveValue(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.replaceAll(".", "*");
    }

    private static String getEnv(String key, String defaultValue) {
        String value = System.getenv(key);
        return (value != null && !value.isBlank()) ? value : defaultValue;
    }
@DataProvider(name = "validUserCredentials")
public static Object[][] testData() {
    String username = System.getenv("encrypted.username");
    String password = System.getenv("encrypted.password");

    if (username == null || password == null) {
        logger.severe("Environment variables for email fields are not set.");
        throw new IllegalStateException("Missing required environment variables for email fields.");
    }

    logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(username));
    logger.info("Providing password for test with masked password: " + maskSensitiveValue(password));
    return new Object[][]{
            {
                    getEnv(username, "encrypted.username"),
                    getEnv(password, "encrypted.password")
            }
    };
}


    @DataProvider(name = "emailFields1")
    public static Object[][] emailFields1() {
        String username = System.getenv("encrypted.username");
        String password = System.getenv("encrypted.password");
        String emailBody = System.getenv("EMAIL_BODY");

        if (username == null || emailBody == null || password == null) {
            logger.severe("Environment variables for email fields are not set.");
            throw new IllegalStateException("Missing required environment variables for email fields.");
        }

        logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(username));
        logger.info("Providing password for test with masked password: " + maskSensitiveValue(password));
        return new Object[][]{
                {
                        (getEnv(username, "encrypted.username")),
                        (getEnv(password, "encrypted.password")),
                        (getEnv(emailBody, "Test email body"))
                }
        };
    }

    @DataProvider(name = "emailFields")
    public static Object[][] emailFields() {
        String email = System.getenv("encrypted.username");
        String password = System.getenv("encrypted.password");
        String to = System.getenv("TO_EMAIL");
        String subject = System.getenv("EMAIL_SUBJECT");
        String emailBody = System.getenv("EMAIL_BODY");
//
        if (email == null || password == null || to == null || subject == null || emailBody == null) {
            logger.severe("Environment variables for email fields are not set.");
            throw new IllegalStateException("Missing required environment variables for email fields.");
        }
        logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(email));

        return new Object[][]{
                {
                        (getEnv(email, "encrypted.username")),
                        (getEnv(password, "encrypted.password")),
                        (getEnv(to, "dinadinski@gmail.com")),
                        (getEnv(subject, "Test email")),
                        (getEnv(emailBody, "Test email body"))
                }
                };
    }
}

