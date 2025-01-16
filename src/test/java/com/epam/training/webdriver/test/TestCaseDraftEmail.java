package com.epam.training.webdriver.test;

import com.epam.training.webdriver.*;
import com.epam.training.webdriver.model.Email;
import com.epam.training.webdriver.model.User;
import org.testng.annotations.*;
import util.TestListener;
import static org.testng.Assert.assertEquals;

/**
 * Test class for verifying the fields of a draft email in the email application.
 */
@Listeners(TestListener.class)
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
     * @param userCredentials The username and password for logging into the email account.
     * @param email The email sender, receiver, subject and content.
     *
     */
    @Test(testName = "TC-3 Verify email fields", dataProvider = "emailFields", dataProviderClass = TestDataProvider.class,
            priority = 3)
    public void verifyEmailFields(User userCredentials, Email email) {
        try {
            logger.info("Starting the verification of email fields.");

            // Step 1: Login with valid credentials
            loginPage.login(userCredentials);
            logger.info("Login successful");

            // Switch to the new window that opens after login
            String originalWindow = driver.getWindowHandle();
            loginPage.switchToNewWindow(originalWindow);

            // Step 3: Open the Drafts folder
            emailPage.openDraftsFolder();
            logger.info("Drafts folder opened.");

            // Step 4: Open the draft email
            emailPage.openDraftEmail();
            logger.info("Draft email opened.");

            // Step 5: Verify the receiver field
            String receiver = emailPage.validateReceiver();
            logger.info("Verified receiver. Actual: " + receiver + ", expected: " + email.getReceiver());
            assertEquals(receiver, email.getReceiver(), "Receiver does not match the expected value.");

            // Step 6: Verify the subject field
            String subject = emailPage.validateSubject();
            logger.info("Verified subject. Actual : " + subject + ", expected: " + email.getSubject());
            assertEquals(subject, email.getSubject(), "Subject does not match the expected value.");

            // Step 7: Verify the email content
            String text = emailPage.validateText();
            logger.info("Verified email content. Actual: " + text + ", expected: " + email.getContent());
            assertEquals(text, email.getContent(), "Email content does not match the expected value.");

        } catch (Exception e) {
            logger.error("Error during email fields verification.", e);
            throw e; // Rethrow the exception after logging
        }
    }
}