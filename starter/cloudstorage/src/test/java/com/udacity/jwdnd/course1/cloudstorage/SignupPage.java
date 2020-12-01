package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameInputField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameInputField;

    @FindBy(id = "inputUsername")
    private WebElement usernameInputField;

    @FindBy(id = "inputPassword")
    private WebElement passwordInputField;

    @FindBy(id = "submitbutton")
    private WebElement submitButton;

    public SignupPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstname, String lastname, String username, String password) {
        this.firstNameInputField.sendKeys(firstname);
        this.lastNameInputField.sendKeys(lastname);
        this.usernameInputField.sendKeys(username);
        this.passwordInputField.sendKeys(password);
        this.submitButton.click();
    }

}
