package pages;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;


import io.appium.java_client.AppiumDriver;
import utils.MobileActions;


public class mobilePage extends BasePage {
    
    	
    	static String logo="//android.widget.ImageView[@content-desc=\"Instagram from Meta\"]";
    	static String Usernametxt="(//android.widget.EditText[@index=2])[1]";
    	static String passwordtxt="(//android.widget.EditText[@index=2])[2]";
    	static String loginbtn="//android.view.View[@content-desc=\"Log in\"]";
    	static String addposticon="//android.widget.FrameLayout[@content-desc=\"Create\"]";
    	static String selectimage ="(//android.widget.ImageView[@resource-id=\"com.instagram.android:id/gallery_picker_grid_item_background\"])[1]";
    	static String nextbtn ="//android.widget.Button[@content-desc=\"Next\"]";
    	static String nextbtn1="//android.widget.TextView[@text=\"Next\"]";
    	static String sharebtn="//android.widget.Button[@resource-id=\"com.instagram.android:id/share_footer_button\"]";
    	static String caption= "//android.widget.AutoCompleteTextView[@resource-id=\"com.instagram.android:id/caption_text_view\"]";
    	static WebElement webele=null;
    	static String userimage="//android.widget.ImageView[@resource-id=\"com.instagram.android:id/tab_avatar\"]";
    	static String postlink="//android.widget.Button[@content-desc=\"Photo by priya at Row 1, Column 1\"]";
    	static String imageScreenshot="//com.instagram.ui.widget.textview.IgTextLayoutView[@text=\"stepin_hope Automated post from #Testing! Tagging @stepand @verity.\"]";
    	
    	
    	
	public static void VerifytheElement() {
		driver1.findElement(By.xpath("//android.widget.Button[@resource-id=\"com.google.android.gms:id/cancel\"]")).click();
	    	MobileActions.waitForPageToLoad();
	    	WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
	    	WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(logo)));
		
		 
		 assertTrue(element.isDisplayed());
		
	}
	public static void launchmobile() throws Exception {
		
	 BasePage.iLaunchTheApp();
		
	}
	public static void Enter_Username_pwd(String username, String password) {
		 WebElement Username = driver1.findElement(By.xpath(Usernametxt));
		 Username.sendKeys(username);
		 WebElement passwordele = driver1.findElement(By.xpath(passwordtxt));
		 passwordele.sendKeys(password);
		 
		
	}
	public static void clickloginbtn() {

		 WebElement login = driver1.findElement(By.xpath(loginbtn));
		 login.click();
		
	}
	public static void Clickcreatepost() {
		
		webele=driver1.findElement(By.xpath(addposticon));
		webele.click();
	}
	public static void Selectimage() throws IOException, InterruptedException {
		
		webele=driver1.findElement(By.xpath(selectimage));
		webele.click();
		webele=driver1.findElement(By.xpath(nextbtn));
		webele.click();
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
		webele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(nextbtn1)));
		webele.click();
		webele=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(caption)));
		webele.sendKeys("Automated post from #Testing! Tagging @stepand @verity.");
		
		
	}
	public static void postimage() throws IOException {
//		
//		webele=driver.findElement(By.xpath(sharebtn));
//		webele.click();
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
		WebElement userimage1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(userimage)));
		userimage1.click();
		 userimage1=driver1.findElement(By.xpath(postlink));
		 
		 userimage1.click();
		 try {
		userimage1=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(imageScreenshot)));;
		  File screenshot = driver1.getScreenshotAs(OutputType.FILE);
		  File destinationFile = new File("C:\\Users\\PRIYA\\git\\stepin-expleo-Team4\\src\\test\\resources\\Image\\screenshot.png");
	        FileUtils.copyFile(screenshot, destinationFile);
		 }
		 catch (IOException e) {
	            e.printStackTrace();
	            System.err.println("Failed to save screenshot.");
	        }
	}
	
	
	
	
}
