package com.yash.poc.utils;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;

import com.yash.poc.configuration.PropertyReader;

/**
 * @author siddiqui.mahboob This class have all the reusable components for all
 *         scenarios
 *
 */
public class UdemyReusables {
	public WebDriver driver;
	Logger log = Logger.getLogger(UdemyReusables.class);
	PropertyReader propertyReader = new PropertyReader();

	// Globally Required Columns
	public int functionName = propertyReader.reader("FUNCTION_NAME");
	public int browser = propertyReader.reader("BROWSER");
	public int testValue = propertyReader.reader("TEST_VALUE");
	public int locatorType = propertyReader.reader("LOCATOR_TYPE");
	public int locatorValue = propertyReader.reader("LOCATOR_VALUE");

	// SignUp Required Rows
	public int signupInvalidStartRow = propertyReader.reader("SIGNUP_INVALID_START_ROW");
	public int signupInvalidEndRow = propertyReader.reader("SIGNUP_INVALID_END_ROW");

	public int signupValidStartRow = propertyReader.reader("SIGNUP_VALID_START_ROW");
	public int signupValidEndRow = propertyReader.reader("SIGNUP_VALID_END_ROW");

	public int invalidSignUp = propertyReader.reader("INVALID_SIGNUP");
	public int validSignUpMouseOver = propertyReader.reader("VALID_SIGNUP_MOUSEOVER");
	public int validSignUpLogout = propertyReader.reader("VALID_SIGNUP_LOGOUT");

	// Login Required Rows
	public int loginInvalidStartRow = propertyReader.reader("LOGIN_INVALID_START_ROW");
	public int loginInvalidEndRow = propertyReader.reader("LOGIN_INVALID_END_ROW");
	public int loginValidStartRow = propertyReader.reader("LOGIN_VALID_START_ROW");
	public int loginValidEndRow = propertyReader.reader("LOGIN_VALID_END_ROW");

	public int invalidLoginVerify = propertyReader.reader("INVALID_LOGIN_VERIFY");
	public int validLoginVerify = propertyReader.reader("VALID_LOGIN_VERIFY");
	public int acountIcon = propertyReader.reader("ACOUNT_ICON");
	public int validLogout = propertyReader.reader("VALID_LOGOUT");
	public int verifyLogout = propertyReader.reader("VERIFY_LOGOUT");

	// Wish list Required Rows
	public int wishStartRow = propertyReader.reader("WISH_START_ROW");
	public int wishEndRow = propertyReader.reader("WISH_END_ROW");
	public int wishLogout = propertyReader.reader("WISH_LOGOUT");
	public int wishVerifyLogout = propertyReader.reader("WISH_VERIFY_LOGOUT");

	// Add to cart Required Rows
	public int cartStartRow = propertyReader.reader("CART_START_ROW");
	public int cartEndRow = propertyReader.reader("CART_END_ROW");

	public int verifyAddCart = propertyReader.reader("VERIFY_ADD_CART");
	public int cartLogout = propertyReader.reader("CART_LOGOUT");

	/**
	 * This method is capable of redirect user to his/her URL and maximize the
	 * window.
	 * 
	 * @param url
	 *            Example :http://www.google.com
	 */
	public void urlAccess(String url) {
		driver.get(url);
		log.info("redirected to the url");
		driver.manage().window().maximize();
		log.info("window maximized");
		driver.manage().deleteAllCookies();
	}

