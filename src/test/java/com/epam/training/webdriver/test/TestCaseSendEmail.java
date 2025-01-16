package com.epam.training.webdriver.test;

import com.epam.training.webdriver.*;
import com.epam.training.webdriver.model.Email;
import com.epam.training.webdriver.model.User;
import org.testng.annotations.*;
import util.TestListener;

import static com.epam.training.webdriver.test.TestDataProvider.maskSensitiveValue;
import static org.testng.Assert.assertEquals;

/**
 * Test class for sending an email and verifying its presence in the Sent folder.
 */
@Listeners(TestListener.class)
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
     * Test case to verify the fields (receiver, subject, and body) of a draft email.
     *
     * @param userCredentials The username and password for logging into the email account.
     * @param email The email sender, receiver, subject and content.
     *
     */
    @Test(testName = "TC-4 Send the mail", dataProvider = "emailFields", dataProviderClass = TestDataProvider.class,
            priority = 4)
    public void sendEmail(User userCredentials, Email email) {
        try {
            logger.info("Starting the process to send an email.");

            // Step 1: Login to the application
            loginPage.login(userCredentials);
            logger.info("Login successful for username: " + maskSensitiveValue(userCredentials.getUsername()) +
                    " with password: " + maskSensitiveValue(userCredentials.getPassword()));

            // Step 2: Switch to the new window that opens after login
            String originalWindow = driver.getWindowHandle();
            loginPage.switchToNewWindow(originalWindow);

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
            loginPage.switchToNewWindow(originalWindow);

            // Step 8: Verify if the email appears in the Sent folder
            String emailIsInSentFolder = emailPage.verifyEmailIsInSentFolder();
            logger.info("Email in sent folder: " + emailIsInSentFolder);

            // Assert that the content of the sent email matches the provided content
            assertEquals(emailIsInSentFolder, email.getContent(),
                    "The email body does not match the expected content in the sent folder.");
            logger.info("Actual email content: " + emailIsInSentFolder + " matches the expected email content: "
                    + email.getContent() + " in Sent folder");

            // Step 9: Log out from the email application
            emailPage.logOut();
            logger.info("Logged out from the email application.");

        } catch (Exception e) {
            logger.error("Error during email sending process.", e);
            throw e; // Rethrow the exception after logging
        }
    }
}