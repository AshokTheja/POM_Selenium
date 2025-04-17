package com.Sauce.Pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Sauce.Test_Base.TestBase;

public class ProductsPage extends TestBase {

	// Locators
	private By inventoryItems = By.className("inventory_item_name");

	// Functions

	public String validate_ProductsPage() {

		return driver.getCurrentUrl();

	}

	public void select_Filter(String text) {

		WebElement reqFilter_Elem = driver.findElement(By.className("product_sort_container"));

		Select select = new Select(reqFilter_Elem);

		select.selectByVisibleText(text);

	}

	@SuppressWarnings("rawtypes")
	public List get_ListOfInventoryItems() {

		List<WebElement> items = driver.findElements(inventoryItems);

		List<String> texts = new ArrayList<>();

		for (WebElement item : items) {
			texts.add(item.getText());
		}
		
		System.out.println("Tests Here: " + texts);

		return texts;

	}

}
