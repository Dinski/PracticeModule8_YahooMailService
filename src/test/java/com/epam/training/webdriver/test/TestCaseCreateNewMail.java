package com.epam.training.webdriver.test;

import com.epam.training.webdriver.*;
import com.epam.training.webdriver.model.Email;
import com.epam.training.webdriver.model.User;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import util.TestListener;

import static org.testng.Assert.assertEquals;

/**
 * Test class for creating a new email and verifying it is saved in the Drafts folder.
 */
@Listeners(TestListener.class)
public class TestCaseCreateNewMail extends Driver {

    public LoginPage loginPage;
    public EmailPage emailPage;

    @BeforeClass
    public void initializePages() {
        // Initialize Page Objects
        loginPage = new LoginPage(driver);
        emailPage = new EmailPage(driver);
    }

    /**
     * Composes a new email and verifies it is saved in the Drafts folder.
     *
     * @param userCredentials The username and password for logging into the email account.
     * @param email The email sender, receiver, subject and content.
     */

    @Test(testName = "TC-2 Create a new mail", dataProvider = "emailFields", dataProviderClass = TestDataProvider.class,
    priority = 2)
    public void composeEmail(User userCredentials, Email email) {

        try {
            logger.info("Starting the email composition process.");

            // Step 1: Login to the application
            loginPage.login(userCredentials);
            logger.info("Login successful");

            // Step 2: Switch to the new window that opens after login
            String originalWindow = driver.getWindowHandle();
            loginPage.switchToNewWindow(originalWindow);

            // Step 3: Open the Compose folder
            emailPage.openComposeFolder().click();
            logger.info("Compose folder opened.");

            // Step 4: Fill the receiver, subject, and body
            emailPage.composeEmail(email);
            logger.info("Receiver field filled with: " + email.getReceiver());

            // Step 5: Open the Drafts folder and verify the email is saved there
            emailPage.openDraftsFolder();
            logger.info("Drafts folder opened.");

            String emailInDraftFolder = emailPage.verifyEmailIsInDraftFolder();
            logger.info("Email in drafts folder: " + emailInDraftFolder);

            // Assert that the email content matches the one saved in the Drafts folder
            assertEquals(emailInDraftFolder, email.getContent(), "The email body does not match the expected content in the drafts folder.");
            logger.info("Actual email content: " + emailInDraftFolder + " matches the expected email content: "
                    + email.getContent() + " in Drafts folder");

            // Step 6: Clean up - close the current window and return to the original window
            driver.close();
            logger.info("Current window closed.");

        } catch (Exception e) {
            logger.error("Error during email composition process.", e);
            throw e; // Rethrow the exception after logging
        }
    }
}
