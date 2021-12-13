package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.util.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleutil; 
	
	private By productHeader = By.xpath("//div[@id='content']//h1");
	private By productimages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(1) li");
	private By productPriceData = By.cssSelector("div#content ul.list-unstyled:nth-of-type(2) li");
	private By qty = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	
	private Map<String, String> productInfoMap;
	
	
	public ProductInfoPage(WebDriver driver) {
	   this.driver = driver;
	   eleutil = new ElementUtil(driver);
	}
	   
	   public String  getProductHeader() {
		   String productheadertext=  eleutil.doGetText(productHeader);
		   System.out.println("The product header is :" +productheadertext);
		return productheadertext;
		   
		   	   }
	
         public int getProductImgcount() {
        	 return eleutil.waitForElementsToBeVisible(productimages, 10).size();
         }

         
         public Map<String, String> getProductInfo() {
        	 productInfoMap = new LinkedHashMap<String, String>();
        	 productInfoMap.put("name", getProductHeader());
        	 getMetaData();
        	 getProductPriceData();
        	 return productInfoMap;
         }
        
         
         /*
          * Brand: Apple
Product Code: Product 18
Reward Points: 800
Availability: Out Of Stock
          */
         private void getMetaData() {
        	 List<WebElement> metaDataList =  eleutil.getElements(productMetaData);
        	 for (WebElement e : metaDataList) {
     			String text = e.getText();
     			String meta[] = text.split(":");
     			String metaKey = meta[0].trim();
     			String metaValue = meta[1].trim();
     			productInfoMap.put(metaKey, metaValue);
     		}
     	}
         

         private void getProductPriceData() {

     		List<WebElement> metaPriceList = eleutil.getElements(productPriceData);
//     		$2,000.00
//     		Ex Tax: $2,000.00
     		String price = metaPriceList.get(0).getText().trim();
     		String exPrice = metaPriceList.get(1).getText().trim();
     		productInfoMap.put("price", price);
     		productInfoMap.put("ExTaxPrice", exPrice);

     	}

     }




