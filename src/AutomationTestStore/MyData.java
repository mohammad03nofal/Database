package AutomationTestStore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyData {
	WebDriver driver = new EdgeDriver();
	String Website="https://automationteststore.com/";
	String SignUpPage="https://automationteststore.com/index.php?rt=account/create";
	String LogoutPage="https://automationteststore.com/index.php?rt=account/logout";
	Connection conn;
	Statement stmt;
	ResultSet rs;
	String CustomerFirstName;
	String CustomerLastName;
	String Email;
	String Password;
  
	@BeforeTest
	public void SetUp() throws SQLException
	{
		driver.get(SignUpPage);	
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));	
		driver.manage().window().maximize();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels","root","0000");
		
	}
	
	@Test(priority=1)
	public void AddRecord() throws SQLException
	{
		String query= "INSERT INTO customers (customerNumber,customerName,contactLastName,contactFirstName, phone, addressLine1,addressLine2,city,state,postalCode,country,salesRepEmployeeNumber, creditLimit) VALUES (500,'TechZone Ltd.','Nofal','Mohammad','+962798648671','123 Market Street','Suite 200','San Francisco','CA','94105','USA',1370,150000.00)";
		
		stmt=conn.createStatement();
		int rowInserted=stmt.executeUpdate(query);
		
		
	}
	
	@Test(priority=3)
	public void ReadData() throws SQLException
	{
		String query= "select * from customers where customerNumber=500";
		stmt=conn.createStatement();
		rs=stmt.executeQuery(query);
		
		while(rs.next())
		{
			int customerNumberInDatabase=rs.getInt("customerNumber");
		    CustomerFirstName=rs.getString("contactFirstName").toString().trim();
			CustomerLastName=rs.getString("contactLastName").toString().trim();
			Email=CustomerFirstName+CustomerLastName+"@gmail.com";
			Password="P@$sword";
			
			System.out.println(customerNumberInDatabase);
			System.out.println(CustomerFirstName);
			System.out.println(CustomerLastName);
			System.out.println(Email);
			System.out.println(Password);
			System.out.println(Password);
		}
		driver.findElement(By.id("AccountFrm_firstname")).sendKeys(CustomerFirstName);
		driver.findElement(By.id("AccountFrm_lastname")).sendKeys(CustomerLastName);
		driver.findElement(By.id("AccountFrm_email")).sendKeys(Email);
		
		
	}
	
	@Test(priority=2)
	public void UpdateData() throws SQLException
	{
		String query= "update customers set contactFirstName='Mazen'where customerNumber=500";
		stmt=conn.createStatement();
		int updatedrow=stmt.executeUpdate(query);
		
		
	}
	
	@Test(priority=4)
	public void DeleteData() throws SQLException
	{
		String query= "delete from customers where customerNumber=500";
		stmt=conn.createStatement();
		int deletedrow=stmt.executeUpdate(query);
	}
}
