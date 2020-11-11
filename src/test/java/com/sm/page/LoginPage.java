package com.sm.page;

import org.apache.log4j.Logger;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import sun.rmi.runtime.Log;

public class LoginPage extends PageInit {


    private Logger logger = Logger.getLogger(LoginPage.this.getClass().getName());

    @FindBy(xpath="//input[@name='loginfmt']")
    private WebElement eleUsername;

    @FindBy(id="idSIButton9")
    private WebElement eleNext;

    @FindBy(id="passwordInput")
    private WebElement elePassword;

    @FindBy(id="submitButton")
    private WebElement eleSignIn;

    @FindBy(id="cookieButton")
    private WebElement eleAcceptCloseBtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void load(String env){
        wedDriver.get(env);

        //Check login page displays correct
        waitForElementToBeClickable(this.eleNext);
    }

    public void login(String userName, String password){
        eleUsername.sendKeys(userName);
        eleNext.click();

        waitForElementToBeClickable(this.eleSignIn);
        elePassword.sendKeys(password);
        try{
            eleSignIn.click();
        }catch (ElementClickInterceptedException e){
            if(this.eleAcceptCloseBtn.isDisplayed()){
                this.eleAcceptCloseBtn.click();
                eleSignIn.click();
            }
        }

    }
}
