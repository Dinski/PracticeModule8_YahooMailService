package com.epam.training.webdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver {

    protected WebDriver driver;
    public Logger logger = Logger.getLogger(Driver.class.getName());

    @BeforeClass
    public  void setUp() {
        try {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.get("https://login.yahoo.com");
            logger.info("WebDriver initialized and navigated to Yahoo login.");
        } catch  (Exception e) {
            logger.log(Level.SEVERE, "Error during WebDriver initialization", e);
            throw e;
        }
    }

    @AfterClass
    public void tearDown() {
        try {
            if (driver != null) {
                driver.quit();
                logger.info("WebDriver closed successfully.");
            }
        } catch (Exception e) {
                logger.log(Level.SEVERE, "Error during WebDriver initialization", e);
        }
    }

    public WebDriver getDriver() {
        return driver;
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