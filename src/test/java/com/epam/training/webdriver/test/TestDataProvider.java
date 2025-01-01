package com.epam.training.webdriver.test;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name = "validUserCredentials")
    public static Object[][] testData(){
        return new Object[][]{
                {"mazgalova3@yahoo.com", "sunnYdAY*"}
        };
    }

    @DataProvider(name = "emailFields1")
    public static Object[][] testData1() {
        return new Object[][]{
                {"mazgalova3@yahoo.com", "sunnYdAY*", "Compose new email test successfully! Congrats!!!"}
        };
    }

    @DataProvider(name = "emailFields")
    public static Object[][] testData2(){
        return new Object[][]{
                {"mazgalova3@yahoo.com", "sunnYdAY*","dinadinski@gmail.com", "Test",
                        "Compose new email test successfully! Congrats!!!"}
        };
    }

}
