package com.testerfabrik.driverfactory;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AmazonTest {
    WebDriver driver;
    WebDriverWait waitElement;

    @BeforeTest
    public void setUpTest(){
        DriverFactory.getInstance().setDriver(BrowserType.CHROME);
        driver = DriverFactory.getInstance().getDriver();
        driver.get("https://www.amazon.com");
        waitElement = new WebDriverWait(driver,15);
    }

    @AfterTest
    public void tearDown(){
        DriverFactory.getInstance().removeDriver();
    }

    @Test(priority = 0)
    public void searchPlasticCaseIpadAir(){
        WebElement txtSearch = driver.findElement(By.id("twotabsearchtextbox"));
        waitElement.until(ExpectedConditions.visibilityOf(txtSearch));
        txtSearch.sendKeys("ipad air 2 case");

        driver.findElement(By.xpath("//input[@value='Go']")).click();

        WebElement chkPlastic = driver.findElement(By.xpath("//*[text()='Plastic']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", chkPlastic);
        chkPlastic.click();
    }

    @Test(priority = 1)
    public void searchFirstFiveItems(){
        for(int i = 0; i < 5; i++){
            String name = driver.findElement(By.xpath("//*[@id='result_" + i + "']/div/div[3]/div[1]//h2")).getText();

            String score = driver.findElement(By.xpath("//*[@id='result_" + i + "']/div/div/span/span/a/i/span")).getAttribute("innerText");
            System.out.println("Name: " + name + ", Score: " + score);
        }
    }
}
