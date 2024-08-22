package stepdefinitions;


import org.junit.After;

import com.microsoft.playwright.Page;

import io.cucumber.java.AfterStep;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pages.BasePage;
import pages.CheckoutPage;
import pages.ItemsPage;
import pages.LoginPage;
import pages.twitterloginandtweet;
import utils.ConfigReader;
//import com.epam.reportportal.example.cucumber6.Belly;

public class UIStepDefinitions extends BasePage{
	
//	private final Belly belly = new Belly();
	LoginPage loginPage;
	ItemsPage itemsPage;
	CheckoutPage checkoutPage;
	pages.twitterloginandtweet twitterlogin;
	  boolean captureScreenshot;
	Page page;

	@Given("^User launched SwagLabs application$")
	public void user_launched_swaglabs_application() {
		 captureScreenshot = true;
		try {
//			BrowserContext context = browser.newContext();
//			context.tracing().start(new Tracing.StartOptions()
//					  .setScreenshots(true)
//					  .setSnapshots(true));
			page = createPlaywrightPageInstance(ConfigReader.getProperty("browser"));
			page.navigate(ConfigReader.getProperty("applicationUrl"));
			
			loginPage = new LoginPage(page);
			itemsPage = new ItemsPage(page);
			checkoutPage = new CheckoutPage(page);
		    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Given("^User launched Twitter$")
	public void user_launched_twitter() {
		 captureScreenshot = true;
		try {
//			BrowserContext context = browser.newContext();
//			context.tracing().start(new Tracing.StartOptions()
//					  .setScreenshots(true)
//					  .setSnapshots(true));
			page = createPlaywrightPageInstance(ConfigReader.getProperty("browser"));
			page.navigate(ConfigReader.getProperty("twitterURL"));
			twitterlogin =new twitterloginandtweet(page);
		
			twitterlogin.twitterlogin(ConfigReader.getProperty("twitterusername"), "Redapple@123");
		    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@And("User Tweets TestAutothon {string} over UI {string}")
	public void tweet_over_twitter(String unique_text,String Post) throws InterruptedException{
		twitterlogin =new pages.twitterloginandtweet(page);
		twitterlogin.tweet(unique_text,Post);
	}
	
	
	@When("User logged in the app using username {string} and password {string}")
	public void user_logged_in_the_app_using_username_and_password(String username, String password) {
		loginPage.login(username, password);
	}

	@Then("^user should be able to log in$")
	public void logInSuccessful() {
		itemsPage.loginSuccessful();
	}

	@Then("^User should not get logged in$")
	public void logInFailed() {
		loginPage.loginFailed();
	}

	@When("User adds {string} product to the cart")
	public void user_adds_product_to_the_cart(String product) {
        itemsPage.orderProduct(product);
	}

	@When("User enters Checkout details with {string}, {string}, {string}")
	public void user_enters_Checkout_details_with(String FirstName, String LastName, String Zipcode) {
		checkoutPage.fillCheckoutDetails(FirstName, LastName, Zipcode);
	}
	
	@When("User completes Checkout process")
	public void user_completes_checkout_process() {
         checkoutPage.completeCheckout();
	}

	@Then("User should get the Confirmation of Order")
	public void user_should_get_the_Confirmation_of_Order() {
         checkoutPage.checkoutSuccessful();
	}
	
//	@Before
//    public void setUpReportPortal() {
//        ScenarioReporter.class.
//    }
	@AfterStep
	public void captureScreenshotAfterStep(Scenario scenario) {
	    // Capture screenshot
	    // Page page = browser.newPage();
	    if (captureScreenshot) {
	        byte[] screenshot = page.screenshot(new Page.ScreenshotOptions());
	        scenario.attach(screenshot, "image/png", "screenshot.png");
	        // page.close();
	    }
	}
	
	@After
	public void tearDown(Scenario scenario) {
//		  try {
//	            byte[] screenshot = page.screenshot(new Page.ScreenshotOptions());
//	            scenario.attach(screenshot, "image/png", "screenshot.png");
//	        } catch (Exception e) {
//	            e.printStackTrace();
//	        }
		if (browser != null) {
			browser.close();
		}
		if (page != null) {
			page.close();
		}
	}
}
