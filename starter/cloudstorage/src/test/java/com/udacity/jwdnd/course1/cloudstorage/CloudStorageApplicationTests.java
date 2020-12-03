package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	public String baseURL;

	String firstName = "Ken";
	String lastName = "Edwards";
	String username = "rpcvjet";
	String password = "MyPassword!";

	private static final String click = "arguments[0].click();";


	private static WebDriver driver;
	private static WebDriverWait wait;

	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@BeforeEach
	public void beforeEach() {
		baseURL = baseURL = "http://localhost:" + port;
		wait = new WebDriverWait(driver, 30);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	private void signupLib(String fn, String ln, String un, String pw){
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(fn, ln, un, pw);
	}

	private void loginLib(String un,String pw) {
		driver.get(baseURL + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(un, pw);
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	//Write a test that verifies that an unauthorized user can only access the login and signup pages.
	@Test
	public void notLoggedIn(){
		driver.get(baseURL + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
		Assertions.assertNotEquals("Home", driver.getTitle());
	}

	@Test
	public void testUserSignupLogin() {
		//new user signs up
		driver.get(baseURL + "/signup");
		Assertions.assertEquals("Sign Up", driver.getTitle());
		SignupPage signupPage = new SignupPage(driver);
		signupPage.signup(firstName, lastName, username, password);

		//user logs in
		driver.get(baseURL + "/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginUser(username, password);

		//verifies that the home page is accessible
		Assertions.assertEquals("Home", driver.getTitle());
	}

//		Notes TEST
	@Test
	public void NotesPageTest() {
		signupLib(firstName,lastName, username,password);
		System.out.println(" SIGNED UP ");
		loginLib(username, password);
		System.out.println(" LOGGED IN ");

		NotesPage notesPage = new NotesPage(driver);

		((JavascriptExecutor) driver).executeScript(click, notesPage.getNavNotesTab());
		System.out.println("ADDING A NOTE...");

		//Add Note
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addNotesButton"))).click();
		System.out.println("NOTES TAB HAS BEEN CLICKED...");
		((JavascriptExecutor) driver).executeScript(click, notesPage.getAddNoteBtn());
		System.out.println("ADD NOTES BUTTON HAS BEEN CLICKED...");

		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getNoteSubmitBtn()));
		notesPage.populateNote("This is a note", "blah blah blah");
		((JavascriptExecutor) driver).executeScript(click, notesPage.getNoteSubmitBtn());

		//check to see if the note was created correctly
		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getNavNotesTab()));
		((JavascriptExecutor) driver).executeScript(click, notesPage.getNavNotesTab());
		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getAddNoteBtn()));

		Assertions.assertEquals("This is a note", notesPage.getNoteTitle().getText());
		Assertions.assertEquals("blah blah blah", notesPage.getNoteDescription().getText());

		//edit note
		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getEditNoteBtn()));
		((JavascriptExecutor) driver).executeScript(click, notesPage.getEditNoteBtn());


		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getNoteSubmitBtn()));
		Assertions.assertEquals("This is a note",notesPage.getInputTitle().getAttribute("value"));
		Assertions.assertEquals("blah blah blah",notesPage.getInputDescription().getAttribute("value"));
		notesPage.populateNote("Editing the Title", "Editing the Description");
		((JavascriptExecutor) driver).executeScript(click, notesPage.getNoteSubmitBtn());

		//check to see if the note was edited correctly
		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getNavNotesTab()));
		((JavascriptExecutor) driver).executeScript(click, notesPage.getNavNotesTab());
		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getAddNoteBtn()));

		Assertions.assertEquals("Editing the Title", notesPage.getNoteTitle().getText());
		Assertions.assertEquals("Editing the Description", notesPage.getNoteDescription().getText());


		//Delete note
		wait.until(ExpectedConditions.elementToBeClickable(notesPage.getDeleteNoteBtn()));
		((JavascriptExecutor) driver).executeScript(click, notesPage.getDeleteNoteBtn());

		//logout
		((JavascriptExecutor) driver).executeScript(click, notesPage.getLogoutBtn());
	}

	@Test
	public void CredentialsPageTest(){
		signupLib(firstName,lastName, username,password);
		System.out.println(" SIGNED UP ");
		loginLib(username, password);
		System.out.println(" LOGGED IN ");

		CredentialsPage credentialsPage = new CredentialsPage(driver);

		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getNavCredentialsTab());
		System.out.println("ABOUT TO ADD A CRED...");

		//Add a Credential
		wait.until(ExpectedConditions.elementToBeClickable(By.id("addCredentialsBtn"))).click();
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getAddCredentialsBtn());
		System.out.println("CREDENTIALS TAB HAS BEEN CLICKED...");
		//enter data
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getAddCredentialsBtn());
		System.out.println("ADD CREDENTIALS BUTTON HAS BEEN CLICKED...");

		//wait for the submit button to render
		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getCredentialSubmitBtn()));
		credentialsPage.populateCredentials("someurl.com", "myusername", "mypassword");
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getCredentialSubmitBtn());


		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getNavCredentialsTab()));
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getNavCredentialsTab());
		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getAddCredentialsBtn()));
		System.out.println("WE HERE!!");

		Assertions.assertEquals("someurl.com", credentialsPage.getCredentialUrl().getText());
		Assertions.assertEquals("myusername", credentialsPage.getCredentialUsername().getText());
		Assertions.assertNotEquals("mypassword",credentialsPage.getCredentialPassword().getText());

		//edit
		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getEditCredentialBtn()));
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getEditCredentialBtn());

		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getCredentialSubmitBtn()));
		wait.until(ExpectedConditions.visibilityOf(credentialsPage.getInputURL()));
		Assertions.assertEquals("someurl.com", credentialsPage.getInputURL().getAttribute("value"));
		Assertions.assertEquals("myusername", credentialsPage.getInputUsername().getAttribute("value"));
		Assertions.assertEquals("mypassword", credentialsPage.getInputPassword().getAttribute("value"));
		credentialsPage.populateCredentials("aneditedURL.com", "differentusername", "editedPassword");
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getCredentialSubmitBtn());

		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getNavCredentialsTab()));
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getNavCredentialsTab());
		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getAddCredentialsBtn()));

		Assertions.assertEquals("aneditedURL.com", credentialsPage.getCredentialUrl().getText());
		Assertions.assertEquals("differentusername", credentialsPage.getCredentialUsername().getText());
		Assertions.assertNotEquals("editedPassword",credentialsPage.getCredentialPassword().getText());

//		//Delete
		wait.until(ExpectedConditions.elementToBeClickable(credentialsPage.getDeleteCredentialBtn()));
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getDeleteCredentialBtn());

		//logout
		((JavascriptExecutor) driver).executeScript(click, credentialsPage.getLogoutBtn());
	}

}
