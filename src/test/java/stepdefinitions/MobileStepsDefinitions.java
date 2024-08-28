package stepdefinitions;

import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.BasePage;
import pages.StepinMobilepage;
import pages.mobilePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class MobileStepsDefinitions extends BasePage{
	

//   @Given("I launch the app")
//   public void launchapp(){
//	   
//	   
//   }
//    @Then("I should see the {string} element")
//    public void iShouldSeeTheElement(By elementId) {
//    	mobilePage.VerifytheElement();
////        WebElement element = driver.findElement(elementId);
////        assertTrue(element.isDisplayed());
//    }
	
	@Given("I launch the app")
	public void i_launch_the_app() throws Exception {
	 mobilePage.launchmobile();
	   
	}

	@When("click the product button it entert to the application")
	public void click_the_product_button_it_entert_to_the_application() {
		StepinMobilepage.clickProductbtn();
	}

	@Then("Scroll to the product and get the details")
	public void scroll_to_the_product_and_get_the_details() {
		StepinMobilepage.scrollAndGetdetails();
		
	}

	@Then("Push those details to the backend")
	public void push_those_details_to_the_backend() {
		StepinMobilepage.writemethod();
	}



}