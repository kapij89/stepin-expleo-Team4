package pages;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.BrowserType.LaunchOptions;

import utils.ConfigReader;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;



public abstract class BasePage {

	/**
	 * Page
	 */

	protected Browser browser;
	protected Page page;

	public Page createPlaywrightPageInstance(String browserTypeAsString) {
		BrowserType browserType = null;
		Playwright playwright = null;
		switch (browserTypeAsString) {
		case "Firefox":
			browserType = Playwright.create().firefox();
			break;
		case "Chromium":
			browserType = Playwright.create().chromium();
			break;
		case "Webkit":
			playwright = Playwright.create();
			break;
		case "Mobile":
			playwright = Playwright.create();
			break;

		}
		// if (browserType == null) {
		// throw new IllegalArgumentException("Could not launch a browser for
		// type " + browserTypeAsString);
		// }
		// browser = browserType.launch(new
		// BrowserType.LaunchOptions().setHeadless(false));
		// To Slow Down Execution
		if (ConfigReader.getProperty("platform").equalsIgnoreCase("Mobile")) {
			// playwright = Playwright.create();
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(true));
			BrowserContext context = browser.newContext(
					new Browser.NewContextOptions().setViewportSize(375, 812).setDeviceScaleFactor(2).setUserAgent(
							"Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0 Mobile/15E148 Safari/604.1"));
			page = context.newPage();
			// Emulate mobile user agent
			// page.setUserAgent("Mozilla/5.0 (iPhone; CPU iPhone OS 15_0 like
			// Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/15.0
			// Mobile/15E148 Safari/604.1");
		} else if (ConfigReader.getProperty("platform").equalsIgnoreCase("WebKit")) {
			 Browser browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(true));
	            BrowserContext context = browser.newContext(new Browser.NewContextOptions()
	                    .setViewportSize(1200, 800)
	                    .setUserAgent("Custom Webkit"));
	             page = context.newPage();
		}else{
			browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(true).setSlowMo(50));
			page = browser.newPage();
		}
		// Recording Trace for webSite
		// BrowserContext context = browser.newContext();
		// Start tracing before creating / navigating a page.

		// context.tracing().start(new Tracing.StartOptions()
		// .setScreenshots(true)
		// .setSnapshots(true));

		return page;

	}
	
	

}