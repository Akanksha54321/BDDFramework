package stepDefinitions;


import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import pageObjects.AddcustomerPage;
import pageObjects.LoginPage;
//import pageObjects.SearchCustomerPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Steps extends BaseClass{
	
	public WebDriver driver;    //create webdriver variable
//	public LoginPage lp;        // Object of Login Page class Page Object
//	public AddcustomerPage addCust;        // Object of AddcustomerPage Page class Page Object
	@Given("User Launch Chrome browser")
	public void user_Launch_Chrome_browser() {
	System.setProperty("webdriver.chrome.driver",".\\Drivers\\chromedriver.exe");
	driver= new ChromeDriver();
	   lp=new LoginPage(driver); 
	}

	@When("User opens URL {string}")
	public void user_opens_URL(String url) {
		driver.get(url);
	    driver.manage().window().maximize(); 
	}

	@When("User enters Email as {string} and Password as {string}")
	public void user_enters_Email_as_and_Password_as(String uname, String pwd) {
		lp.setUserName(uname);
		lp.setPassword(pwd);
	}

	@When("Click on Login")
	public void click_on_Login() throws InterruptedException {
		lp.clickLogin();
	    Thread.sleep(5000); 
	}

	@Then("Page Title should be {string}")
	public void page_Title_should_be(String exptitle) throws InterruptedException {
		 
       if(driver.getPageSource().contains("Login was unsuccessful"))
       {
         
          driver.close();
          Assert.assertTrue(false);
       }
       else
       {
          
           Assert.assertEquals(exptitle, driver.getTitle());
       }
       Thread.sleep(5000);
	}

	@When("User click on Log out link")
	public void user_click_on_Log_out_link() {
		lp.clickLogout();
	}

	@Then("close browser")
	public void close_browser() {
	//	driver.quit(); 
	}
	@Then("User can view Dashboad")
	public void User_can_view_Dashboad() {
		addCust=new AddcustomerPage(driver);
		Assert.assertEquals("Dashboard / nopCommerce administration",addCust.getPageTitle());
	}
	@When("User click on customers Menu")
	public void user_click_on_customers_Menu() {
		addCust.clickOnCustomersMenu();
	}

	@When("click on customers Menu Item")
	public void click_on_customers_Menu_Item() throws InterruptedException {
		Thread.sleep(2000);
        addCust.clickOnCustomersMenuItem();
	}

	@When("click on Add new button")
	public void click_on_Add_new_button() {
		addCust.clickOnAddnew();
	}

	@Then("User can view Add new customer page")
	public void user_can_view_Add_new_customer_page() throws InterruptedException {
		Assert.assertEquals("Add a new customer / nopCommerce administration", addCust.getPageTitle());
	}

	@When("User enter customer info")
	public void user_enter_customer_info() throws InterruptedException {
		  String email = randomestring() + "@gmail.com";
	        addCust.setEmail(email);
	        addCust.setPassword("test123");
	        // Registered - default
	        // The customer cannot be in both 'Guests' and 'Registered' customer roles
	        // Add the customer to 'Guests' or 'Registered' customer role
	        addCust.setCustomerRoles("Guest");
	        Thread.sleep(3000);

	        addCust.setManagerOfVendor("Vendor 2");
	        addCust.setGender("Male");
	        addCust.setFirstName("Pavan");
	        addCust.setLastName("Kumar");
	        addCust.setDob("7/05/1985"); // Format: D/MM/YYY
	        addCust.setCompanyName("busyQA");
	        addCust.setAdminContent("This is for testing.........");
	}

	@When("click on Save button")
	public void click_on_Save_button() {
		addCust.clickOnSave();
	}

	@Then("User can view confirmation message {string}")
	public void user_can_view_confirmation_message(String string) {
		  Assert.assertTrue(driver.findElement(By.tagName("body")).getText()
	                .contains("The new customer has been added successfully"));
	}


}
