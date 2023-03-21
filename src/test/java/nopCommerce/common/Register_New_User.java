package nopCommerce.common;

import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commons.BaseTest;
import commons.PageGeneratorManager;
import pageObject.nopCommerce.user.UserHomePageObject;
import pageObject.nopCommerce.user.UserRegisterPageObject;
import utilities.Environment;
import utilities.UserDataMapper;

public class Register_New_User extends BaseTest {

	private UserHomePageObject homePage;
	private UserRegisterPageObject registerPage;
	private WebDriver driver;
	private String firstName, lastName;
	public static String emailAddress, password;
	UserDataMapper userData;
	Environment env;

	@Parameters({ "browserName", "environment" })
	@BeforeTest(description = "Create new User for all Test Classes")
	public void Register(String browserName, String environment) {

		ConfigFactory.setProperty("env", environment);
		env = ConfigFactory.create(Environment.class);

		driver = getBrowserDriver(browserName, env.appUrl());
		homePage = PageGeneratorManager.getUserHomePage(driver);
		userData = UserDataMapper.getUserData();

		firstName = userData.getFirstName();
		lastName = userData.getLastName();

		emailAddress = userData.getEmailAddress() + generateFakeNumber() + "@mail.com";
		password = userData.getPassword();

		log.info("Pre-condition - Step 01: Navigate to Register page.");
		registerPage = homePage.openRegisterPage();

		log.info("Pre-condition - Step 02: Input to First Name textbox with value: '" + firstName + "'");
		registerPage.inputToFirstnameTextbox(firstName);

		log.info("Pre-condition - Step 03: Input to Last Name textbox with value: '" + lastName + "'");
		registerPage.inputToLastnameTextbox(lastName);

		log.info("Pre-condition - Step 04: Input to Email Address textbox with value: '" + emailAddress + "'");
		registerPage.inputToEmailTextbox(emailAddress);

		log.info("Pre-condition - Step 05: Input to Valid Password textbox with value: '" + password + "' ");
		registerPage.inputToPasswordTextbox(password);

		log.info("Pre-condition - Step 06: Input to confirm Password textbox with value: '" + password + "' ");
		registerPage.inputToConfirmPasswordTextbox(password);

		log.info("Pre-condition - Step 07: Click to Register button.");
		registerPage.clickToRegisterButton();

		log.info("Pre-condition - Step 08: Verify Register success message is displayed.");
		verifyEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");

		log.info("Pre-condition - Step 09: Click to Logout link.");
		homePage = registerPage.clickToLogoutLink();

		closeBrowserDriver();

	}
}
