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
import nopCommerce.common.Register_New_User;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserLoginPageObject;
import utilities.Environment;
import utilities.UserDataMapper;

public class Login extends BaseTest {

	UserDataMapper userData;
	private UserHomePageObject homePage;
	private UserLoginPageObject loginPage;
	private WebDriver driver;
	private String emailAddress, incorrectEmail, notFoundEmail, password, incorrectPassword;
	Environment env;

	@Parameters({ "browserName", "environment" })
	@BeforeClass()
	public void beforeClass(String browserName, String environment) {

		ConfigFactory.setProperty("env", environment);
		env = ConfigFactory.create(Environment.class);
		driver = getBrowserDriver(browserName, env.appUrl());

		homePage = PageGeneratorManager.getUserHomePage(driver);

		userData = UserDataMapper.getUserData();
		emailAddress = Register_New_User.emailAddress;
		password = Register_New_User.password;
		incorrectEmail = userData.getIncorrectEmail();
		notFoundEmail = userData.getNotFoundEmail();
		incorrectPassword = userData.getIncorrectPassword();

	}

	@Test
	public void Login_01_Empty_Data() {

		log.info("Login_01 - Step 01: Navigate to Login page.");
		loginPage = homePage.openLoginPage();

		log.info("Login_01 - Step 02: Click to Login button.");
		loginPage.clickToLoginButton();

		log.info("Login_01 - Step 03: Verify Error message is displayed.");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Please enter your email");
	}

	@Test
	public void Login_02_Invalid_Email() {

		log.info("Login_02 - Step 01: Navigate to Login page.");
		loginPage = homePage.openLoginPage();

		log.info("Login_02 - Step 02: Input to Email textbox with invalid email: ' " + incorrectEmail + "'");
		loginPage.inputToEmailTextbox(incorrectEmail);

		log.info("Login_03 - Step 03: Click to Login button.");
		loginPage.clickToLoginButton();

		log.info("Login_04 - Step 04: Verify Error message is displayed.");
		Assert.assertEquals(loginPage.getErrorMessageAtEmailTextbox(), "Wrong email");

	}

	@Test
	public void Login_03_Email_Not_Found() {

		log.info("Login_03 - Step 01: Navigate to Login page.");
		loginPage = homePage.openLoginPage();

		log.info("Login_03 - Step 02: Input to Email textbox with invalid email: '" + notFoundEmail + "'");
		loginPage.inputToEmailTextbox(notFoundEmail);

		log.info("Login_03 - Step 03: Click to Login button.");
		loginPage.clickToLoginButton();

		log.info("Login_03 - Step 04: Verify Error message is displayed.");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nNo customer account found");

	}

	@Test
	public void Login_04_Existing_Email_Empty_Password() {

		log.info("Login_04 - Step 01: Navigate to Login page.");
		loginPage = homePage.openLoginPage();

		log.info("Login_04 - Step 02: Input to Email textbox with exsiting email: '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		log.info("Login_04 - Step 03: Input to Password textbox with empty.");
		loginPage.inputToPasswordTextbox("");

		log.info("Login_04 - Step 04: Click to Login button.");
		loginPage.clickToLoginButton();

		log.info("Login_04 - Step 05: Verify Error message is displayed.");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_05_Existing_Email_Incorrect_Password() {

		log.info("Login_05 - Step 01: Navigate to Login page.");
		loginPage = homePage.openLoginPage();

		log.info("Login_05 - Step 02: Input to Email textbox with exsiting email: '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		log.info("Login_05 - Step 03: Input to Password textbox with incorrect password: '" + incorrectPassword + "'");
		loginPage.inputToPasswordTextbox(incorrectPassword);

		log.info("Login_05 - Step 04: Click to Login button.");
		loginPage.clickToLoginButton();

		log.info("Login_05 - Step 05: Verify Error message is displayed.");
		Assert.assertEquals(loginPage.getErrorMessageUnsuccessfull(), "Login was unsuccessful. Please correct the errors and try again.\nThe credentials provided are incorrect");

	}

	@Test
	public void Login_06_Valid_Email_Valid_Password() {

		log.info("Login_06 - Step 01: Navigate to Login page.");
		loginPage = homePage.openLoginPage();

		log.info("Login_06 - Step 02: Input to Email textbox with exsiting email: '" + emailAddress + "'");
		loginPage.inputToEmailTextbox(emailAddress);

		log.info("Login_06 - Step 03: Input to Password textbox with valid password: '" + password + "'");
		loginPage.inputToPasswordTextbox(password);

		log.info("Login_06 - Step 04: Click to Login button.");
		homePage = loginPage.clickToLoginButton();

		log.info("Login_06 - Step 05: Verify My Account Link is displayed.");
		Assert.assertTrue(homePage.isMyAccountLinkDisplayed());
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		closeBrowserDriver();

	}

}
