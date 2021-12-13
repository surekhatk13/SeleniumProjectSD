package com.qa.opencart.tests;

import java.util.Properties;


import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.searchResultsPage;

public class BaseTest {

	DriverFactory df;
	WebDriver driver;
	LoginPage loginpage;
	Properties prop;
	AccountPage accountpage;
	searchResultsPage searchResultsPage;
	ProductInfoPage productInfoPage;
	RegisterPage registrationPage;
	SoftAssert softassert;
	
	
	
	
	@BeforeTest
	
	public void setup(){
		 df = new DriverFactory();
		 prop = df.init_prop();
		 driver = df.init_driver(prop);
		 loginpage = new LoginPage(driver);
		 softassert = new SoftAssert();
	}

	@AfterTest
	public void tearDown() {
		
	}
	
	
}
