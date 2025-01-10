package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import java.util.logging.Level;

import static com.epam.training.webdriver.test.TestDataProvider.maskSensitiveValue;
import static org.testng.Assert.*;

/**
 * Test class for Yahoo login functionality.
 * It verifies the successful login process using valid credentials.
 */
public class TestCaseLogin extends Driver {

    public LoginPage loginPage;
    public EmailPage emailPage;

    @BeforeMethod
    public void initializePages() {
        loginPage = new LoginPage(driver);
        emailPage = new EmailPage(driver);
    }

    /**
     * Tests the successful login process for a user.
     * Validates the login process by comparing the actual page title with the expected title.
     *
     * @param usernameKey The username for login, fetched from the data provider.
     * @param passwordKey The password for login, fetched from the data provider.
     */
    @Test(testName = "TC-1 User login", dataProvider =  "validUserCredentials",
            dataProviderClass = TestDataProvider.class, priority = 1)
    public void successfulLogin(String usernameKey, String passwordKey) {
        try {
            String username = Driver.getDecryptedValue(usernameKey);
            String password = Driver.getDecryptedValue(passwordKey);
            logger.info("Starting test: TC-1 User login");

            // Step 1: Login to the application
            loginPage.login(username, password);
            logger.info("Login successful for username: " + maskSensitiveValue(username) +
                    " with password: " + maskSensitiveValue(password));

            // Step 2: Switch to the new window for email validation
            String originalWindow = driver.getWindowHandle();
            switchToNewWindow(originalWindow);
            emailPage.validateLogin();

            // Step 3: Validate the login result by comparing page title
            String actual = emailPage.validateLogin();
            assertEquals("(Няма нови имейли) – " + username + " – Yahoo Mail", actual, "Page title does not match");
            logger.info("Test passed: TC-1 User Login");
        }catch (Exception e){
            logger.log(Level.SEVERE, "Test failed due to an error", e);
            throw e;
        }
    }
}
