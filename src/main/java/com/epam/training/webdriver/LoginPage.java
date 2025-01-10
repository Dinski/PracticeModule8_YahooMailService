package com.epam.training.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPage extends Driver {

    private final WebDriver driver;
    private final Logger logger = Logger.getLogger(Driver.class.getName());

    @FindBy(id = "login-username")
    private WebElement userName;

    @FindBy(id = "login-passwd")
    private WebElement password;

    @FindBy(id = "login-signin")
    private WebElement nextButton;

    @FindBy(name = "confirmCommChannels")
    private WebElement doneButton;

    @FindBy(id = "ybarMailIndicator")
    private WebElement mailIcon;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pwd) {
        try {
            logger.info("Performing login...");
           waitForElementToBeVisible(userName);
           userName.sendKeys(user);
            logger.info("Username entered.");
           waitForElementToBeClickable(nextButton);
           nextButton.click();
           waitForElementToBeVisible(password);
            password.sendKeys(pwd);
            logger.info("Password entered.");
            waitForElementToBeClickable(nextButton);
            nextButton.click();
            logger.info("Navigating to mailbox.");
            mailIcon.click();
            logger.info("Mailbox icon clicked.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Error during login process", e);
            throw e;
        }
    }
}
