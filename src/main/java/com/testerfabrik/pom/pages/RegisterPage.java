package com.testerfabrik.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    By txtFirstName = By.name("firstName");
    By ddlCountry = By.name("country");
    By txtUserName = By.id("email");
    By txtPassword = By.name("password");
    By txtConfPassword = By.name("confirmPassword");
    By btnSubmit = By.xpath("//input[@type='image' and @name='register']");

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public void setFirstName(String strFirstName){
        driver.findElement(txtFirstName).sendKeys(strFirstName);
    }

    public void selectCountry(String strCountry){
        new Select(driver.findElement(ddlCountry)).selectByVisibleText(strCountry);
    }

    public void setUserName(String strUserName){
        driver.findElement(txtUserName).sendKeys(strUserName);
    }

    public void setPassword(String strPassword){
        driver.findElement(txtPassword).sendKeys(strPassword);
    }

    public void setConfirmPassword(String strConfirm){
        driver.findElement(txtConfPassword).sendKeys(strConfirm);
    }

    public void clickOnSubmitButton(){
        driver.findElement(btnSubmit).click();
    }

    public void submitUserInformation(String strUser, String strPassword){
        this.setUserName(strUser);
        this.setPassword(strPassword);
        this.setConfirmPassword(strPassword);
        this.clickOnSubmitButton();
    }
}
