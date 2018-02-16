package com.yash.poc.executionengine;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.yash.poc.configuration.ExcelReader;
import com.yash.poc.utils.UdemyReusables;

/**
 * This class is a execution for Add course to cart scenario it extends
 * UdemyReusables class..
 * 
 * @author siddiqui.mahboob
 *
 */
public class AddCourseToCart extends UdemyReusables {
	// creating an object for excel reader
	ExcelReader read = new ExcelReader();

	// log4j logger class
	Logger log = Logger.getLogger(AddCourseToCart.class);

	/**
	 * This method read the input values from excel and and verify the product added
	 * to cart successfully.
	 */
	@Test
	public void addCourseToCart() {
		log.info("addCourseToCart Case initiated");
		for (int i = cartStartRow; i <= cartEndRow; i++) {
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
				System.out.println("no method found AddCourseToCart Case");
				break;
			}
		}
		// Capturing screenshot
		captureScreenShot("CourseAddedtocart");
		lessudmyWait();
		String expectedValue = read.readCell(verifyAddCart, testValue);
		String actualValue = udemyGetValue(read.readCell(verifyAddCart, locatorType),
		read.readCell(verifyAddCart, locatorValue));
		// Validating the expected and actual values
		 SoftAssert softAssert = new SoftAssert();
		 softAssert.assertEquals(actualValue, expectedValue);
		// Logout from account
		logoutFromAccount(read.readCell(verifyAddCart, locatorValue), read.readCell(cartLogout, locatorValue));
		// quit the browser
		quitBrowser();
		log.info("addCourseToCart Case Completed");
	}
}
