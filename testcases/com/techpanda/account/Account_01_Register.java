package com.techpanda.account;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Account_01_Register {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");

    @BeforeClass
    public void beforeClass(){
        System.setProperty("webdriver.chrome.driver",projectPath + "\\browserDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://live.techpanda.org/");
    }

    @Test
    public void Register_01_Empty_Data(){

    }

    @Test
    public void Register_02_Invalid_Email(){

    }

    @Test
    public void Register_03_Invalid_Password(){

    }

    @AfterClass
    public void afterClass(){

    }
}
