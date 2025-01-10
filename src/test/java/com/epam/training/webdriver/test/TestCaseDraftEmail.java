package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;
import org.testng.annotations.*;
import static org.testng.Assert.assertEquals;
import java.util.logging.Level;

/**
 * Test class for verifying the fields of a draft email in the email application.
 */
public class TestCaseDraftEmail extends Driver {
    public LoginPage loginPage;
    public EmailPage emailPage;

    @BeforeClass
    public void initializePages() {
        loginPage = new LoginPage(driver);
        emailPage = new EmailPage(driver);
    }


    /**
     * Test case to verify the fields (receiver, subject, and body) of a draft email.
     *
     * @param usernameKey The username for logging into the email account.
     * @param passwordKey  The password for the email account.
     * @param to   The recipient's email address.
     * @param sub  The subject of the draft email.
     * @param body The body content of the draft email.
     */
    @Test(testName = "TC-3 Verify email fields", dataProvider = "emailFields", dataProviderClass = TestDataProvider.class,
            priority = 3)
    public void verifyEmailFields(String usernameKey, String passwordKey, String to, String sub, String body) {
        try {
            String username = Driver.getDecryptedValue(usernameKey);
            String password = Driver.getDecryptedValue(passwordKey);

            logger.info("Starting the verification of email fields.");

            // Step 1: Login with valid credentials
            loginPage.login(username, password);
            logger.info("Login successful");

            // Switch to the new window that opens after login
            String originalWindow = driver.getWindowHandle();
            switchToNewWindow(originalWindow);

            // Step 3: Open the Drafts folder
            emailPage.openDraftsFolder();
            logger.info("Drafts folder opened.");

            // Step 4: Open the draft email
            emailPage.openDraftEmail();
            logger.info("Draft email opened.");

            // Step 5: Verify the receiver field
            String receiver = emailPage.validateReceiver();
            logger.info("Verified receiver: " + receiver);
            assertEquals(receiver, to, "Receiver does not match the expected value.");

            // Step 6: Verify the subject field
            String subject = emailPage.validateSubject();
            logger.info("Verified subject: " + subject);
            assertEquals(subject, sub, "Subject does not match the expected value.");

            // Step 7: Verify the email body
            String text = emailPage.validateText();
            logger.info("Verified email body: " + text);
            assertEquals(text, body, "Email body does not match the expected value.");

            driver.close();

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during email fields verification.", e);
            throw e; // Rethrow the exception after logging
        }
    }
}


