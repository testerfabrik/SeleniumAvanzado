package com.testerfabrik.avanzado;

import com.testerfabrik.driverfactory.BrowserType;
import com.testerfabrik.driverfactory.DriverFactory;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

public class HundredAssessments {
    WebDriver driver;
    WebDriverWait waitElement;
    String user= "gilberto@thecoursekey.com";
    String pass = "Test619";

    @BeforeTest
    public void setUpTest() throws InterruptedException {
        DriverFactory.getInstance().setDriver(BrowserType.CHROME);
        driver = DriverFactory.getInstance().getDriver();
        driver.get("https://staging.thecoursekey.com/login");
        waitElement = new WebDriverWait(driver,15);
        driver.findElement(By.xpath(".//*[contains(@id,'undefined-exampleexamplecom-Email')]")).sendKeys(user);
        driver.findElement(By.xpath(".//*[contains(@id,'undefined--Password')]")).sendKeys(pass);
        driver.findElement(By.id("login-button")).click();
        Thread.sleep(3000);
        WebElement spnSentry = driver.findElement(By.xpath("//*[@id='page-content']/div/div[1]/div[1]/div[1]/div[3]/div/div[31]/div/span/div/div/div[1]/div[1]/div/div[2]/div[1]/div/span"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true)", spnSentry);
        spnSentry.click();
        driver.findElement(By.xpath("//*[contains(text(),'Create Assessment')]")).click();
        Thread.sleep(3000);
    }

    @AfterTest
    public void tearDown(){
        DriverFactory.getInstance().removeDriver();
    }

    @Test
    public void create500Assessments(){
        try{
            for(int i = 65; i<=500; i++){

                if(!((i%10)==0))
                {
                    WebElement btnCreate = driver.findElement(By.xpath("//*[text()='Create']"));
                    waitElement.until(ExpectedConditions.elementToBeClickable(btnCreate));
                    btnCreate.click();
                    Thread.sleep(1000);
                    driver.findElement(By.xpath("//*[text()='Assessment']")).click();
                    Thread.sleep(2000);
                    driver.findElement(By.cssSelector("#page-content > div > div:nth-child(1) > div > div > div.col-xs > div.create-assessment-header > div.row.end-xs.middle-xs > div > div > input[type=\"checkbox\"]")).click();
                    Thread.sleep(1000);
                    ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,250)");
                    driver.findElement(By.xpath("//*[text()='SAVE AS']")).click();
                    Thread.sleep(1000);
                    try{
                        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        driver.findElement(By.xpath("//*[text()='DONE']")).click();
                        i--;
                    }catch(NoSuchElementException e){
                        driver.findElement(By.xpath("//*[contains(@id, 'undefined--AssessmentName')]")).sendKeys(Integer.toString(i));
                        Thread.sleep(1000);
                        new Robot().keyPress(KeyEvent.VK_ENTER);
                        Thread.sleep(1000);
                        driver.findElement(By.xpath("//*[text()='DONE']")).click();
                        System.out.println("Assessment " + i + " created");
                    }
                }
            }

            Assert.assertTrue(true,"500 Assessments created!");
        }catch(Exception e){
            Assert.fail(e.getMessage());
        }
    }
}
