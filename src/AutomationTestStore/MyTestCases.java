package AutomationTestStore;

import static org.testng.Assert.assertEquals;

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
	WebDriver driver = new EdgeDriver();
	String Website="https://automationteststore.com/";
	String SignUpPage="https://automationteststore.com/index.php?rt=account/create";
	String LogoutPage="https://automationteststore.com/index.php?rt=account/logout";
	
	@BeforeTest
	
	public void SetUp()
	{
		driver.get(Website);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		driver.manage().window().maximize();

    }
	
	@Test(priority=1,enabled=false)
	public void SignUpTest() throws InterruptedException
	{
		driver.navigate().to(SignUpPage);
		WebElement TheFirstName= driver.findElement(By.id("AccountFrm_firstname"));
		WebElement TheLastName= driver.findElement(By.id("AccountFrm_lastname"));
		WebElement TheEmail=driver.findElement(By.id("AccountFrm_email"));
		WebElement Telephone = driver.findElement(By.id("AccountFrm_telephone"));
		WebElement Address1= driver.findElement(By.id("AccountFrm_address_1"));
		WebElement Country=driver.findElement(By.id("AccountFrm_country_id"));
		WebElement State=driver.findElement(By.id("AccountFrm_zone_id"));
		Select CountrySelection =new Select(Country);
		Select StateSelection =new Select(State);
		CountrySelection.selectByIndex(108);
		Thread.sleep(2000);
		StateSelection.selectByIndex(RandomStateSelectIndex);
		List <WebElement> AllStates = State.findElements(By.tagName("option"));
		String TheCity = AllStates.get(RandomStateSelectIndex).getText();
		WebElement City=driver.findElement(By.id("AccountFrm_city"));
		WebElement ZipCode = driver.findElement(By.id("AccountFrm_postcode"));
		WebElement LoginName= driver.findElement(By.id("AccountFrm_loginname"));
		WebElement Password =driver.findElement(By.id("AccountFrm_password"));
		WebElement ConfirmPassword =driver.findElement(By.id("AccountFrm_confirm"));
		WebElement AgreeBox= driver.findElement(By.id("AccountFrm_agree"));
		WebElement ContinueButton= driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
		//-------   Actions ---------------
		TheFirstName.sendKeys(FirstName);	
		TheLastName.sendKeys(LastName);
		TheEmail.sendKeys(Email);
		Telephone.sendKeys(TheTelephone);
		Address1.sendKeys(TheAddressOne);
        City.sendKeys(TheCity);
	    ZipCode.sendKeys(TheZipCode);
	    LoginName.sendKeys(loginName);
	    Password.sendKeys(password);
	    ConfirmPassword.sendKeys(password);
	    AgreeBox.click();
	    ContinueButton.click();
	    Thread.sleep(4000);
	    String ActualResult= driver.findElement(By.className("maintext")).getText();
	    Assert.assertEquals(ActualResult, ExpectedResult);
	    		
	}
	
	@Test(priority=2,enabled=false)
	
	public void logOutTest() throws InterruptedException
	{
		driver.navigate().to(LogoutPage);
		Thread.sleep(3000);
		 String ActualResult2= driver.findElement(By.className("maintext")).getText();
		Assert.assertEquals(ActualResult2, ExpectedResult2);
	}
	
@Test(priority=3,enabled=false)
	
	public void LogIn() throws InterruptedException
   
{
	   driver.findElement(By.linkText("Login or register")).click();
	   WebElement LoginName= driver.findElement(By.id("loginFrm_loginname"));
	   WebElement LoginPassword=driver.findElement(By.id("loginFrm_password"));
	   WebElement LoginButton=driver.findElement(By.xpath("//button[@title='Login']"));
	   LoginName.sendKeys(loginName);
	   LoginPassword.sendKeys(password);
	   Thread.sleep(4000);
	   LoginButton.click();
	   Thread.sleep(2000);
	   String WelcomeMessage="Welcome back "+FirstName;
	   boolean actualValue=driver.getPageSource().contains(WelcomeMessage);
	   Assert.assertEquals(actualValue, true);
	}

@Test(priority=4,invocationCount = 4)

public void AddItems()
{
	driver.navigate().to(Website);
	for(int i=0;i<10;i++)//10 attempts
	{
		List <WebElement> AllItems= driver.findElements(By.className("prdocutname"));
		int randomItem=rand.nextInt(AllItems.size());
		AllItems.get(randomItem).click();
		boolean OutOfStock=driver.getPageSource().contains("Out of Stock");
		boolean blockedproduct=driver.getCurrentUrl().contains("product_id=116");
		
		
	if(!OutOfStock||!blockedproduct)
	{
		WebElement AddItemButton=driver.findElement(By.cssSelector(".cart"));
		AddItemButton.click();
		return;
		//List <WebElement> AlternativeItem= driver.findElements(By.className("prdocutname"));
		//int randomAlternativeItem=rand.nextInt(AlternativeItem.size());
		//AlternativeItem.get(randomAlternativeItem).click();

	}
	 driver.navigate().back();
	}
}
	
	
	@AfterTest
	public void AfterMyTest()
	{
		
		//driver.quit();
	}
	
	
}
	
	
	
