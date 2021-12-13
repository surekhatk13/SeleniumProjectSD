package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class AccountPage {

	private WebDriver driver; 
	private ElementUtil eleUtil;
	
	private By header = By.cssSelector("div#logo a");
	//private By header = By.ByLinkText("Your Store");
	
   private By accountSections = By.cssSelector("div#content h2");
   private By cartButton = By.cssSelector("div#cart button");
   private By searchField = By.name("search");
   private By searchbutton = By.cssSelector("div#search button");
   private By logoutlink = By.linkText("Logout");
	


	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String getAccountPageTitle() {
		return eleUtil.doGetTitle(Constants.ACCOUNT_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public String getAccountPageHeader() {
		return eleUtil.doGetText(header);
	}
	
	public boolean isLogoutLinkExist() {
		return eleUtil.doIsDisplayed(logoutlink);
	}
	
	
	public void logoutFromApplication() {
		eleUtil.doActionClick(logoutlink);
	}
	
	public List<String> getAccountSecList() {
		List<WebElement> accountSecList = eleUtil.waitForElementsToBeVisible(accountSections, 10);
		List<String> accSecValList = new ArrayList<String>();
		for(WebElement e:accountSecList) {
			String text = e.getText();
			accSecValList.add(text);
		}
		return accSecValList;
	}
	
	public boolean issearchfieldexist() {
		return eleUtil.isElementExist(searchField);
	}
	
	public searchResultsPage  doSearch(String productName) {
		System.out.println("Searching the product:" +productName);
		eleUtil.doSendKeys(searchField, productName);
		eleUtil.doActionClick(searchbutton);
		return new searchResultsPage(driver);
		
		
	}
	
}
