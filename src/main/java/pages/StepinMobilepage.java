package pages;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.microsoft.playwright.Browser;
//import com.microsoft.playwright.Page;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import utils.MobileActions;


public class StepinMobilepage extends BasePage {
    
    	
	static TouchAction action = new TouchAction((PerformsTouchActions) driver1);

    	static String productbtn="android.widget.Button";
    	static String productname="(//android.widget.TextView[@index=0])[1]";
    	static String productname2="//android.widget.TextView[@text=\"Mi Fitness Tracker\"]";
    	static String productdescription="(//android.widget.TextView[@index=1])[1]";
    	
    	static String productprice ="(//android.widget.TextView[@index=2])[1]";
    
    	static WebElement webele=null;
    	
    	
    	
	
	public static void launchmobile() throws Exception {
		
	 BasePage.iLaunchTheApp();
		
	}
	
	
	public static void clickProductbtn() {
		WebDriverWait wait = new WebDriverWait(driver1, Duration.ofSeconds(10));
    	
		 WebElement product = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(productbtn)));
		
		 product.click();
	}

	public static String[] scrollAndGetdetails() {
		
		WebElement productname1=driver1.findElement(By.xpath(productname));
		String productna=productname1.getAttribute("text");
		System.out.println(productna);
		WebElement productdescr1=driver1.findElement(By.xpath(productdescription));
		String productdes=productdescr1.getAttribute("text");
		System.out.println(productdes);
		WebElement productpric=driver1.findElement(By.xpath(productprice));
		String productpr=productname1.getAttribute("text");
		System.out.println(productpr);
//		webele=driver1.findElement(By.xpath(productname2));
//		try {
//		while(!webele.isDisplayed()) {
//		action.press(PointOption.point(500, 1000))  // Start point (x, y)
//	      .waitAction(WaitOptions.waitOptions(Duration.ofMillis(2000)))
//	      .moveTo(PointOption.point(500, 500))  // End point (x, y)
//	      .release()
//	      .perform();
//		}
//		}
//		catch(Exception e) {
//			
//		}
		String arr[] =new String[3];
		arr[0]=productna;
		arr[1]=productdes;
		arr[2]=productpr;
     return arr;
		
	}
	public static void writemethod() {
		String arr[] =new String[3];
		arr=scrollAndGetdetails();
		 ObjectMapper objectMapper = new ObjectMapper();

	        try {
	            // Read the JSON file into the Item object
	            Item item = objectMapper.readValue(new File("src\\test\\resources\\new_mobile.json"), Item.class);

	            // Modify the values
	            item.setName(arr[0]);
	            item.setDescription(arr[1]);
	            try {
	                // Check if the string can be parsed to a long
	                if (isNumeric(arr[2])) {
	                    long price = Long.parseLong(arr[2]);
	                    item.setPrice(price);
	                    System.out.println("Price set to: " + item.getPrice());
	                } else {
	                    System.out.println("The value is not a valid number.");
	                }
	            } catch (NumberFormatException e) {
	                System.err.println("Invalid number format: " + e.getMessage());
	            }
	         // New price
	            item.setItem_type("New Item Type");

	            // Write the modified Item object back to the JSON file
	            objectMapper.writeValue(new File("src\\test\\resources\\new_mobile.json"), item);

	            System.out.println("JSON file modified successfully.");

	        }catch (Exception e) {
		
	        }
		
	}


	 private static boolean isNumeric(String str) {
	        if (str == null) {
	            return false;
	        }
	        try {
	            Long.parseLong(str);
	            return true;
	        } catch (NumberFormatException e) {
	            return false;
	        }
	    }
			 
	
	
	
	
	
}
