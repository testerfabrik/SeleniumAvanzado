package com.testerfabrik.pf.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class RegisterPage {
    WebDriver driver;

    @FindBy(name="firstName")
    WebElement txtFirstName;

    @FindBy(name = "country")
    WebElement ddlCountry;

    @FindBy(id = "email")
    WebElement txtUserName;

    @FindBy(name = "password")
    WebElement txtPassword;

    @FindBy(name = "confirmPassword")
    WebElement txtConfPassword;

    @FindBy(xpath = "//input[@type='image' and @name='register']")
    WebElement btnSubmit;

    public RegisterPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void setFirstName(String strFirstName){
        txtFirstName.sendKeys(strFirstName);
    }

    public void selectCountry(String strCountry){
        new Select(ddlCountry).selectByVisibleText(strCountry);
    }

    public void setUserName(String strUserName){
        txtUserName.sendKeys(strUserName);
    }

    public void setPassword(String strPassword){
        txtPassword.sendKeys(strPassword);
    }

    public void setConfirmPassword(String strConfirm){
        txtConfPassword.sendKeys(strConfirm);
    }

    public void clickOnSubmitButton(){
        btnSubmit.click();
    }

    public void submitUserInformation(String strUser, String strPassword){
        this.setUserName(strUser);
        this.setPassword(strPassword);
        this.setConfirmPassword(strPassword);
        this.clickOnSubmitButton();
    }
}
