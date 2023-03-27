package com.qa.ninjatutorials.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ninjatutorials.base.TestBase;

public class SearchTest extends TestBase {
	public SearchTest() throws Exception {
		super();
		
	}

	public static WebDriver driver;
	public static ChromeOptions options;

	@BeforeMethod
	public void startUp() {
		driver = initializeBrowser(prop.getProperty("BrowserName"));
	}

	@Test(priority = 1)
	public void verifySearchWithValidProduct() {
		driver.findElement(By.name("search")).sendKeys(prop.getProperty("searchItem1"));
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Samsung SyncMaster 941BW")).isDisplayed());

	}

	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {
		driver.findElement(By.name("search")).sendKeys(prop.getProperty("searchItem2"));
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		String actualInvalidSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]"))
				.getText();
		Assert.assertEquals(actualInvalidSearchMessage, "There is no product that matches the search criteria.",
				"The Search Message is Not Displayed");

	}

	@Test(priority = 3)
	public void verifySearchSearchWithNoProduct() {
		driver.findElement(By.cssSelector("button.btn.btn-default.btn-lg")).click();
		String actualNoProductSearchMessage = driver
				.findElement(By.xpath("//p[contains(text(), 'There is no product that matches the search criteria.')]"))
				.getText();
		Assert.assertEquals(actualNoProductSearchMessage, "There is no product that matches the search criteria.",
				"The Search Message is Not Displayed");

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

}