	/**
	 * This method select his browser.
	 * 
	 * @param browser
	 *            Example: google chrome
	 */
	public void useBrowser(String browser) {
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				log.info("Firefox browser selected");
				System.setProperty("webdriver.gecko.driver", "Browsers\\geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				log.info("chrome browser selected");
				System.setProperty("webdriver.chrome.driver", "Browsers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browser.equalsIgnoreCase("IE")) {
				log.info("IE browser selected");
				System.setProperty("webdriver.ie.driver", "Browsers\\IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			}

		} catch (WebDriverException e) {
			log.error("Browser Selection error" + e.getMessage());
		}
	}

	/**
	 * This method close the browser
	 */
	public void quitBrowser() {
		driver.quit();
		log.info("Browser quit");
	}

	/**
	 * This method refresh the page
	 */
	public void refreshBrowser() {
		driver.navigate().refresh();
		log.info("Page Refreshed");
	}

	/**
	 * This method is click on specified element.
	 * 
	 * @param locterType
	 *            Example : id,xpath
	 * @param locaterValue
	 *            Example : id=name
	 */
	public void udemyClickElement(String locterType, String locaterValue) {
		switch (locterType) {
		case "id":
			driver.findElement(By.id(locaterValue)).click();
			log.info("Element Clicked");
			break;
		case "xpath":
			driver.findElement(By.xpath(locaterValue)).click();
			log.info("Element Clicked");
			break;
		case "linkText":
			driver.findElement(By.linkText(locaterValue)).click();
			log.info("Element Clicked");
			break;
		default:
			System.out.println("Locator is not available");
			log.info("No udemyClickElement locater selected");
			break;
		}
	}

	/**
	 * This method is send key for specified element.
	 * 
	 * @param locterType
	 *            Example : id,xpath
	 * @param locaterValue
	 *            Example : id=name
	 * @param testData
	 *            Example : siddiqui
	 */
	public void udemySendKey(String locterType, String locaterValue, String testData) {
		switch (locterType) {
		case "id":
			driver.findElement(By.id(locaterValue)).sendKeys(testData);
			log.info("Typed key to field");
			break;
		case "xpath":
			driver.findElement(By.xpath(locaterValue)).sendKeys(testData);
			log.info("Typed key to field");
			break;
		case "linkText":
			driver.findElement(By.linkText(locaterValue)).sendKeys(testData);
			log.info("Typed key to field");
			break;
		default:
			System.out.println("Locator is not available");
			log.info("No udemySendKey locater selected");
			break;
		}

	}

	/**
	 * This method for wait.
	 */
	public void moreudmyWait() {
		int waitTime = 1000;
		try {
			Thread.sleep(waitTime);
			log.info("Waiting");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method for wait.
	 */
	public void lessudmyWait() {
		int waitTime = 5000;
		try {
			Thread.sleep(waitTime);
			log.info("Waiting");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method captures the screenshot
	 * 
	 * @param name
	 *            name of screenshot
	 */
	public void captureScreenShot(String name) {
		// Take screenshot and store as a file format
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			// now copy the screenshot to desired location using copyFile //method
			FileUtils.copyFile(src, new File("Screenshots\\" + name + "_" + System.currentTimeMillis() + ".png"));
			log.info("captured the screenshot");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			log.error("Error while capturing the screenshot");
		}

	}

	/**
	 * This method logout user from account.
	 * 
	 * @param selectElement
	 *            Example:Xapth
	 * @param clickingelement
	 *            Example:Xpath
	 */
	public void logoutFromAccount(String selectElement, String clickingelement) {
		Actions action = new Actions(driver);
		WebElement webelemt = driver.findElement(By.xpath(selectElement));
		action.moveToElement(webelemt).build().perform();
		driver.findElement(By.xpath(clickingelement)).click();
		log.info("loggedout from account");
	}

	/**
	 * This method is get the value from given locater element.
	 * 
	 * @param locatorType
	 *            Example: xpath,id
	 * @param locaterValue
	 *            Example://[@id=""]
	 * @return
	 */
	public String udemyGetValue(String locatorType, String locaterValue) {

		String value = null;
		switch (locatorType) {
		case "id":
			value = driver.findElement(By.id(locaterValue)).getText();
			log.info("getting text from element");
			break;
		case "xpath":
			value = driver.findElement(By.xpath(locaterValue)).getText();
			log.info("getting text from element");
			break;
		case "linkText":
			value = driver.findElement(By.linkText(locaterValue)).getText();
			log.info("getting text from element");
			break;
		default:
			System.out.println("Locator is not available");
			log.info("No udemyGetValue locater selected");
			break;
		}

		return value;
	}
}
