package com.epam.training.webdriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends Driver {

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

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pwd) {
        userName.sendKeys(user);
        nextButton.click();
        password.sendKeys(pwd);
        nextButton.click();
        mailIcon.click();

    }
}
