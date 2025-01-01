package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class TestCaseDraftEmail extends Driver {
    LoginPage loginPage;
    EmailPage emailPage;

    public TestCaseDraftEmail() {super();
    }

    @BeforeClass
    public void setUp(){
        getDriver();

        loginPage = new LoginPage();
        emailPage = new EmailPage();
    }

    @Test(testName = "TC-3 Verify email fields", dataProvider = "emailFields", dataProviderClass = TestDataProvider.class,
    priority = 3)
    public void verifyEmailFields(String user, String pwd, String to, String sub, String body) {
        System.out.println("This is the test code " + new Object(){}.getClass().getEnclosingMethod().getName());
        loginPage.login(user, pwd);
        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);
        emailPage.openDraftsFolder();
        emailPage.openDraftEmail();

        String receiver = emailPage.validateReceiver();
        assertEquals(to, receiver);

        String subject = emailPage.validateSubject();
        assertEquals(sub, subject);

        String text = emailPage.validateText();
        assertEquals(body, text);
        System.out.println("Test End " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}


