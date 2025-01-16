package com.epam.training.webdriver;

import org.apache.logging.log4j.LogManager;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BasePage extends Driver{
    private WebDriver driver;
    protected WebDriverWait wait;
    protected Logger logger;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        this.logger =LogManager.getLogger(getClass());
    }

    /**
     * Wait for a WebElement to be visible.
     * @param element WebElement to wait for.
     * @return The visible WebElement.
     */
    protected WebElement waitForElementToBeVisible(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException e) {
            logger.error("Element not visible: " + element, e);
            throw e;
        }
    }
    /**
     * Wait for a WebElement to be clickable.
     * @param element WebElement to wait for.
     */
    protected void waitForElementToBeClickable(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException e) {
            logger.error("Element not clickable: " + element, e);
            throw e;
        }
    }

    public void switchToNewWindow(String originalWindow) {
        try {
            Set<String> allWindows = driver.getWindowHandles();
            for (String window : allWindows) {
                if (!window.equals(originalWindow)) {
                    driver.switchTo().window(window);
                    logger.info("Switched to a new window: " + driver.getTitle());
                    return;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        throw new RuntimeException("No new window found to switch to.");
    }
}
