package com.yash.poc.executionengine;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.yash.poc.configuration.ExcelReader;
import com.yash.poc.utils.UdemyReusables;

/**
 * This class is to verify the login and logout scenario it extends
 * UdemyReusables class.
 * 
 * @author siddiqui.mahboob
 *
 */
public class LoginAndLogout extends UdemyReusables {
	ExcelReader read = new ExcelReader();
	public static Logger log = Logger.getLogger(LoginAndLogout.class);

	/**
	 * This method verifies the application login functionality with invalid
	 * credentials
	 */
	@Test
	public void udemyInvalidUserSignin() {
		log.info("Login Case1 udemyInvalidUserSigninVerification Initiated");
		for (int i = loginInvalidStartRow; i <= loginInvalidEndRow; i++) {
			switch (read.readCell(i, functionName)) {
			case "useBrowser":
				useBrowser(read.readCell(i, browser));
				break;
			case "urlAccess":
				urlAccess(read.readCell(i, testValue));
				break;
			case "udemyClickElement":
				udemyClickElement(read.readCell(i, locatorType), read.readCell(i, locatorValue));
				lessudmyWait();
				break;
			case "udemySendKey":
				udemySendKey(read.readCell(i, locatorType), read.readCell(i, locatorValue),
						read.readCell(i, testValue));
				break;
			default:
				System.out.println("no method found in udemyInvalidUserSigninVerification Case");
				break;
			}
		}
		String actualValue = udemyGetValue(read.readCell(invalidLoginVerify, locatorType),
				read.readCell(invalidLoginVerify, locatorValue));
		String expectedValue = read.readCell(invalidLoginVerify, testValue);
		// Comparing expected vs actual value
		SoftAssert softAssert = new SoftAssert();
		 softAssert.assertEquals(actualValue, expectedValue);
		lessudmyWait();
		// capture screenshot
		captureScreenShot("loggedin");
		// page refresh
		refreshBrowser();
		log.info("Login Case1 udemyInvalidUserSigninVerification Ended");
	}

	/**
	 * This method verifies the application login functionality with
	 * valid/registered credentials
	 */
	@Test(dependsOnMethods = { "udemyInvalidUserSignin" })
	public void udemyValidUserSignin() {
		log.info("Login Case2 udemyValidUserSigninVerification Initiated");
		for (int i = loginValidStartRow; i <= loginValidEndRow; i++) {
			switch (read.readCell(i, functionName)) {

			case "urlAccess":
				urlAccess(read.readCell(i, testValue));
				break;
			case "udemyClickElement":
				udemyClickElement(read.readCell(i, locatorType), read.readCell(i, locatorValue));
				lessudmyWait();
				break;
			case "udemySendKey":
				udemySendKey(read.readCell(i, locatorType), read.readCell(i, locatorValue),
						read.readCell(i, testValue));
				break;
			default:
				System.out.println("no method found udemyValidUserSigninVerification Case");
				break;
			}
		}
		String actualValue = udemyGetValue(read.readCell(validLoginVerify, locatorType),
				read.readCell(validLoginVerify, locatorValue));
		String expectedValue = read.readCell(validLoginVerify, testValue);
		// Comparing expected vs actual value
		SoftAssert softAssert = new SoftAssert();
		 softAssert.assertEquals(actualValue, expectedValue);
		lessudmyWait();
		// capture screenshot
		captureScreenShot("validLogin");
		log.info("Login Case2 udemyInvalidUserSigninVerification Ended");
	}

	@Test(dependsOnMethods = { "udemyValidUserSignin" })
	public void logout() {
		log.info("Login Case3 logoutVerification Initiated");
		logoutFromAccount(read.readCell(acountIcon, locatorValue), read.readCell(validLogout, locatorValue));
		lessudmyWait();
		captureScreenShot("loggedout");
		String expectedValue = read.readCell(verifyLogout, testValue);
		String actualValue = udemyGetValue(read.readCell(verifyLogout, locatorType),
				read.readCell(verifyLogout, locatorValue));
		// Comparing expected vs actual value
		SoftAssert softAssert = new SoftAssert();
		 softAssert.assertEquals(actualValue, expectedValue);
		// quit browser
		quitBrowser();
		log.info("Login Case3 logoutVerification Ended");
	}

}
