package com.qa.opencart.pages;

import org.openqa.selenium.By;

public class OrderPage {

	String itemname;
	float price;
	
	private By ordername = By.id("ordername");
	private By ordernumber = By.className("Ordernumber");
	
	
	public void Itemdescription()
	{
		System.out.println("The item is");
	}
	
	
}
