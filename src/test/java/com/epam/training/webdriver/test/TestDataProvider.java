package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.EncryptionUtil;
import util.PropertiesUtil;
import com.epam.training.webdriver.model.Email;
import com.epam.training.webdriver.model.User;
import org.testng.annotations.DataProvider;

public class TestDataProvider {
    public static Logger logger = LogManager.getLogger(Driver.class.getName());

    // Helper method to mask sensitive information for logs
    static String maskSensitiveValue(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        return value.replaceAll(".", "*");
    }

    @DataProvider(name = "userCredentials")
    public static Object[][] userCredentials() throws Exception {
        String encryptedUsername = PropertiesUtil.getProperty("encrypted.username");
        String encryptedPassword = PropertiesUtil.getProperty("encrypted.password");

        if (encryptedUsername == null || encryptedPassword == null )
        {
            logger.warn("Properties for email fields are not set.");
            throw new IllegalStateException("Missing required properties for email fields.");
        }

        String username = EncryptionUtil.decrypt(encryptedUsername);
        String password = EncryptionUtil.decrypt(encryptedPassword);

        logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(username));
        logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(password));

        return new Object[][]{
                {new User(username, password)}
        };
    }

    @DataProvider(name = "emailFields")
    public static Object[][] emailFields() throws Exception {

        String encryptedSender = PropertiesUtil.getProperty("encrypted.username");
        String encryptedPassword = PropertiesUtil.getProperty("encrypted.password");
        String encryptedTo = PropertiesUtil.getProperty("TO_EMAIL");
        String encryptedSubject = PropertiesUtil.getProperty("EMAIL_SUBJECT");
        String encryptedBody = PropertiesUtil.getProperty("EMAIL_BODY");

        if (encryptedSender == null || encryptedPassword == null || encryptedTo == null ||
                encryptedSubject == null || encryptedBody == null) {
            logger.warn("Properties for email fields are not set.");
            throw new IllegalStateException("Missing required properties for email fields.");
        }

       // Decrypt sensitive information using EncryptionUtil
        String username = EncryptionUtil.decrypt(encryptedSender);
        String password = EncryptionUtil.decrypt(encryptedPassword);
        String receiver = EncryptionUtil.decrypt(encryptedTo);
        String subject = EncryptionUtil.decrypt(encryptedSubject);
        String content = EncryptionUtil.decrypt(encryptedBody);

        logger.info("Providing email fields for test with masked sender email: " + maskSensitiveValue(receiver));

        return new Object[][]{
                {new User(username, password),
                new Email(username,receiver,subject,content)}
        };
    }
}