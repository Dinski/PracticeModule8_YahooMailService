package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;
import java.util.logging.Level;

import static com.epam.training.webdriver.test.TestDataProvider.maskSensitiveValue;
import static org.testng.Assert.assertEquals;

/**
 * Test class for creating a new email and verifying it is saved in the Drafts folder.
 */
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
     * @param user The username for logging into the email account.
     * @param pwd  The password for the email account.
     * @param to   The recipient's email address.
     * @param sub  The subject of the email.
     * @param body The body content of the email.
     */
    @Test(testName = "TC-2 Create a new mail", dataProvider = "emailFields", dataProviderClass = TestDataProvider.class,
    priority = 2)
    public void composeEmail(String user, String pwd, String to, String sub, String body) {
        logger.info(maskSensitiveValue(user + pwd + to + sub + body) );
      try {
        logger.info("Starting the email composition process.");

        // Step 1: Login to the application
        loginPage.login(user, pwd);
        logger.info("Login successful" );

        // Step 2: Switch to the new window that opens after login
        String originalWindow = driver.getWindowHandle();
        switchToNewWindow(originalWindow);

        // Step 3: Open the Compose folder
        emailPage.openComposeFolder();
        logger.info("Compose folder opened.");

        // Step 4: Fill the receiver, subject, and body
        emailPage.fillReceiver(to);
        logger.info("Receiver field filled with: " + to);

        emailPage.fillSubject(sub);
        logger.info("Subject field filled with: " + sub);

        emailPage.fillEmailText(body);

        // Step 5: Open the Drafts folder and verify the email is saved there
        emailPage.openDraftsFolder();
        logger.info("Drafts folder opened.");

        String emailInDraftFolder = emailPage.verifyEmailIsInDraftFolder();
        logger.info("Email in drafts folder: " + emailInDraftFolder);

        // Assert that the email content matches the one saved in the Drafts folder
        assertEquals(emailInDraftFolder, body, "The email body does not match the expected content in the drafts folder.");

        // Step 6: Clean up - close the current window and return to the original window
        driver.close();
        logger.info("Current window closed.");

    } catch (Exception e) {
        logger.log(Level.SEVERE, "Error during email composition process.", e);
        throw e; // Rethrow the exception after logging
    }
}

}
