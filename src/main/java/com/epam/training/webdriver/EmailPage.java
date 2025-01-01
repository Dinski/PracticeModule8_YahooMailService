package com.epam.training.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class EmailPage extends Driver {

    public EmailPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div[1]/nav/div/div[1]/a")
    private WebElement composeLink;

    @FindBy(xpath = "//*[@id=\"message-to-field\"]")
    private WebElement addressee;

    @FindBy(id = "compose-subject-input")
    private WebElement subjectField;

    @FindBy(xpath = "//*[@id=\"editor-container\"]/div[1]/div")
    private WebElement emailText;

    @FindBy(id = "senders_list")
    private WebElement senderText;

    public String validateLogin() {
      return driver.getTitle();
    }

    public void openComposeFolder() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    composeLink.click();
                    return true;
                });
    }

    public void fillReceiver() {
        addressee.sendKeys("dinadinski@gmail.com");
    }

    public void fillSubject() {
        subjectField.sendKeys("Test");
    }

    public void fillEmailText(){
        emailText.sendKeys("Compose new email test successfully! Congrats!!!");
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div[1]/nav/div/div[3]/div[1]/ul/li[4]/div/a/span[1]")
    private WebElement draftsFoder;

    @FindBy(xpath = "//a[@data-test-id='message-list-item']")
    private WebElement openDraftEmail;

    @FindBy(xpath =
            "//*[@id=\"mail-app-component\"]/div[1]/div/div/div[1]/div[2]/div/div/div/div[2]/div/ul/li[1]/span/span/span/div/div")
    private WebElement reciever;

    @FindBy(xpath = "//*[@id=\"mail-app-component\"]/div[1]/div/div/div[1]/div[1]/div[1]")
    private WebElement subject;

    @FindBy(xpath = "//*[@id=\"editor-container\"]/div[1]/div")
    private WebElement getEmailText;
    public void openDraftsFolder() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    draftsFoder.click();
                    return true;
                });
    }

    @FindBy(xpath = "//div[@data-test-id='snippet']//div[@role='gridcell']")
    private WebElement textDraftFolder;

    public String verifyEmailIsInDraftFolder() {
        return textDraftFolder.getText();
    }

    public String validateReceiver() {
        return reciever.getText();
    }

    public void openDraftEmail() {
        openDraftEmail.click();
    }
    public String validateSubject() {
        return subject.getText();
    }

    public String  validateText() {return getEmailText.getText();}

    @FindBy(xpath = "//*[@id=\"mail-app-component\"]/div[1]/div/div/div[2]/div[2]/div/button")
    private WebElement sendButton;

    public void sendEmail() {
        sendButton.click();
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div[1]/nav/div/div[3]/div[1]/ul/li[5]/div/a/span")
    private WebElement openSentFolder;

    public void openSentFolder() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .ignoring(StaleElementReferenceException.class)
                .until((WebDriver d) -> {
                    openSentFolder.click();
                    return true;
                });
    }

    @FindBy(xpath =
            "//*[@id=\"mail-app-component\"]/div[1]/div/div/div[2]/div/div/div[4]/div/div[1]/ul/li[2]/a/div/div[3]/div[1]/div[2]/div")
    private WebElement textSentFolder;

    public String verifyEmailIsInSentFolder() {
        return textSentFolder.getText();
    }

    @FindBy(className = "_yb_1ikeu53")
    private WebElement userIcon;

    @FindBy(xpath = "//*[@id=\"profile-signout-link\"]/span[2]")
    private WebElement signOutButton;

    public void logOut(){
        userIcon.click();
        signOutButton.click();
    }
}
