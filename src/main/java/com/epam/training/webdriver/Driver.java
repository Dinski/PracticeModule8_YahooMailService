package com.epam.training.webdriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver {

    protected WebDriver driver;
    public Logger logger = Logger.getLogger(Driver.class.getName());
    public WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/main/resources/config.properties")) {
            properties.load(fis);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load configuration properties.", e);
        }
    }

    public static String getDecryptedValue(String key) {
        try {
            String encryptedValue = properties.getProperty(key);
            return EncryptionUtil.decrypt(encryptedValue);
        } catch (Exception e) {
            throw new RuntimeException("Failed to decrypt property: " + key, e);
        }
    }
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


    public WebElement waitForElementToBeVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
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