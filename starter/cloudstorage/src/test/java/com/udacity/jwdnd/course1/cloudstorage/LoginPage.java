package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement usernameInputField;

    @FindBy(id = "inputPassword")
    private WebElement passwordInputField;

    @FindBy(id = "submitbutton")
    private WebElement submitbutton;

    @FindBy(css="#error-msg")
    private WebElement errorMsg;

    public LoginPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }
    public void loginUser(String username, String password){
        this.usernameInputField.sendKeys(username);
        this.passwordInputField.sendKeys(password);
        this.submitbutton.click();
    }

    public WebElement getErrorMsg() {
        return errorMsg;
    }
}
