package browserFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FirefoxDriverManager implements BrowserFactory {

	@Override
	public WebDriver getBrowserDriver() {

		WebDriverManager.firefoxdriver().setup();

		FirefoxProfile profile = new FirefoxProfile();

		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		FirefoxOptions options = new FirefoxOptions();
		options.setProfile(profile);

		options.addPreference("intl.accept_languages", "vi-vn, vi, en-us, en");

		options.addArguments("--disable-notifications");

		options.addArguments("--disable-geolocation");

		return new FirefoxDriver(options);
	}

}
