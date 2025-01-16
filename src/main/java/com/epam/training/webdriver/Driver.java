package com.epam.training.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import util.PropertiesUtil;

import java.time.Duration;
import java.util.Properties;

public class Driver {

    protected WebDriver driver;

    private static final ThreadLocal<WebDriver> threadLocal = new ThreadLocal<>();
    private static Properties properties = new Properties();

    public Logger logger = LogManager.getLogger(Driver.class.getName());

    @BeforeClass
    public void setUp() {
        String browser = PropertiesUtil.getProperty("browser");
        int implicitWait = Integer.parseInt(PropertiesUtil.getProperty("implicit.wait"));
        String baseUrl = PropertiesUtil.getProperty("base.url");

        try {
            switch (browser.toLowerCase()) {
                case "chrome" -> {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
                case "edge" -> {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                case "firefox" -> {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                default -> throw new IllegalAccessException("Unsupported browser: " + browser);
            }
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitWait));
            driver.get(baseUrl);
            logger.info("WebDriver initialized for " + browser + " and navigated to: " + baseUrl);

            threadLocal.set(driver);
        } catch  (Exception e) {
            logger.error("Error during WebDriver initialization");
            try {
                throw e;
            } catch (IllegalAccessException ex) {
                throw new RuntimeException(ex);
            }
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
            logger.error("Error during WebDriver initialization", e);
        }
    }

    /**
     * Provides the WebDriver instance.
     * @return The WebDriver instance.
     */
    public static WebDriver getDriver() {
        return threadLocal.get();
    }

//    public WebElement waitForElementToBeVisible(WebElement element) {
//        return wait.until(ExpectedConditions.visibilityOf(element));
//    }
//
//    public void waitForElementToBeClickable(WebElement element) {
//        wait.until(ExpectedConditions.elementToBeClickable(element));
//    }
//
//    public void switchToNewWindow(String originalWindow) {
//        try {
//            Set<String> allWindows = driver.getWindowHandles();
//            for (String window : allWindows) {
//                if (!window.equals(originalWindow)) {
//                    driver.switchTo().window(window);
//                    logger.info("Switched to a new window: " + driver.getTitle());
//                    return;
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        throw new RuntimeException("No new window found to switch to.");
//    }
}