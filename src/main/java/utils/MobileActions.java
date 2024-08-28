package utils;

import java.time.Duration;

import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BasePage;

public class MobileActions extends BasePage {
	static Duration timeOut;
	 public static void waitForPageToLoad() {
	        
			WebDriverWait wait = new WebDriverWait(driver1, timeOut);
	        wait.pollingEvery(Duration.ofSeconds(30));
	    }

}
