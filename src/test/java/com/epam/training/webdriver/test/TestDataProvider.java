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

    @DataProvider(name = "validUserCredentials")
    public static Object[][] validUserCredentials() {
        String email = System.getenv("USER_EMAIL");
        String password = System.getenv("USER_PASSWORD");

        if (email == null || password == null) {
            logger.severe("Environment variables for user credentials are not set.");
            throw new IllegalStateException("Missing required environment variables for user credentials.");
        }

        logger.info("Providing credentials for test with masked email: username: " + maskSensitiveValue(email) + ", password: " + maskSensitiveValue(password));

        return new Object[][]{
                {email, password}
        };
    }

    @DataProvider(name = "emailFields1")
    public static Object[][] emailFields1() {
        String email = System.getenv("USER_EMAIL");
        String password = System.getenv("USER_PASSWORD");
        String emailBody = System.getenv("EMAIL_BODY");

        if (email == null || emailBody == null || password == null) {
            logger.severe("Environment variables for email fields are not set.");
            throw new IllegalStateException("Missing required environment variables for email fields.");
        }

        logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(email));
        logger.info("Providing password for test with masked password: " + maskSensitiveValue(password));
        return new Object[][]{
                {email, password, emailBody}
        };
    }

    @DataProvider(name = "emailFields")
    public static Object[][] emailFields() {
        String email = System.getenv("FROM_EMAIL");
        String password = System.getenv("FROM_PASSWORD");
        String to = System.getenv("TO_EMAIL");
        String subject = System.getenv("EMAIL_SUBJECT");
        String emailBody = System.getenv("EMAIL_BODY");

        if (email == null || password == null || to == null || subject == null || emailBody == null) {
            logger.severe("Environment variables for email fields are not set.");
            throw new IllegalStateException("Missing required environment variables for email fields.");
        }

        logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(email));
        return new Object[][]{
                {email, password, to, subject, emailBody}
        };
    }
}

