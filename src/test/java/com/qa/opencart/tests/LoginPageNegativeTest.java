package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginPageNegativeTest extends BaseTest{

	@DataProvider
	public Object[][] loginWrongTestData() {
		return new Object[][] {
			{"test11@test.com", "12345"},
			{"swathitk13@gmail.com","test@1234"},
			{"ssssss@test.com",""}
		};
	}
	
	@Test(dataProvider = "loginWrongTestData")
	public void loginNegativeTest(String username, String password) {
		Assert.assertFalse(loginpage.doLoginWithWrongCredentials(username, password));
		
	}
}
