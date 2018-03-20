package com.testerfabrik.pf.tests;

import com.testerfabrik.pom.pages.HomePage;
import com.testerfabrik.pom.pages.RegisterPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class RegisterUserTest {
    WebDriver driver;
    String expectedResult = null;
    String actualResult = null;
    HomePage objHome;
    RegisterPage objRegister;

    @BeforeTest
    public void setUp(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("http://newtours.demoaut.com/");
    }

    @AfterTest
    public void tearDown(){
        driver.close();
    }

    @Test(priority = 0)
    public void goToRegisterPage(){
        objHome = new HomePage(driver);
        objHome.clickRegisterLink();

        expectedResult = "Register: Mercury Tours";
        actualResult = objHome.getHomePageTitle();
        Assert.assertEquals(actualResult,expectedResult);
    }

    @Test(priority = 1)
    public void registerAnUser(){
        objRegister = new RegisterPage(driver);

        objRegister.setFirstName("Gilberto");
        objRegister.selectCountry("AUSTRIA");

        objRegister.submitUserInformation("gilberto@mail.com", "123");

        String textSuccess = driver.findElement(By.xpath("html/body/div[1]/table/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr/td[2]/table/tbody/tr[3]/td/p[3]/a/font/b")).getText();
        System.out.println("Test passed! : " + textSuccess);
    }
}
