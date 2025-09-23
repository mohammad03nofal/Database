package AutomationDatabase;

import static org.testng.Assert.assertEquals;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestCases extends MyData {
	//WebDriver driver = new EdgeDriver();
	//String Website="https://automationteststore.com/";
	//String SignUpPage="https://automationteststore.com/index.php?rt=account/create";
	//String LogoutPage="https://automationteststore.com/index.php?rt=account/logout";
	//String LoginPage="https://automationteststore.com/index.php?rt=account/login";
	

	
	
	@Test(priority=5,enabled=true)
	public void SignUpTest() throws InterruptedException, SQLException
	{
		driver.navigate().to(SignUpPage);
		driver.manage().window().maximize();
		WebElement TheFirstName= driver.findElement(By.id("AccountFrm_firstname"));
		WebElement TheLastName= driver.findElement(By.id("AccountFrm_lastname"));
		WebElement TheEmail=driver.findElement(By.id("AccountFrm_email"));
		WebElement Telephone = driver.findElement(By.id("AccountFrm_telephone"));
		WebElement Address1= driver.findElement(By.id("AccountFrm_address_1"));
		WebElement Address2=driver.findElement(By.id("AccountFrm_address_2"));
		WebElement Country=driver.findElement(By.id("AccountFrm_country_id"));
		WebElement State=driver.findElement(By.id("AccountFrm_zone_id"));
		Select CountrySelection =new Select(Country);
		Select StateSelection =new Select(State);
		CountrySelection.selectByVisibleText(CustomerCountryInDataBase);
		Thread.sleep(2000);
		StateSelection.selectByIndex(1);
		List <WebElement> AllStates = State.findElements(By.tagName("option"));
		String TheCity = AllStates.get(1).getText();
		WebElement City=driver.findElement(By.id("AccountFrm_city"));
		WebElement ZipCode = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement LoginName= driver.findElement(By.id("AccountFrm_loginname"));
		WebElement Password =driver.findElement(By.id("AccountFrm_password"));
		WebElement ConfirmPassword =driver.findElement(By.id("AccountFrm_confirm"));
		WebElement AgreeBox= driver.findElement(By.id("AccountFrm_agree"));
		WebElement ContinueButton= driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
		
		//-------   Actions ---------------
		//ReadData();
		TheFirstName.sendKeys(CustomerFirstName);
		TheLastName.sendKeys(CustomerLastName);
		TheEmail.sendKeys(Email);
		Telephone.sendKeys(PhoneNumber);
		Address1.sendKeys(AddLine1);
		Address2.sendKeys(AddLine2);
		ZipCode.sendKeys(PostCode);
		City.sendKeys(TheCity);
		LoginName.sendKeys(loginnam);
		Password.sendKeys(pass);
		ConfirmPassword.sendKeys(confpass);
		AgreeBox.click();
		ContinueButton.click();
		Thread.sleep(3000);
		//String actualValue=driver.findElement(By.id(TheCity))
		    		
	}
@Test(priority=6,enabled=true)
	
	public void logOutTest() throws InterruptedException
	{
		driver.navigate().to(LogoutPage);
		Thread.sleep(3000);
		
	}
	
	@Test(priority=7,enabled=true)
	public void LoginTest() throws InterruptedException, SQLException
	{
	 driver.navigate().to(LoginPage);
	 WebElement Login_Name=driver.findElement(By.id("loginFrm_loginname"));
	 WebElement password=driver.findElement(By.id("loginFrm_password"));
	 WebElement LoginButton=driver.findElement(By.xpath("//button[@title='Login']"));
	 
	 Login_Name.sendKeys(loginnam);
	 password.sendKeys(pass);
	 Thread.sleep(2000);
	 LoginButton.click();
	 Thread.sleep(3000);
	 String WelcomeMessage="Welcome back "+CustomerFirstName;
	 boolean actualValue=driver.getPageSource().contains(WelcomeMessage);
	 boolean expectedValue=true;
	 Assert.assertEquals(actualValue, expectedValue);
	  
	}
	
	@Test(priority=8,enabled=true,invocationCount =2)
	public void AddItems() throws InterruptedException
	{
		driver.navigate().to(Website);
		for(int i=0;i<15;i++)
		{
		List <WebElement> AllItems= driver.findElements(By.className("prdocutname"));
		int RandomItem=rand.nextInt(AllItems.size());
		AllItems.get(RandomItem).click();
		boolean OutOfStockMessage=driver.getPageSource().contains("Out of Stock");
		boolean blockedProduct=driver.getCurrentUrl().contains("product_id=116");
		if(!OutOfStockMessage&&!blockedProduct)
	
		{
			WebElement AddToCartButton=driver.findElement(By.cssSelector(".cart"));
			AddToCartButton.click();
	        return;
		}
		
		driver.navigate().back();	
	    }
	
	}
	@Test(priority=9,enabled=true)
	public void UpdateItemsTest() throws InterruptedException
	{
		WebElement UpdateItemsCart=driver.findElement(By.cssSelector(".btn.btn-default.pull-right.mr10"));
		UpdateItemsCart.click();
	}
	
	@Test(priority=10,enabled=true)
	public void CheckOutTest() throws InterruptedException
	{
		WebElement CheckOutButton= driver.findElement(By.id("cart_checkout1"));
		CheckOutButton.click();
		WebElement ConfirmOrderButton= driver.findElement(By.id("checkout_btn"));
		ConfirmOrderButton.click();
		Thread.sleep(3000);
		//System.out.print(driver.getPageSource());
		String ActualMessage=driver.findElement(By.className("maintext")).getText();
		String ExpectedMessage ="YOUR ORDER HAS BEEN PROCESSED!";
		Assert.assertEquals(ActualMessage, ExpectedMessage);
	
	}
}
	
	
	
