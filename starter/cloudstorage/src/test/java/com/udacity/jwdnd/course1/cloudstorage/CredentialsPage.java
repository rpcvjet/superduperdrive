package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsPage {

    @FindBy(id="nav-credentials-tab")
    private WebElement navCredentialsTab;

    @FindBy(id="addCredentialsBtn")
    private WebElement addCredentialsBtn;

    @FindBy(id="credential-url")
    private WebElement inputURL;

    @FindBy(id="credential-username")
    private WebElement inputUsername;

    @FindBy(id="credential-password")
    private WebElement inputPassword;

    @FindBy(id="credentialSubmit")
    private WebElement credentialSubmitBtn;

    @FindBy(id="credentialUrl")
    private WebElement credentialUrl;

    @FindBy(id="credentialUsername")
    private WebElement credentialUsername;

    @FindBy(id="credentialPassword")
    private WebElement credentialPassword;

    @FindBy(id="edit-credential-button")
    private WebElement editCredentialBtn;

    @FindBy(id="delete-credential-button")
    private WebElement deleteCredentialBtn;

    @FindBy(id="logoutButton")
    private WebElement logoutBtn;

    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public void logout(){
        this.logoutBtn.click();
    }

    public WebElement getNavCredentialsTab() {
        return navCredentialsTab;
    }

    public WebElement getAddCredentialsBtn() {
        return addCredentialsBtn;
    }

    public WebElement getInputURL() {
        return inputURL;
    }

    public WebElement getInputUsername() {
        return inputUsername;
    }

    public WebElement getInputPassword() {
        return inputPassword;
    }

    public WebElement getCredentialSubmitBtn() {
        return credentialSubmitBtn;
    }

    public CredentialsPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void populateCredentials(String url, String username, String password){
        System.out.println("TYPING FUNCTION");
        inputURL.clear();
        inputUsername.clear();
        inputPassword.clear();
        inputURL.sendKeys(url);
        inputUsername.sendKeys(username);
        inputPassword.sendKeys(password);
    }

    public WebElement getCredentialUrl() {
        return credentialUrl;
    }

    public WebElement getCredentialUsername() {
        return credentialUsername;
    }

    public WebElement getCredentialPassword() {
        return credentialPassword;
    }

    public WebElement getEditCredentialBtn() {
        return editCredentialBtn;
    }

    public WebElement getDeleteCredentialBtn() {
        return deleteCredentialBtn;
    }
}
