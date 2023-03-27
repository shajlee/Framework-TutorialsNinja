package com.qa.ninjatutorials.testcases;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.ninjatutorials.base.TestBase;
import com.qa.ninjatutorials.utilities.Utilities;

public class RegisterTest extends TestBase {
	public RegisterTest() throws Exception {
		super();
		
	}





	public static WebDriver driver;
	public static ChromeOptions options;
	
	@BeforeMethod
	public void setup() {
		driver = initializeBrowser(prop.getProperty("BrowserName"));
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Register")).click();
	}
	@Test
	public void verifyRegistrationWithMandatoryFields() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("validPassword2"));
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("validPassword2"));
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualHeading = driver.findElement(By.xpath("//div[@id = 'content']/h1")).getText();
		Assert.assertEquals(actualHeading, "Your Account Has Been Created!", "account success message did not display");
	}
	@Test
	public void verifyRegistrationWithAllFields() {
		driver.findElement(By.cssSelector("input#input-firstname")).sendKeys(prop.getProperty("firstName"));
		driver.findElement(By.cssSelector("input#input-lastname")).sendKeys(prop.getProperty("lastName"));
		driver.findElement(By.cssSelector("input#input-email")).sendKeys(Utilities.generateDateTimeStamp());
		driver.findElement(By.cssSelector("input#input-telephone")).sendKeys(prop.getProperty("telephoneNumber"));
		driver.findElement(By.cssSelector("input#input-password")).sendKeys(prop.getProperty("validPassword2"));
		driver.findElement(By.cssSelector("input#input-confirm")).sendKeys(prop.getProperty("validPassword2"));
		
		List<WebElement> NewsLetterButton = driver.findElements(By.cssSelector("label.radio-inline input"));
		for(int i = 0; i<NewsLetterButton.size();i++) {
			if(NewsLetterButton.get(i).getAttribute("value").equalsIgnoreCase("1")) {
				NewsLetterButton.get(i).click();
				break;
			}
		driver.findElement(By.name("agree")).click();
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualHeading = driver.findElement(By.xpath("//div[@id = 'content']/h1")).getText();
		Assert.assertEquals(actualHeading, "Your Account Has Been Created!", "account success message did not display");
		
			}
		}
	@Test
	public void verifyRegistrationWithoutFillingAnyDetails() {
		driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
		String actualPrivacyPolicyWarning = driver.findElement(By.cssSelector("div.alert.alert-danger.alert-dismissible")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains("Warning: You must agree to the Privacy Policy!"), "Privacy Policy Warning not displaying");
		
		String actualFirstNameWarning = driver.findElement(By.cssSelector("input#input-firstname+div")).getText();
		Assert.assertTrue(actualFirstNameWarning.contains("First Name must be between 1 and 32 characters!"), "First Name Warning not displaying");
		
		String actualLastNameWarning = driver.findElement(By.cssSelector("input#input-lastname+div")).getText();
		Assert.assertTrue(actualLastNameWarning.contains("Last Name must be between 1 and 32 characters!"), "Last Name Warning not displaying");
		
		String actualEmailWarning = driver.findElement(By.cssSelector("input#input-email+div")).getText();
		Assert.assertTrue(actualEmailWarning.contains("E-Mail Address does not appear to be valid!"), "Email Warning not displaying");
		
		String actualTelephoneWarning = driver.findElement(By.cssSelector("input#input-telephone+div")).getText();
		Assert.assertTrue(actualTelephoneWarning.contains("Telephone must be between 3 and 32 characters!"));
		
		String actualPasswordWarning = driver.findElement(By.cssSelector("input#input-password+div")).getText();
		Assert.assertTrue(actualPasswordWarning.contains("Password must be between 4 and 20 characters!"));
		
		
		
		
		
		
		
		
	}
		
		
		
	
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
		
		
	}

}
