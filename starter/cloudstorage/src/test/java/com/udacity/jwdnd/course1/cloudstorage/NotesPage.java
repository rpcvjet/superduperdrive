package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesPage {

    @FindBy(id="nav-notes-tab")
    private WebElement navNotesTab;

    @FindBy(id="addNotesButton")
    private WebElement addNoteBtn;

    @FindBy(id="note-title")
    private WebElement inputTitle;

    @FindBy(id="note-description")
    private WebElement inputDescription;

    @FindBy(id="saveChangesButton")
    private WebElement noteSubmitBtn;

    @FindBy(id="noteTitle")
    private WebElement noteTitle;

    @FindBy(id="noteDescription")
    private WebElement noteDescription;

    @FindBy(id="editNoteButton")
    private WebElement editNoteBtn;

    @FindBy(id="deleteNoteButton")
    private WebElement deleteNoteBtn;

    @FindBy(id="logoutButton")
    private WebElement logoutBtn;

    public WebElement getLogoutBtn() {
        return logoutBtn;
    }

    public void logout(){
        this.logoutBtn.click();
    }

    public WebElement getDeleteNoteBtn() {
        return deleteNoteBtn;
    }

    public WebElement getEditNoteBtn() {
        return editNoteBtn;
    }

    public WebElement getInputTitle() {
        return inputTitle;
    }

    public WebElement getInputDescription() {
        return inputDescription;
    }

    public NotesPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getNavNotesTab() {
        return navNotesTab;
    }

    public WebElement getAddNoteBtn() {
        return addNoteBtn;
    }

    public void populateNote(String title, String description){
        inputTitle.clear();
        inputDescription.clear();
        inputTitle.sendKeys(title);
        inputDescription.sendKeys(description);

    }

    public WebElement getNoteTitle() {
        return noteTitle;
    }

    public WebElement getNoteDescription() {
        return noteDescription;
    }

    public void addNoteClick(){
        this.addNoteBtn.click();
    }

    public WebElement getNoteSubmitBtn() {
        return noteSubmitBtn;
    }
}