package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestCaseSendEmail extends Driver {

    LoginPage loginPage;
    EmailPage emailPage;

    public TestCaseSendEmail() {
        super();
    }

    @BeforeClass
    public void setUp() {
        getDriver();

        loginPage = new LoginPage();
        emailPage = new EmailPage();
    }

    @Test(testName = "TC-4 Send the mail", dataProvider = "emailFields1", dataProviderClass = TestDataProvider.class,
            priority = 4)
    public void sendEmail(String user, String pwd, String body){
        System.out.println("This is the test code " + new Object(){}.getClass().getEnclosingMethod().getName());
        loginPage.login(user, pwd);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        emailPage.openDraftsFolder();
        emailPage.openDraftEmail();
        emailPage.sendEmail();
        emailPage.openSentFolder();

        driver.switchTo().window((String) windowHandles[1]);
        String emailIsInSentFolder =  emailPage.verifyEmailIsInSentFolder();
        assertEquals(body, emailIsInSentFolder);
        System.out.println("Test End " + new Object(){}.getClass().getEnclosingMethod().getName());
        emailPage.logOut();
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
