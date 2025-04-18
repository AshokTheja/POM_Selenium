package com.Sauce.Tests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.Sauce.Pages.LoginPage;
import com.Sauce.Pages.ProductsPage;
import com.Sauce.TestUtils.Test_Utils;
import com.Sauce.Test_Base.TestBase;

public class TC_Sauce_TestFilterNameZtoA extends TestBase {

	LoginPage loginPage;
	ProductsPage productsPage;

	static ArrayList<String> reqTCDataList;

	@SuppressWarnings("static-access")
	@BeforeMethod
	public void setUp() throws IOException {

		System.out.println("Setup Started: TC_Sauce_TestFilterNameZtoA");

		Test_Utils data = new Test_Utils();
		reqTCDataList = (ArrayList<String>) data.getTestDataByTestCaseName(this.getClass().getSimpleName());

		initialization();

		loginPage = new LoginPage();

	}

	@SuppressWarnings("unchecked")
	@Test
	public void test_FilterNameAtoZ() throws IOException {
		
		System.out.println("Test Started: TC_Sauce_TestFilterNameZtoA");

		String expectedURL = reqTCDataList.get(1);
		String filter = reqTCDataList.get(2);
		String expectedList = reqTCDataList.get(3);

		List<String> expectedListItems = Arrays.asList(expectedList.split(",\\s*")); // split by comma and optional
																						// space

		productsPage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));

		String actualURL = productsPage.validate_ProductsPage();

		Assert.assertEquals(actualURL, expectedURL);

		productsPage.select_Filter(filter);

		List<String> actualList = productsPage.get_ListOfInventoryItems();

		Assert.assertEquals(actualList, expectedListItems, "Lists do not match");

	}

	@AfterMethod
	public void tearDown() throws InterruptedException {

		System.out.println("TearDown Started: TC_Sauce_TestFilterNameZtoA");
		quitDriver();

	}

}
