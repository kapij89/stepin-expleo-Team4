package stepdefinitions;

import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

//import cucumber.api.java.en.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class MobileStepsDefinitions {

    public AppiumDriver driver;

    @Given("I launch the app")
    public void iLaunchTheApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "eec1e9e8");
//        capabilities.setCapability(MobileCapabilityType.APP, "");
        capabilities.setCapability("appPackage", "com.instagram.android");
        capabilities.setCapability("appActivity", ".activity.MainTabActivity");

        URI appiumServerURI = URI.create("http://localhost:4723/wd/hub");
        URL appiumServerURL = appiumServerURI.toURL();
        driver = new AndroidDriver(appiumServerURL, capabilities);

    }

    @Then("I should see the {string} element")
    public void iShouldSeeTheElement(By elementId) {
        WebElement element = driver.findElement(elementId);
        assertTrue(element.isDisplayed());
    }
}