package pages;

import java.io.IOException;
import java.net.URI;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public abstract class BasePage {

    protected static final Logger LOG = LoggerFactory.getLogger(BasePage.class);
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    protected String platform = System.getProperty("os.name");
    protected String browser = System.getProperty("browser", "chrome");
    protected String driverPath;
    public static AppiumDriver driver1;
//	protected Browser browser;
//	protected Page page;
	 static URI appiumServerURI=null;
	static URL appiumServerURL=null;
	static DesiredCapabilities capabilities=null;
    protected WebDriver getDriver() {
        if (driver.get() == null) {
            createNewDriverInstance();
        }
        return driver.get();
    }

    private void createNewDriverInstance() {
        try {
            LOG.info("OS is: " + platform);

            switch (browser.toLowerCase()) {
                case "chrome":
                    setupChromeDriver();
                    break;

                case "firefox":
                    setupFirefoxDriver();
                    break;

                case "edge":
                    setupEdgeDriver();
                    break;

                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        } catch (Exception e) {
            LOG.error("Error initializing WebDriver", e);
            throw e;
        }
    }

    private void setupChromeDriver() {
        try {
            driverPath =System.getProperty("user.dir") + "\\drivers\\chromedriver.exe";
            LOG.info("Driver Path: " + driverPath);
            System.setProperty("webdriver.chrome.driver", driverPath);

            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--incognito");
            chromeOptions.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            chromeOptions.addArguments("--remote-allow-origins=*");
            chromeOptions.addArguments("--ignore-ssl-errors=yes");
            chromeOptions.addArguments("--ignore-certificate-errors");
            chromeOptions.addArguments("start-maximized");

            driver.set(new ChromeDriver(chromeOptions));
        } catch (Exception e) {
            LOG.error("Error setting up Chrome driver", e);
            throw e;
        }
    }

    private void setupFirefoxDriver() {
        try {
            driverPath = platform.contains("Windows") ? System.getProperty("user.dir") + "\\drivers\\" + "geckodriver.exe" : System.getProperty("user.dir") + "/drivers/" + "geckodriver";
            LOG.info("Driver Path: " + driverPath);
            System.setProperty("webdriver.gecko.driver", driverPath);

            driver.set(new FirefoxDriver());
        } catch (Exception e) {
            LOG.error("Error setting up Firefox driver", e);
            throw e;
        }
    }

    private void setupEdgeDriver() {
        try {
            driverPath = platform.contains("Windows") ? System.getProperty("user.dir") + "\\drivers\\" + "msedgedriver.exe" : System.getProperty("user.dir") + "/drivers/" + "msedgedriver";
            LOG.info("Driver Path: " + driverPath);
            System.setProperty("webdriver.edge.driver", driverPath);

            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--no-sandbox");
            edgeOptions.addArguments("--headless");
            edgeOptions.addArguments("start-maximized");
            edgeOptions.addArguments("--disable-extensions");
            edgeOptions.addArguments("--disable-dev-shm-usage");

            driver.set(new EdgeDriver(edgeOptions));
        } catch (Exception e) {
            LOG.error("Error setting up Edge driver", e);
            throw e;
        }
    }

    public void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
    public static void iLaunchTheApp() throws Exception {
    	String imagepath="C:\\Users\\PRIYA\\git\\stepin-expleo-Team4\\src\\test\\resources\\Image\\Testimage.jpg";
    	String deviceImagePath="/storage/emulated/0/Pictures";
		ProcessBuilder pb = new ProcessBuilder("adb", "push", imagepath, deviceImagePath);
		
		try {
            // Start the process
            Process process = pb.start();

            // Wait for the process to complete
            int exitCode = process.waitFor();
            ProcessBuilder scanBuilder = new ProcessBuilder("adb", "shell", "am", "broadcast",
                    "-a", "android.intent.action.MEDIA_SCANNER_SCAN_FILE",
                    "-d", "file://" + deviceImagePath);

            // Start the process
            Process scanProcess = scanBuilder.start();

            // Wait for the process to complete
            int scanExitCode = scanProcess.waitFor();

            // Check if the push command was successful
            if (exitCode == 0) {
                System.out.println("Image transferred successfully!");
            } else {
                System.err.println("Failed to transfer image. Exit code: " + exitCode);
            }

        } catch (Exception e) {
           
        }
        capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "RZ8R32QJCAW");
        capabilities.setCapability("appPackage", "com.dharma.stepin");
        capabilities.setCapability("appActivity", "com.dharma.stepin.MainActivity");
        capabilities.setCapability("noReset", true);
        capabilities.setCapability("fullReset", false);
         appiumServerURI = URI.create("http://localhost:4723/wd/hub");
         appiumServerURL = appiumServerURI.toURL();
         driver1 = new AndroidDriver(appiumServerURL, capabilities);
	       
    }

}
