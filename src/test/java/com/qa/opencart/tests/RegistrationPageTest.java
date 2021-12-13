package com.qa.opencart.tests;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ExcelUtil;

public class RegistrationPageTest extends BaseTest{

	
	@BeforeClass
	public void setupRegistration() {
		registrationPage = loginpage.goToRegisterationPage();
			
	}
	
	public String getRandomEmail() {
		Random randomGenerator= new Random();
		String email = "selenium2021"+randomGenerator.nextInt(1000)+"@gmail.com";
		return email;
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getTestData(Constants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider = "getRegisterData")
	public void accountRegistration(String firstname, String lastname, 
			 String telephone, String password, String subscribe) {
		Assert.assertTrue(registrationPage.accountRegistration(firstname, lastname, getRandomEmail(), telephone, password, subscribe));
	}
	
}
