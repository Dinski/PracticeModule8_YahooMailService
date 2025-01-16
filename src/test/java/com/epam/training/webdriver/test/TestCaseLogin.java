package com.epam.training.webdriver.test;

import com.epam.training.webdriver.*;

import com.epam.training.webdriver.model.User;
import org.testng.annotations.*;
import util.TestListener;

import static com.epam.training.webdriver.test.TestDataProvider.maskSensitiveValue;
import static org.testng.Assert.*;

/**
 * Test class for Yahoo login functionality.
 * It verifies the successful login process using valid credentials.
 */
@Listeners(TestListener.class)
public class TestCaseLogin extends Driver {

    private LoginPage loginPage;
    private EmailPage emailPage;


    @BeforeMethod
    public void initializePages() {
        loginPage = new LoginPage(driver);
        emailPage = new EmailPage(driver);
    }

    /**
     * Tests the successful login process for a user.
     * Validates the login process by comparing the actual page title with the expected title.
     * @param userCredentials
//     */
    @Test(testName = "TC-1 User login", dataProvider =  "userCredentials",
            dataProviderClass = TestDataProvider.class, priority = 1)
    public void successfulLogin(User userCredentials) {
        try {
            logger.info("Starting test: TC-1 User login");

            // Step 1: Login to the application
            loginPage.login(userCredentials);
            logger.info("Login successful for username: " + maskSensitiveValue(userCredentials.getUsername()) +
                    " with password: " + maskSensitiveValue(userCredentials.getPassword()));

            // Step 2: Switch to the new window for email validation
            String originalWindow = driver.getWindowHandle();
            loginPage.switchToNewWindow(originalWindow);
            emailPage.validateLogin();

            // Step 3: Validate the login result by comparing page title
            String actual = emailPage.validateLogin();
            assertEquals("Yahoo Mail", actual, "Page title does not match");
            logger.info("Yahoo Mail" + " matches the expected page title: " + actual);
            logger.info("Test passed: TC-1 User Login");
        }catch (Exception e){
            logger.error("Test failed due to an error", e);
            throw e;
        }
    }
}
