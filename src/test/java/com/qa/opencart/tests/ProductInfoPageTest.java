package com.qa.opencart.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;

public class ProductInfoPageTest extends BaseTest {

	
	
	@BeforeClass
	public void productInfoSetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority = 1)
	public void productHeaderTest() {
		searchResultsPage = accountpage.doSearch("MacBook");
		productInfoPage = searchResultsPage.selectProduct("MacBook Pro");
		Assert.assertEquals(productInfoPage.getProductHeader(),"MacBook Pro");
			}
	
	@Test(priority = 2)
	public void productImagesCountTest() {
		searchResultsPage = accountpage.doSearch("iMac");
		productInfoPage = searchResultsPage.selectProduct("iMac");
		Assert.assertEquals(productInfoPage.getProductImgcount(), Constants.IMAC_IMAGE_COUNT);
	}
		//Assert.assertEquals(productInfoPage.getProductImgcount(), Constants.MACBOOKPRO_IMAGE_COUNT);
		
	
    @Test(priority = 3)
	public void productInfotest()
	{
    	
    	searchResultsPage  = accountpage.doSearch("MacBook");
    		productInfoPage = searchResultsPage .selectProduct("MacBook Pro");
    		Map<String, String> actProductInfoMap = productInfoPage.getProductInfo();
    		actProductInfoMap.forEach((k, v) -> System.out.println(k + ":" + v));
    		softassert.assertEquals(actProductInfoMap.get("name"), "MacBook Pro");
    		softassert.assertEquals(actProductInfoMap.get("Brand"), "Apple");
    		softassert .assertEquals(actProductInfoMap.get("Reward Points"), "800");
    		softassert.assertEquals(actProductInfoMap.get("price"), "$2,000.00");
    		softassert.assertAll();
	}
	
	}

