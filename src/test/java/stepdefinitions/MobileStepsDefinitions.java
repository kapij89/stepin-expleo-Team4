package stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import org.openqa.selenium.remote.DesiredCapabilities;

//import cucumber.api.java.en.*;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertTrue;

public class MobileStepsDefinitions {

    private AppiumDriver<MobileElement> driver;

    @Given("I launch the app")
    public void iLaunchTheApp() throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "emulator-5554");
        capabilities.setCapability(MobileCapabilityType.APP, "path/to/your/app.apk");

        URL appiumServerURL = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(appiumServerURL, capabilities);
    }

    @Then("I should see the {string} element")
    public void iShouldSeeTheElement(String elementId) {
        MobileElement element = driver.findElementById(elementId);
        assertTrue(element.isDisplayed());
    }
}