package com.epam.training.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class Driver {

    public static WebDriver driver;

    public void getDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.get("https://login.yahoo.com");

        String parent=driver.getWindowHandle();

        Set<String> s=driver.getWindowHandles();

        Iterator<String> iterator= s.iterator();

        while(iterator.hasNext())
        {
            String child_window=iterator.next();
            if(!parent.equals(child_window))
            {
                driver.switchTo().window(child_window);

                System.out.println(driver.switchTo().window(child_window).getTitle());

                driver.close();
            }
        }
        //switch to the parent window
        driver.switchTo().window(parent);
    }
}
