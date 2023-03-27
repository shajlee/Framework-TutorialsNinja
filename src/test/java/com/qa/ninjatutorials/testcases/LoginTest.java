package com.qa.ninjatutorials.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ninjatutorials.base.TestBase;
import com.qa.ninjatutorials.utilities.Utilities;

public class LoginTest extends TestBase {
	public LoginTest() throws Exception {
		super();
		
	}

	public static WebDriver driver;
	public static ChromeOptions options;


	@BeforeMethod
	public void setUp() {
		driver = initializeBrowser(prop.getProperty("BrowserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}

	@Test(priority = 1)
	public void logInWithValidCredentials() throws Exception {
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(prop.getProperty("validEmail1"));
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("validPassword1"));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(),
				"The Link Showing Edit your account information does not exit");
		
	}

	@Test(priority = 2)
	public void logInWithInvalidCredentials() throws Exception {
		driver.findElement(By.cssSelector("input#input-email"))
				.sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("Shajlee12");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(2000);
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");
		
	}

	@Test(priority = 3)
	public void logInWithInvalidEmailValidPassword() throws Exception {
		driver.findElement(By.cssSelector("input#input-email"))
				.sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("validPassword1"));
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(2000);
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");
		
	}

	@Test(priority = 4)
	public void logInWithValidEmailInValidPassword() throws Exception {
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(prop.getProperty("validEmail1"));
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("Shajlee12");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(2000);
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");
		
	}

	@Test(priority = 5)
	public void logInWithNoCredentials() throws Exception {
		driver.findElement(By.cssSelector("input#input-email")).sendKeys("");
		driver.findElement(By.cssSelector("input#input-password")).sendKeys("");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		Thread.sleep(2000);
		String actualWarningMessage = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible"))
				.getText();
		String expectedWarningMessage = "Warning: No match for E-Mail Address and/or Password.";
		Assert.assertTrue(actualWarningMessage.contains(expectedWarningMessage),
				"Expected Warning Message is not displayed");
		
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();

	}

	

}
