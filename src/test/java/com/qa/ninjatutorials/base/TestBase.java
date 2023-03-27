package com.qa.ninjatutorials.base;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.ninjatutorials.utilities.Utilities;

public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public static ChromeOptions options;
	
	public void laodPropertiesFile() throws Exception { //make method
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\injatutorials\\configure\\config.properties");
		prop.load(ip);
	}
	public TestBase() throws Exception {  //make constructor so that we can use the Super keyword. Either method or constructor will work.
		prop = new Properties();
		FileInputStream ip = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\injatutorials\\configure\\config.properties");
		prop.load(ip);
		
	}
	public WebDriver initializeBrowser(String browserName) { //change public void, to public WebDriver(return driver below. Secure codes optimally
		if(browserName.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}else if(browserName.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		}
		options = new ChromeOptions();
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		options.addArguments("--Incognito");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMPLICIT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGELOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
		}
		
	}


