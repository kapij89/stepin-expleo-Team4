package stepdefinitions;


import java.util.Set;

import org.junit.After;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

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
//			page = createPlaywrightPageInstance(ConfigReader.getProperty("browser"));
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
//			page = createPlaywrightPageInstance(ConfigReader.getProperty("browser"));
			page.navigate(ConfigReader.getProperty("twitterURL"));
			twitterlogin =new twitterloginandtweet(page);
		
			twitterlogin.twitterlogin(ConfigReader.getProperty("twitterusername"), "Redapple@123");
		    
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Given("^User launches IndianExpress$")
    public void user_launched_indianexpress() {
        captureScreenshot = true; // Assuming this is a member variable for capturing screenshots

        try {
            WebDriver driver = getDriver(); // Get WebDriver instance from BasePage

            // Navigate to Indian Express website
            driver.navigate().to("https://indianexpress.com/");

            // Optional: Sleep for demonstration (not recommended for real tests)
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	@When("User click on india linklabel")
	public void navigate_business_links() throws InterruptedException {
//		 WebDriver driver = getDriver(); // Get WebDriver instance from BasePage
		navigationonindianexpress("India");
		Thread.sleep(5000);
		navigationonindianexpress("Business");
		Thread.sleep(5000);
//		navigationonindianexpress("Sports");
		 
        
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
        WebDriver driver = getDriver();
        if (driver != null && scenario.isFailed()) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "screenshot.png");
            } catch (Exception e) {
                System.out.println("Failed to capture screenshot: " + e.getMessage());
            }
        }
    }
	
	
	public void navigationonindianexpress(String linklabel) throws InterruptedException {
		 WebDriver driver = getDriver(); // Get WebDriver instance from BasePage
		 // Business carousel flow
       driver.findElement(By.xpath("//a[@data-ie-event-action='Open Nav'] [@data-ie-event-label='"+linklabel+"']")).click();
    // Get all window handles
       Set<String> windowHandles = driver.getWindowHandles();
       // Switch to new browser tab/window if necessary
       // Assuming new tab/window handling is needed here
    // Iterate through handles
       for (String handle : windowHandles) {
           // Switch to the new window/tab
           driver.switchTo().window(handle);
       }
       // Now you can interact with elements on the new window/tab
       // Example: Get current URL of the new tab
       String currentUrl = driver.getCurrentUrl();
       System.out.println("Current URL of new tab: " + currentUrl);  	
       Thread.sleep(2000);
       if (linklabel.equalsIgnoreCase("Sports")) {
    	   driver.findElement(By.xpath("//*[@class='slide-m-dots']/ul/li[1]")).click();
       }else {
    	   driver.findElement(By.xpath("//*[@aria-label = '1 of 5']")).click(); 
       }
       Thread.sleep(2000);
//       if (linklabel.equalsIgnoreCase("Sports")) {
//    	   driver.findElement(By.xpath("//*[@class='slide-m-dots']/ul/li[1]")).click();
//       }else {
//    	   driver.findElement(By.xpath("//*[@aria-label = '1 of 5']")).click(); 
//       }
//      
    	 
    	   
       String news1Headline = driver.findElement(By.xpath("//li[contains(@class,'slick-slide slick-current slick-active')]")).getText();
       System.out.println(news1Headline);
       String newshyperlink = driver.findElement(By.xpath("//li[contains(@class,'slick-slide slick-current slick-active')]/a")).getAttribute("href");
//       String news1link = driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).getAttribute("alt");
       System.out.println(newshyperlink);
       driver.navigate().to(newshyperlink);
//       driver.findElement(By.xpath("//*[@id=\"slick-slide10\"]/a")).click();
//       System.out.println(news1link);
       
//       String news1link = driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).getAttribute("alt");
//       System.out.println(news1link);
//       driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).click();
       String news1DateTime = driver.findElement(By.xpath("//div[@id='storycenterbyline']/div/span")).getText();
       System.out.println(news1DateTime);
       driver.navigate().back();
       Thread.sleep(5000);
       //second
       Thread.sleep(2000);
       driver.findElement(By.xpath("//*[@aria-label = '2 of 5']")).click();
       
        news1Headline = driver.findElement(By.xpath("//li[contains(@class,'slick-slide slick-current slick-active')]")).getText();
       System.out.println(news1Headline);
        newshyperlink = driver.findElement(By.xpath("//li[contains(@class,'slick-slide slick-current slick-active')]/a")).getAttribute("href");
//       String news1link = driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).getAttribute("alt");
       System.out.println(newshyperlink);
       driver.navigate().to(newshyperlink);
//       driver.findElement(By.xpath("//*[@id=\"slick-slide10\"]/a")).click();
//       System.out.println(news1link);
       
//       String news1link = driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).getAttribute("alt");
//       System.out.println(news1link);
//       driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).click();
        news1DateTime = driver.findElement(By.xpath("//div[@id='storycenterbyline']/div/span")).getText();
       System.out.println(news1DateTime);
       driver.navigate().back();
       Thread.sleep(5000);
       //thrid corosul
       Thread.sleep(3000);
       driver.findElement(By.xpath("//*[@aria-label = '3 of 5']")).click();
//       driver.findElement(By.xpath("//*[@aria-label = '3 of 5']")).click();
       Thread.sleep(2000);
        news1Headline = driver.findElement(By.xpath("//li[contains(@class,'slick-slide slick-current slick-active')]")).getText();
       System.out.println(news1Headline);
        newshyperlink = driver.findElement(By.xpath("//li[contains(@class,'slick-slide slick-current slick-active')]/a")).getAttribute("href");
//       String news1link = driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).getAttribute("alt");
       System.out.println(newshyperlink);
       driver.navigate().to(newshyperlink);
//       driver.findElement(By.xpath("//*[@id=\"slick-slide10\"]/a")).click();
//       System.out.println(news1link);
       
//       String news1link = driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).getAttribute("alt");
//       System.out.println(news1link);
//       driver.findElement(By.xpath("//div[@class='slick-track']/li[2]/a/figure/img")).click();
        news1DateTime = driver.findElement(By.xpath("//div[@id='storycenterbyline']/div/span")).getText();
       System.out.println(news1DateTime);
       driver.navigate().back();
//       driver.navigate().refresh();
       Thread.sleep(5000);
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
//			browser.close();
		}
		if (page != null) {
			page.close();
		}
	}
}
