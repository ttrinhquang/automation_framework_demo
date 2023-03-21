package nopCommerce.user;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import utilities.Environment;
import utilities.UserDataMapper;

public class Register extends BaseTest {

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private WebDriver driver;
	private String firstName, lastName, emailAddress, password, incorrectEmail, incorrectPassword;
	UserDataMapper userData;
	Environment env;

	@Parameters({ "browserName", "environment" })
	@BeforeClass
	public void beforeClass(String browserName, String environment) {

		ConfigFactory.setProperty("env", environment);
		env = ConfigFactory.create(Environment.class);

		driver = getBrowserDriver(browserName, env.appUrl());

		homePage = PageGeneratorManager.getUserHomePage(driver);
		userData = UserDataMapper.getUserData();

		firstName = userData.getFirstName();
		lastName = userData.getLastName();
		emailAddress = userData.getEmailAddress() + generateFakeNumber() + "@mail.com";
		password = userData.getPassword();
		incorrectEmail = userData.getIncorrectEmail();
		incorrectPassword = userData.getIncorrectPassword();

	}

	@Test
	public void Register_01_Empty_Data() {

		log.info("Register_01 - Step 01: Navigate to Register page.");
		registerPage = homePage.openRegisterPage();

		log.info("Register_01 - Step 02: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("Register_01 - Step 03: Verify Error message is displayed at First name textbox.");
		Assert.assertEquals(registerPage.getErrorMessageAtFirstnameTextbox(), "First name is required.");

		log.info("Register_01 - Step 04: Verify Error message is displayed at Last name textbox.");
		Assert.assertEquals(registerPage.getErrorMessageAtLastnameTextbox(), "Last name is required.");

		log.info("Register_01 - Step 05: Verify Error message is displayed at Email textbox.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Email is required.");

		log.info("Register_01 - Step 06: Verify Error message is displayed at Password textbox.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password is required.");

		log.info("Register_01 - Step 07: Verify Error message is displayed at Confirm password textbox.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "Password is required.");

	}

	@Test
	public void Register_02_Invalid_Email() {

		log.info("Register_02 - Step 01: Navigate to Register page.");
		registerPage = homePage.openRegisterPage();

		log.info("Register_02 - Step 02: Input to First name textbox with value: '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_02 - Step 03 Input to Last name textbox with value: '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_02 - Step 04: Input to Email textbox with invalid value: '" + incorrectEmail + "'");
		registerPage.inputToEmailTextbox(incorrectEmail);

		log.info("Register_02 - Step 05: Input to Password textbox with value: '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_02 - Step 06: Input to Confirm password textbox with value: '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register_02 - Step 07: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("Register_02 - Step 08: Verify Error message is displayed.");
		Assert.assertEquals(registerPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Register_03_Registers_Successfully() {
		log.info("Register_03 - Step 01: Navigate to Register page.");
		registerPage = homePage.openRegisterPage();

		log.info("Register_03 - Step 02: Input to First name textbox with value: '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_03 - Step 03: Input to Last name textbox with value: '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_03 - Step 04: Input to Email address textbox with value: '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_03 - Step 05: Input to Password textbox with value: '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_03 - Step 06: Input to Confirm password textbox with value: '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register_03 - Step 07: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("Register_03 - Step 08: Verify Success message is displayed.");
		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Register_03 - Step 09: Click to Logout link.");
		homePage = registerPage.clickToLogoutLink();
	}

	@Test
	public void Register_04_Existing_Email() {

		log.info("Register_04 - Step 01: Navigate to Register page.");
		registerPage = homePage.openRegisterPage();

		log.info("Register_04 - Step 02: Input to First name textbox with value: '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_04 - Step 03: Input to Last name textbox with value: '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_04 - Step 04: Input to Email name textbox with value: '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_04 - Step 05: Input to Password textbox with value: '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_04 - Step 06: Input to Confirm password textbox with value: '" + password + "'");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Register_04 - Step 07: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("Register_04 - Step 08: Verify Error message is displayed.");
		Assert.assertEquals(registerPage.getErrorExistingEmailMessage(), "The specified email already exists");

	}

	@Test
	public void Register_05_Password_Less_Than_6_Chars() {

		log.info("Register_05 - Step 01: Navigate to Register page.");
		registerPage = homePage.openRegisterPage();

		log.info("Register_05 - Step 02: Input to First name textbox with value: '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_05 - Step 03: Input to Last name textbox with value: '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_05 - Step 04: Input to Email textbox with value: '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_05 - Step 05: Input to Password textbox with value: '" + incorrectPassword + "'");
		registerPage.inputToPasswordTextbox(incorrectPassword);

		log.info("Register_05 - Step 06: Input to Confirm password textbox with value: '" + incorrectPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(incorrectPassword);

		log.info("Register_05 - Step 07: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("Register_05 - Step 08: Verify Error message is displayed.");
		Assert.assertEquals(registerPage.getErrorMessageAtPasswordTextbox(), "Password must meet the following rules:\nmust have at least 6 characters");

	}

	@Test
	public void Register_06_Invalid_Confirm_Password() {
		log.info("Register_05 - Step 01: Navigate to Register page.");
		homePage.openRegisterPage();
		registerPage = new UserRegisterPageObject(driver);

		log.info("Register_05 - Step 02: Input to First name textbox with value: '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Register_05 - Step 03: Input to First name textbox with value: '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Register_05 - Step 04: Input to First name textbox with value: '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Register_05 - Step 05: Input to First name textbox with value: '" + password + "'");
		registerPage.inputToPasswordTextbox(password);

		log.info("Register_05 - Step 06: Input to First name textbox with value: '" + incorrectPassword + "'");
		registerPage.inputToConfirmPasswordTextbox(incorrectPassword);

		log.info("Register_05 - Step 07: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("Register_05 - Step 08: Verify Error message is displayed.");
		Assert.assertEquals(registerPage.getErrorMessageAtConfirmPasswordTextbox(), "The password and confirmation password do not match.");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();

	}

}
