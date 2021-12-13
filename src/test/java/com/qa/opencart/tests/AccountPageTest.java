package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.util.Constants;
import com.qa.opencart.util.Error;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accPageSetup() {
		accountpage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test(priority = 1)
	public void accPageTitleTest() {
		String acctitle = accountpage.getAccountPageTitle();
		System.out.println("Account Page Title is :" +acctitle);
		Assert.assertEquals(acctitle, Constants.ACCOUNT_PAGE_TITLE);
	}
	
	
	@Test(priority = 2)
	public void accPageHeaderTest() {
		String headTitle = accountpage.getAccountPageHeader();
		System.out.println(headTitle);
		Assert.assertEquals(headTitle, Constants.ACCOUNT_PAGE_HEADER, Error.ACCOUNT_HEADER_PAGE_NOT_FOUND_ERROR_MESSG);
	}
	
	@Test(priority = 3)
	public void isLogoutExistTest() {
		Assert.assertTrue(accountpage.isLogoutLinkExist());
	}
	
	
	
	@Test(priority = 4)
	public void accountSecTest(){
		List<String> accSecList= accountpage.getAccountSecList();
		Assert.assertEquals(accSecList, Constants.getExpectedAccSecList());
	}
//	@DataProvider
	public Object[][] productData() {
		return new Object[][] { 
			{ "MacBook" }, 
			{ "Apple" }, 
			{ "Samsung" }, 
			};
	}
//	@Test(priority = 5, dataProvider = "productData")
	public void searchTest(String productName) {
		searchResultsPage = accountpage.doSearch(productName);
		Assert.assertTrue(searchResultsPage.getProductsListCount() > 0);
	}
	
	@DataProvider
	public Object[][] productSelectData() {
		return new Object[][] { 
			{ "MacBook" , "MacBook Pro"}, 
			{ "iMac", "iMac" }, 
			{ "Samsung" , "Samsung SyncMaster 941BW"},
			{"Apple", "Apple Cinema 30\""}
			};
	}

	@Test(priority = 6, dataProvider = "productSelectData")
	public void selectProductTest(String productName, String mainProductName) {
		searchResultsPage = accountpage.doSearch(productName);
		productInfoPage = searchResultsPage.selectProduct(mainProductName);
		Assert.assertEquals(productInfoPage.getProductHeader(), mainProductName);
	}

}
//	@Test(priority = 5, dataProvider = "productData")
//	public void SearchTest(String productName) {
//		searchResultsPage = accountpage.doSearch(productName);
//		Assert.assertTrue(searchResultsPage.getProductsListCount()>0);
//	}
	
//	@DataProvider
//	public Object[][] productSelectData() {
//		return ExcelUtil.getTestData(Constants.PRODUCT_SHEET_NAME);
//		}
//		
//	
//	
//	@Test(priority = 6,dataProvider = "productSelectData")
//	public void selectproductTest(String productName, String mainProductName) {
//		searchResultsPage = accountpage.doSearch("productName");
//		productInfoPage = searchResultsPage.selectProduct("mainProductName");
//		Assert.assertEquals(productInfoPage.getProductHeader(),mainProductName);
//		
//		
//	}
//	

