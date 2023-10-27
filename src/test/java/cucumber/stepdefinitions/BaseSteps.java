package cucumber.stepdefinitions;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import pages.WebMyntraHomePage;
import pages.MobileMyntraHomePage;

/**
 * this class is used to initialize page classes with driver.
 */
public class BaseSteps {
  protected MobileMyntraHomePage mobileMyntraHomePage;
  protected WebMyntraHomePage webMyntraHomePage;

  public void setupScreensMobile(AppiumDriver<MobileElement> driver) {
    mobileMyntraHomePage = new MobileMyntraHomePage(driver);
  }

  public void setupScreensWebLocal(WebDriver driver) {
    webMyntraHomePage = new WebMyntraHomePage(driver);
  }
}