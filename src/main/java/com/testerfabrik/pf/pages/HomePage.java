package com.testerfabrik.pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    WebDriver driver;

    @FindBy(linkText = "REGISTER")
    WebElement lnkRegister;

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void clickRegisterLink(){
        lnkRegister.click();
    }

    public String getHomePageTitle(){
        return driver.getTitle();
    }
}
