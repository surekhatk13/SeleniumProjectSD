package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver; 
	private ElementUtil eleUtil;
	
	
	
	private By firstName = By.id("input-firstname");
	private By lastName = By.id("input-lastname");
	private By email = By.id("input-email");
	private By telephone = By.id("input-telephone");
	private By password = By.id("input-password");
	private By confirmPassword = By.id("input-confirm");
	
	private By subscribebtnYes = By.xpath("(//label[@class= 'radio-inline'])[position()=1]//input[@type = 'radio']");
	private By subscribebtnNo = By.xpath("(//label[@class= 'radio-inline'])[position()=2]//input[@type = 'radio']");
	
	private By agreeCheckbox = By.xpath("//input[@name ='agree']");
	private By continuebtn = By.xpath("//input[@class ='btn btn-primary']");
	
	private By successmsg = By.xpath("//div[@id = 'content']//h1");
	private By logout = By.linkText("Logout");
	private By registerlink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	public boolean accountRegistration(String firstname, String lastname, 
			String email, String telephone, String password, String subscribe ) {
		eleUtil.doSendKeys(this.firstName, firstname);
		eleUtil.doSendKeys(this.lastName, lastname);
		eleUtil.doSendKeys(this.email, email);
		eleUtil.doSendKeys(this.telephone, telephone);
		eleUtil.doSendKeys(this.password, password);
		eleUtil.doSendKeys(this.confirmPassword, password);
		
		if(subscribe.equals("yes")) {
			eleUtil.doClick(subscribebtnYes);
		}else
		{
			eleUtil.doClick(subscribebtnNo);
	}
	
		eleUtil.doClick(agreeCheckbox);
		eleUtil.doClick(continuebtn);
		String msg = eleUtil.waitForElementToBeVisible(successmsg, 5, 1000).getText();
		
		if(msg.contains(Constants.REGISTRATION_SUCCESS_MSG)) {
			eleUtil.doClick(logout);
			eleUtil.doClick(registerlink);
			return true;
		}
		return false;
		}
	}


