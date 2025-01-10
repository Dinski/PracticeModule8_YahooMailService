package com.epam.training.webdriver;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmailPage extends Driver {

    private static final Logger logger = Logger.getLogger(EmailPage.class.getName());
    public WebDriver driver;

    public EmailPage(WebDriver driver) {
        this.driver = driver;
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
        String title = driver.getTitle();
        logger.info("Validating login. Page title: " + title);
        return title;
    }

    public WebElement openComposeFolder() {
        try {
            logger.info("Opening compose folder...");
            waitForElementToBeClickable(composeLink);
            composeLink.click();
            logger.info("Compose folder opened successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to open compose folder.", e);
            throw e;
        }
    return composeLink;
    }

    public void fillReceiver(String email) {
        try {
            logger.info("Filling receiver: " + email);
            addressee.sendKeys(email);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fill receiver field.", e);
            throw e;
        }
    }

    public void fillSubject(String subjectText) {
        try {
            logger.info("Filling subject: " + subjectText);
            subjectField.sendKeys(subjectText);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fill subject field.", e);
            throw e;
        }
    }

    public void fillEmailText(String bodyText) {
        try {
            logger.info("Filling email text.");
            emailText.sendKeys(bodyText);
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to fill email text field.", e);
            throw e;
        }
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
        try {
            logger.info("Opening drafts folder...");
            waitForElementToBeClickable(draftsFoder);
            draftsFoder.click();
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to open drafts folder.", e);
            throw e;
        }
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
        try {
            logger.info("Opening draft email...");
            openDraftEmail.click();
            logger.info("Draft email opened successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to open draft email.", e);
            throw e;
        }
    }

    public String validateSubject() {
        return subject.getText();
    }

    public String  validateText() {return getEmailText.getText();}

    @FindBy(xpath = "//*[@id=\"mail-app-component\"]/div[1]/div/div/div[2]/div[2]/div/button")
    private WebElement sendButton;

    public void sendEmail() {
        try {
            logger.info("Sending email...");
            waitForElementToBeClickable(sendButton);
            sendButton.click();
            logger.info("Email sent successfully.");
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Failed to send email.", e);
            throw e;
        }
    }

    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div[1]/nav/div/div[3]/div[1]/ul/li[5]/div/a/span")
    private WebElement openSentFolder;

    public void openSentFolder() {
        try {
            logger.info("Opening sent folder...");

            waitForElementToBeClickable(openSentFolder);
            openSentFolder.click();

            logger.info("Sent folder opened successfully.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to open sent folder.", e);
            throw e;
        }
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

    public void logOut() {
        try {
            logger.info("Logging out...");
            waitForElementToBeVisible(userIcon);
            userIcon.click();
            waitForElementToBeClickable(signOutButton);
            signOutButton.click();
            logger.info("Logged out successfully.");
        } catch (Exception e) {
            logger.log(Level.WARNING, "Failed to log out.", e);
            throw e;
        }
    }
}
