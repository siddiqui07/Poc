package com.yash.poc.executionengine;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.yash.poc.configuration.ExcelReader;
import com.yash.poc.utils.UdemyReusables;

/**
 * This class execute sign-up scenario it extends UdemyReusables class.
 * 
 * @author siddiqui.mahboob
 *
 */
public class SignUp extends UdemyReusables {

	// creating object for ExcelReader class
	ExcelReader read = new ExcelReader();
	// log4j
	public static Logger log = Logger.getLogger(LoginAndLogout.class);

	/**
	 * This method tries to sign-up into application using inValid details.
	 */
	@Test
	public void inValidSingUp() {
		log.info("SignUp Case1 inValidSingup Initiated");
		for (int i = signupInvalidStartRow; i <= signupInvalidEndRow; i++) {
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
				System.out.println("no method found inValidSingup");
				break;
			}
		}
		// Capture screenshot
		captureScreenShot("InvalidSignup");
		lessudmyWait();
		String expectedValue = read.readCell(invalidSignUp, testValue);
		String actualValue = udemyGetValue(read.readCell(invalidSignUp, locatorType),
				read.readCell(invalidSignUp, locatorValue));
		// Comparing expected Vs actual value
		Assert.assertEquals(actualValue, expectedValue);
		refreshBrowser();
		// refresh the page
		lessudmyWait();
		log.info("SignUp Case1 InvalidSignup Initiated");
	}

	/**
	 * This method executes valid SignUp case
	 */
	@Test
	public void validSingUp() {
		log.info("SignUp Case2 validSingup Initiated");
		for (int i = signupValidStartRow; i <= signupValidEndRow; i++) {
			switch (read.readCell(i, functionName)) {
			case "udemyClickElement":
					udemyClickElement(read.readCell(i, locatorType), read.readCell(i, locatorValue));
					lessudmyWait();
				break;
			case "udemySendKey":
				udemySendKey(read.readCell(i, locatorType), read.readCell(i, locatorValue),
						read.readCell(i, testValue));
				break;
			default:
				System.out.println("no method found");
				break;
			}
		}
		// Capture screenshot
		captureScreenShot("ValidSignup inValidSingup");
		// logout from account
		logoutFromAccount(read.readCell(validSignUpMouseOver, locatorValue),
				read.readCell(validSignUpLogout, locatorValue));
		// quit the browser
		quitBrowser();
		log.info("SignUp Case2 validSingup Initiated");
	}
}
