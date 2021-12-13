package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("EPIC 100:Design open cart App")
@Story("S100 open cart app with multiple features")
public class LoginPageTest extends BaseTest{

	@Description("Login page Title Test")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginpage.getLoginPageTitle();
		System.out.println("Page title is :" +actTitle);
		Assert.assertEquals(actTitle, Constants.LOGIN_PAGE_TITLE);
	}
	
	@Description("Login page URL Test")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2, enabled = false)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginpage.getLoginPageUrl());
	}
	
	@Description("Login page historylink Test")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void orderhistorylinkTest() {
		Assert.assertTrue(loginpage.isorderhistorylinkExist());
	}
	
	@Test(priority = 4)
	public void registerlinkTest() {
		Assert.assertTrue(loginpage.isregisterlinkExist());
	}
	
	@Test(priority = 5)
	public void doLoginTest() {
		accountpage = loginpage.doLogin(prop.getProperty("username").trim(), prop.getProperty("password").trim());
		Assert.assertEquals(accountpage.getAccountPageTitle(),Constants.ACCOUNT_PAGE_TITLE);
		
	}
}
