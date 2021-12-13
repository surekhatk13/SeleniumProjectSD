package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {

	
	
	//Whenever we are creating a page class 
	//1.Initialize it's own Private driver
	private WebDriver driver;
	private ElementUtil eleutil;
	
	//2.Create Page constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleutil = new ElementUtil(driver);
	}
	//3.Create By locators
	private By registerlink = By.linkText("Register");
	private By email = By.id("input-email");
	private By password = By.id("input-password");
	private By orderhistory = By.linkText("Order History");
	private By loginBtn = By.xpath("//input [@value='Login']");
	private By loginerrormsg = By.xpath("//div[@class = 'alert alert-danger alert-dismissible']"); 
	
	
	
	//4.Page Actions
	@Step("Get Login page title....")
	public String getLoginPageTitle() {
		return eleutil.doGetTitle(Constants.LOGIN_PAGE_TITLE, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean getLoginPageUrl() {
		return eleutil.waitForURLToContain(Constants.LOGIN_PAGE_URL_FRACTION, Constants.DEFAULT_TIME_OUT);
	}
	
	public boolean isregisterlinkExist() {
		return eleutil.doIsDisplayed(registerlink);
	}
	
	public boolean isorderhistorylinkExist() {
		return eleutil.doIsDisplayed(orderhistory);
	}
	
	public AccountPage doLogin(String un, String pwd) {
		System.out.println("Login with :" +un + ":" +pwd);
		eleutil.doSendKeys(email, un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		return new AccountPage(driver);
	}

	public boolean doLoginWithWrongCredentials(String un, String pwd) {
		System.out.println("Try to login with wrong credentials:" +un +":"+pwd);
		eleutil.doSendKeys(email, un);
		eleutil.doSendKeys(password, pwd);
		eleutil.doClick(loginBtn);
		
		String errormsgtext = eleutil.doGetText(loginerrormsg);  
		System.out.println(errormsgtext);
		if(errormsgtext.contains(Constants.LOGIN_ERROR_MSG)) {
			System.out.println("Login not successful");
			return false;
		}
		return true;
	}

	
		public RegisterPage goToRegisterationPage() {
			eleutil.doClick(registerlink);
			return new RegisterPage(driver);
		}
}
