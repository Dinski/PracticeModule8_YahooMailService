package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;
import org.testng.annotations.*;

import static com.epam.training.webdriver.test.TestDataProvider.maskSensitiveValue;
import static org.testng.Assert.assertEquals;
import java.util.logging.Level;

/**
 * Test class for sending an email and verifying its presence in the Sent folder.
 */
public class TestCaseSendEmail extends Driver {
    public LoginPage loginPage;
    public EmailPage emailPage;

    @BeforeMethod
    public void initializePages() {
        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        emailPage = new EmailPage(driver);
    }

    /**
     * Test case for sending an email and verifying its presence in the Sent folder.
     *
     * @param usernameKey The username for logging into the email account.
     * @param passwordKey  The password for the email account.
     * @param body The body content of the email to be sent.
     */
    @Test(testName = "TC-4 Send the mail", dataProvider = "emailFields1", dataProviderClass = TestDataProvider.class,
            priority = 4)
    public void sendEmail(String usernameKey, String passwordKey, String body) {
        try {
            logger.info("Starting the process to send an email.");

            String  username = Driver.getDecryptedValue(usernameKey);
            String password = Driver.getDecryptedValue(passwordKey);
            // Step 1: Login to the application
            loginPage.login(username, password);
            logger.info("Login successful for username: " + maskSensitiveValue(username) +
                    " with password: " + maskSensitiveValue(password));

            // Step 2: Switch to the new window that opens after login
            String originalWindow = driver.getWindowHandle();
            switchToNewWindow(originalWindow);

            // Step 3: Open the Drafts folder
            emailPage.openDraftsFolder();
            logger.info("Drafts folder opened.");

            // Step 4: Open the draft email
            emailPage.openDraftEmail();
            logger.info("Draft email opened.");

            // Step 5: Send the email
            emailPage.sendEmail();
            logger.info("Email sent successfully.");

            // Step 6: Open the Sent folder
            emailPage.openSentFolder();
            logger.info("Sent folder opened.");

            // Step 7: Switch back to the original window
            switchToNewWindow(originalWindow);

            // Step 8: Verify if the email appears in the Sent folder
            String emailIsInSentFolder = emailPage.verifyEmailIsInSentFolder();
            logger.info("Email in sent folder: " + emailIsInSentFolder);

            // Assert that the body of the sent email matches the provided body
            assertEquals(emailIsInSentFolder, body,
                    "The email body does not match the expected content in the sent folder.");

            // Step 9: Log out from the email application
            emailPage.logOut();
            logger.info("Logged out from the email application.");

            // Step 10: Clean up - close the current window
            driver.close();
            logger.info("Current window closed.");

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during email sending process.", e);
            throw e; // Rethrow the exception after logging
        }
    }
}
