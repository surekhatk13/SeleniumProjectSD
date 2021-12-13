package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class searchResultsPage {
	
	private WebDriver driver;
	private ElementUtil eleutil; 
	
	private By productResults = By.cssSelector("div.caption a");

	public searchResultsPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	public int getProductsListCount() {
		int resultCount = eleutil.waitForElementsToBeVisible(productResults, 10, 2000).size();
		System.out.println("the search product count: " + resultCount);
		return resultCount;
	}
	
	public ProductInfoPage selectProduct(String mainProductName) {
		System.out.println("main product name is : " + mainProductName);
		List<WebElement> searchList = eleutil.waitForElementsToBeVisible(productResults, 10, 2000);
		for (WebElement e : searchList) {
			String text = e.getText();
			if (text.equals(mainProductName)) {
				e.click();
				break;
			}
		}
		return new ProductInfoPage(driver);

	}

}


