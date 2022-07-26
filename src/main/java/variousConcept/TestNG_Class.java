package variousConcept;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestNG_Class {
	WebDriver driver;
	String browser;
	String url;

	@BeforeClass
	public void readConfig() {
		try {
			InputStream input = new FileInputStream("src\\main\\java\\config\\config.properties");
			Properties prop = new Properties();
			prop.load(input);
			browser = prop.getProperty("browser");
			System.out.println("Browser " + browser);
			url = prop.getProperty("url");

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	@BeforeMethod
	public void lauchBrowser() {

		if (browser.equalsIgnoreCase("chrome")) {

			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");

			driver = new ChromeDriver();

		} else if (browser.equalsIgnoreCase("firefox")) {

			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();

		driver.get(url);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	By USERNAME_LOCATOR = By.xpath("//*[@id='username']");
	By PASSWORD_LOCATOR = By.xpath("//*[@id='password']");
	By LOGIN_LOCATOR = By.xpath("//*[@name='login']");
	By DASHBOARD_LOCATOR = By.xpath("//span[normalize-space()='Dashboard']");
	By CUSTOMER_LOCATOR = By.xpath("//span[normalize-space()='Customers']");
	By ADD_CUSTOMER_HEADER_LOCATOR = By.xpath("//a[normalize-space()='Add Customer']");
	By ADD_CONTACT_HEADER_LOCATOR = By.xpath("//h5[normalize-space()='Add Contact']");
	By FULL_NAME_LOCATOR = By.xpath("//input[@id='account']");
	By COMPANY_LOCATOR = By.xpath("//select[@id='cid']");
	By EMAIL_LOCATOR = By.xpath("//input[@id='email']");
	By PHONE_NUMBER = By.xpath("//input[@id='phone']");
	By ADDRESS_LOCATOR = By.xpath("//input[@id='address']");
	By CITY_LOCATOR = By.xpath("//input[@id='city']");
	By STATE_KOCATOR = By.xpath("//input[@id='state']");
	By ZIP_LOCATOR = By.xpath("//input[@id='zip']");
	By COUNTRY_LOCATOR = By.xpath("//select[@id='country']");
	By TAG_LOCATOR = By.xpath("//select[@id='tags']");
	By SAVE_LOCATOR = By.xpath("//button[@id='submit']");
	By CURRENCY_LOCATOR = By.xpath("//select[@id='currency']");
	By GROUP_LOCATOR = By.xpath("//select[@id='group']");
	By PASS_LOCATOR = By.xpath("//input[@id='password']");
	By CPASS_LOCATOR = By.xpath("//input[@id='cpassword']");
	By WELCOME_LOCATOR = By.xpath("//input[@id='send_client_signup_email']");

	@Test()
	public void login() {
		driver.findElement(USERNAME_LOCATOR).sendKeys("demo@techfios.com");
		driver.findElement(PASSWORD_LOCATOR).sendKeys("abc123");
		driver.findElement(LOGIN_LOCATOR).click();

		String expectedText = "Dashboard";
		String actualText = driver.findElement(DASHBOARD_LOCATOR).getText();
		System.out.println(actualText);

		Assert.assertEquals(actualText, expectedText, "Wrong Page");

	}
	

	@Test()
	public void customerTest() {
		login();
		driver.findElement(CUSTOMER_LOCATOR).click();
		driver.findElement(ADD_CUSTOMER_HEADER_LOCATOR).click();
		Assert.assertEquals(driver.findElement(ADD_CONTACT_HEADER_LOCATOR).getText(), "Add Contact",
				"Urayobye suburamo neza");

		driver.findElement(FULL_NAME_LOCATOR).sendKeys("Patrick Niyibizi");

		Select select1 = new Select(driver.findElement(COMPANY_LOCATOR));
		select1.selectByVisibleText("Amazon");
		driver.findElement(EMAIL_LOCATOR).sendKeys(generateRandom(999) +"niyibixipatty@gmail.com");
		driver.findElement(PHONE_NUMBER).sendKeys(generateRandom(9)+"6824027482");
		driver.findElement(ADDRESS_LOCATOR).sendKeys("6930 W CREECKHAVEN DR");
		driver.findElement(CITY_LOCATOR).sendKeys("Fort Worth");
		driver.findElement(STATE_KOCATOR).sendKeys("Texas");
		driver.findElement(ZIP_LOCATOR).sendKeys("76137");
		driver.findElement(CURRENCY_LOCATOR).click();
		
		Select select3 = new Select(driver.findElement(GROUP_LOCATOR));
		select3.selectByVisibleText("Selenium");
		driver.findElement(PASS_LOCATOR).sendKeys("1234567");
		driver.findElement(CPASS_LOCATOR).sendKeys("1234567");
		

//		driver.findElement(WELCOME_LOCATOR).click();

		Select Select2 = new Select(driver.findElement(COUNTRY_LOCATOR));
		Select2.selectByVisibleText("United States");
		
		Select select4=new Select(driver.findElement(TAG_LOCATOR));
		select4.selectByVisibleText("Enrico");
        driver.findElement(SAVE_LOCATOR).click();

	}
	
	private int generateRandom(int gereratedRandom) {
		Random rd= new Random();
		int results=rd.nextInt(gereratedRandom);
		return results;
	}

	@AfterMethod
	public void tearDown() {
//		driver.close();
//		driver.quit();

	}

}
