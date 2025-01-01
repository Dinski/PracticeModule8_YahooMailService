package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestCaseCreateNewMail extends Driver {
    LoginPage loginPage;
    EmailPage emailPage;


    public TestCaseCreateNewMail() {
        super();
    }

    @BeforeClass
    public void setUp() {
        getDriver();

        loginPage = new LoginPage();
        emailPage = new EmailPage();
    }

    @Test(testName = "TC-2 Create a new mail", dataProvider = "emailFields1", dataProviderClass = TestDataProvider.class,
    priority = 2)
    public void composeEmail(String user, String pwd, String body) {
        System.out.println("This is the test code " + new Object(){}.getClass().getEnclosingMethod().getName());
        loginPage.login(user, pwd);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        emailPage.openComposeFolder();
        emailPage.fillReceiver();
        emailPage.fillSubject();
        emailPage.fillEmailText();
        emailPage.openDraftsFolder();

        String emailIsInDraftFolder = emailPage.verifyEmailIsInDraftFolder();
        assertEquals(body, emailIsInDraftFolder);
        System.out.println("Test End " + new Object(){}.getClass().getEnclosingMethod().getName());
    }
    @AfterClass
    public void close() {
        driver.quit();
    }
}
