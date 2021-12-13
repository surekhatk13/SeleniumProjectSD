package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

	public WebDriver driver;
	public Properties prop;
	public static String highlight;
	public OptionsManager optionsmanager;
	
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	/**
	 * This method is used to initialize the webDriver
	 * @param browserName
	 * @return this will return the driver
	 */
	public WebDriver init_driver(Properties prop) {
		
		String browserName = prop.getProperty("browser").trim();
		
		
		System.out.println("Browser name is:" +browserName);
		
		highlight = prop.getProperty("highlight");
		optionsmanager = new OptionsManager(prop);
	 
		if(browserName.equals("chrome")){
			WebDriverManager.chromedriver().setup();
			//driver = new ChromeDriver(optionsmanager.getChromeOptions());
			tlDriver.set(new ChromeDriver(optionsmanager.getChromeOptions()));
			}else if(browserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				tlDriver.set(new FirefoxDriver(optionsmanager.getFirefoxOptions()));
				//driver = new FirefoxDriver(optionsmanager.getFirefoxOptions());
			}else if(browserName.equals("safari")) {
				tlDriver.set(new SafariDriver());
				//driver = new SafariDriver();
			}
			else
			{
				System.out.println("please pass the right browser:" +browserName);
			}
		
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		//openUrl(prop.getProperty("url"));
		URL url;
		try {
			url = new URL(prop.getProperty("url"));
			openUrl(url);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
				//getDriver().get(prop.getProperty("url"));
		return getDriver();
		
		
			}
	
	/*
	 * getdriver(); it will return a  thread local copy of the web driver
	 */
		
	public static synchronized WebDriver getDriver() {
		return tlDriver.get();
	}
	
	
	
	/**
	 * this method is used to initialize the properties 
	 * @return this will return properties prop reference
	 */
	
		public Properties init_prop() {
			prop = new Properties();
			FileInputStream ip = null;
			
			String envName = System.getProperty("env");  //qa/dev/stage/uat
			
			if(envName==null) {
				System.out.println("Running in PROD env");
				 try {
					ip = new FileInputStream("./src/test/resources/config/config.properties");
				} catch (FileNotFoundException e) {
					
					e.printStackTrace();
				}
				
			}else {
				System.out.println("Running on the environment:" +envName);
			try {
			switch (envName.toLowerCase()) {
			
			case "qa":
				ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
				break;
			case "dev":
				ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
				break;
			case "stage":
				ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
				break;
			case "uat":
				ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
				break;

			default:
				System.out.println("Please pass the right Environment ");
				break;
			}
			}
			catch(FileNotFoundException e) {
				e.printStackTrace();
			}
			}
			
			try {
				prop.load(ip);
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			
			
			
		return prop;
		
		}
		/**
		 * take screenshot
		 */
		
		public String getScreenshot() {
			File src = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
			String path = System.getProperty("user.dir")+"/screenshots/"+System.currentTimeMillis()+".png";
			File destination = new File(path);
			try {
				FileUtils.copyFile(src, destination);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return path;
		}
		
		
		public void openUrl(URL url) {
			try {
			if(url==null) {
				throw new Exception("url is null");
			}
			}
			catch(Exception e) {
			}
			
			getDriver().navigate().to(url);
		}
			public void openUrl(String  url) {
				try {
					if(url==null) {
						throw new Exception("url is null");
					}
				}
				catch(Exception e) {
				}
				
				getDriver().navigate().to(url);
		}
		public void openUrl(String baseurl, String path) {
			try {
				if(baseurl==null) {
					throw new Exception("baseurl is null");
				}
			}
			catch(Exception e) {
			}
			//http://amazon.com//accpage/users.html
			getDriver().get(baseurl+"/"+path);
		}
		public void openUrl(String baseurl, String path,String queryParam) {
			try {
				if(baseurl==null) {
					throw new Exception("baseurl is null");
				}
			}
			catch(Exception e) {
			}
			//http://amazon.com//accpage/users.html?route=account/login
			getDriver().get(baseurl+"/"+path+"?"+queryParam);
		}
	}
	

