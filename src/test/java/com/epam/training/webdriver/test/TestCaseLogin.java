package com.epam.training.webdriver.test;

import com.epam.training.webdriver.Driver;
import com.epam.training.webdriver.EmailPage;
import com.epam.training.webdriver.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestCaseLogin extends Driver {

    LoginPage loginPage;
    EmailPage emailPage;

    public TestCaseLogin() {
        super();
    }

    @BeforeTest
    public void setUp() {
        getDriver();

        loginPage = new LoginPage();
        emailPage = new EmailPage();
    }

    @Test(testName = "TC-1 User login", dataProvider =  "validUserCredentials",
            dataProviderClass = TestDataProvider.class, priority = 1)
    public void successfulLogin(String user, String pwd) {
        System.out.println("This is the test code " + new Object(){}.getClass().getEnclosingMethod().getName());
        loginPage.login(user, pwd);

        Object[] windowHandles=driver.getWindowHandles().toArray();
        driver.switchTo().window((String) windowHandles[1]);

        emailPage.validateLogin();

        String actual = emailPage.validateLogin();

        Assert.assertEquals("(Няма нови имейли) – mazgalova3@yahoo.com – Yahoo Mail", actual);
        System.out.println("Test End " + new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @AfterTest
    public void close() {
        driver.quit();
    }
}
