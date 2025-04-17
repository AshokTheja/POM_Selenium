package com.Sauce.Pages;

import java.io.IOException;

import org.openqa.selenium.By;

import com.Sauce.Test_Base.TestBase;

public class LoginPage extends TestBase {

	// Selectors
	private By username_Field = By.id("user-name");
	private By password_Field = By.id("password");
	private By login_Btn = By.id("login-button");

	// Functions
	public ProductsPage login(String username, String password) throws IOException {

		driver.findElement(username_Field).sendKeys(username);
		driver.findElement(password_Field).sendKeys(password);

		driver.findElement(login_Btn).click();

		return new ProductsPage();

	}

}
