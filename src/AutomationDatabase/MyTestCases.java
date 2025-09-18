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
	WebDriver driver = new EdgeDriver();
	String Website="https://automationteststore.com/";
	String SignUpPage="https://automationteststore.com/index.php?rt=account/create";
	String LogoutPage="https://automationteststore.com/index.php?rt=account/logout";
	
	@BeforeTest
	
	public void SetUp() throws SQLException
	{
		driver.get(Website);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		driver.manage().window().maximize();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","0000");

    }
	
	@Test(priority=5,enabled=true)
	public void SignUpTest() throws InterruptedException, SQLException
	{
		driver.navigate().to(SignUpPage);
		WebElement TheFirstName= driver.findElement(By.id("AccountFrm_firstname"));
		WebElement TheLastName= driver.findElement(By.id("AccountFrm_lastname"));
		WebElement TheEmail=driver.findElement(By.id("AccountFrm_email"));
		WebElement Telephone = driver.findElement(By.id("AccountFrm_telephone"));
		WebElement Address1= driver.findElement(By.id("AccountFrm_address_1"));
		WebElement Address2=driver.findElement(By.id("AccountFrm_address_2"));
		//WebElement Country=driver.findElement(By.id("AccountFrm_country_id"));
		//WebElement State=driver.findElement(By.id("AccountFrm_zone_id"));
		//Select CountrySelection =new Select(Country);
		//Select StateSelection =new Select(State);
		//CountrySelection.selectByValue("country");
		//Thread.sleep(2000);
		//StateSelection.selectByValue("state");;
		//List <WebElement> AllStates = State.findElements(By.tagName("option"));
		//String TheCity = AllStates.get().getText();
		//WebElement City=driver.findElement(By.id("AccountFrm_city"));
		WebElement ZipCode = driver.findElement(By.id("AccountFrm_postcode"));
		//WebElement LoginName= driver.findElement(By.id("AccountFrm_loginname"));
		//WebElement Password =driver.findElement(By.id("AccountFrm_password"));
		//WebElement ConfirmPassword =driver.findElement(By.id("AccountFrm_confirm"));
		//WebElement AgreeBox= driver.findElement(By.id("AccountFrm_agree"));
		//WebElement ContinueButton= driver.findElement(By.cssSelector(".btn.btn-orange.pull-right.lock-on-click"));
		
		//-------   Actions ---------------
		//ReadData();
		TheFirstName.sendKeys(CustomerFirstName);
		TheLastName.sendKeys(CustomerLastName);
		TheEmail.sendKeys(Email);
		Telephone.sendKeys(PhoneNumber);
		Address1.sendKeys(AddLine1);
		Address2.sendKeys(AddLine2);
		
		
		
	    		
	}
	
	
	
	
}
	
	
	
