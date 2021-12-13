package com.qa.opencart.util;

import java.util.ArrayList;
import java.util.List;

public class Constants {

	public static final String LOGIN_PAGE_TITLE = "Account Login11";
	public static final String ACCOUNT_PAGE_TITLE = "My Account";
	public static final String ACCOUNT_PAGE_HEADER = "Your Store11";

	public static final int DEFAULT_TIME_OUT = 5;
	public static final int IMAC_IMAGE_COUNT = 3;
	public static final int MACBOOKPRO_IMAGE_COUNT = 4;
	public static final int MACBOOKAIR_IMAGE_COUNT = 4;
	public static final String  LOGIN_ERROR_MSG = "Warning: No match for E-Mail Address and/or Password.";
	public static final String  REGISTRATION_SUCCESS_MSG = "Your Account Has Been Created!";
	public static final String REGISTER_SHEET_NAME = "opencartregistration";
	public static final String PRODUCT_SHEET_NAME = "product";
	
		public static String LOGIN_PAGE_URL_FRACTION =  "account/login";

		public static List<String> getExpectedAccSecList() {
			List<String> expSecList= new ArrayList<String>();
			expSecList.add("My Account");
			expSecList.add("My Orders");
			expSecList.add("My Affiliate Account");
			expSecList.add("Newsletter");
			return expSecList;
			
		}
	
	
}
