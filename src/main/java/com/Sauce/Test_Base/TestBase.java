package com.Sauce.Test_Base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	protected static WebDriver driver;
	protected static WebDriverWait wait;
	public static Properties prop;

	// Initialization Method
	public void initialization() throws IOException {

		FileInputStream fis = new FileInputStream(
				"C:\\Users\\ashok\\eclipse-workspace\\POM_Selenium\\src\\main\\java\\com\\Sauce\\Config\\configurationData.Properties");
		prop = new Properties();
		prop.load(fis);

		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");

		if (browser.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();

			ChromeOptions options = new ChromeOptions();

			options.addArguments("start-maximized");
			options.addArguments("--disable-features=PasswordManagerEnabled");
			options.setExperimentalOption("prefs",
					Map.of("credentials_enable_service", false, "profile.password_manager_enabled", false));

			driver = new ChromeDriver(options);

		} else if (browser.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			// Create Firefox profile
			FirefoxProfile profile = new FirefoxProfile();

			// Disable password manager features
			profile.setPreference("signon.rememberSignons", false); // Don't remember logins
			profile.setPreference("signon.autofillForms", false); // Don't autofill forms
			profile.setPreference("signon.autologin.proxy", false); // No proxy auto-login
			profile.setPreference("network.http.phishy-userpass-length", 255); // Suppress phishing warning on
																				// credentials in URL

			// Attach profile to options
			FirefoxOptions options = new FirefoxOptions();
			options.setProfile(profile);

			// Start maximized (workaround since Firefox doesn't support --start-maximized
			// like Chrome)
			options.addArguments("--width=1920");
			options.addArguments("--height=1080");

			// Launch browser
			driver = new FirefoxDriver(options);

		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();

			// Maximize window on start
			options.addArguments("start-maximized");

			// Disable Edge's password manager popup
			options.setExperimentalOption("prefs",
					Map.of("credentials_enable_service", false, "profile.password_manager_enabled", false));

			// Launch Edge browser
			driver = new EdgeDriver(options);
		}

		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		driver.get(url);

	}

	public void quitDriver() {
		if (driver != null) {
			driver.quit();
		}
	}

}
