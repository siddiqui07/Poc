package com.yash.poc.executionengine;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import com.yash.poc.configuration.ExcelReader;
import com.yash.poc.utils.UdemyReusables;

/**
 * This class executes the add course to wish list it extends UdemyReusables
 * class.
 * 
 * @author siddiqui.mahboob
 *
 */
public class AddCourseToWishList extends UdemyReusables {
	// creating an object for excel reading
	ExcelReader read = new ExcelReader();
	// log4j
	Logger log = Logger.getLogger(AddCourseToWishList.class);

	/**
	 * This method add course to wish list
	 */
	@Test
	public void addCourseToWishlist() {
		log.info("addCourseToWishlist Case initiated");
		for (int i = wishStartRow; i <= wishEndRow; i++) {

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
				System.out.println("no method found AddCourseToWishList Case");
				break;
			}
		}
		// capture screenshot
		captureScreenShot("CourseAdded to WishList");
		// Logout
		logoutFromAccount(read.readCell(wishLogout, locatorValue), read.readCell(wishVerifyLogout, locatorValue));
		// quit browser
		quitBrowser();
		log.info("addCourseToWishlist Case Ended");

	}
}
