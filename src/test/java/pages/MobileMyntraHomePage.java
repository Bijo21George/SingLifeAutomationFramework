package pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import static listener.CucumberListener.*;
import static utilities.ThreadLocalDriver.getAppiumDriverThreadLocal;

public class MobileMyntraHomePage {
    public MobileMyntraHomePage(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Mobile Elements
     *//*
    @AndroidFindBy(xpath = "//*[@text='Studio']")
    @iOSXCUITFindBy(xpath = "//*[@text='Studio']")
    MobileElement studio;*/

    @AndroidFindBy(xpath = "//*[@class='android.view.ViewGroup']")
    MobileElement homepage;

    public MobileElement getHomePageLogo() {
        return homepage;
    }

    @AndroidFindBy(xpath = "(//*[@class='android.view.ViewGroup'])[24]")
    MobileElement menuItem;

    public MobileElement getMenuItem() {
        return menuItem;
    }

    @AndroidFindBy(xpath = "(//*[@class='android.widget.ImageView'])[8]")
    MobileElement searchIcon;

    public MobileElement getSearchIcon() {
        return searchIcon;
    }

    @AndroidFindBy(xpath = "//*[contains(@text,'Search for brands & products')]")
    MobileElement searchTextBox;

    public MobileElement getSearchTextBox() {
        return searchTextBox;
    }

    /**
     * Actions
     */
    public void userOnHomePage() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        Assert.assertTrue(getHomePageLogo().isDisplayed());
        extentTestThreadLocalTestStep.get().log(Status.INFO, "Clicked Studio", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Mobile()).build());
    }
  
    public void enterBrandNameInSearchbox(String brand) throws InterruptedException {
        getMenuItem().click();
            Thread.sleep(2000);
        getSearchIcon().click();
        Thread.sleep(2000);
        getSearchTextBox().sendKeys(brand);
            Thread.sleep(5000);
        extentTestThreadLocalTestStep.get().log(Status.INFO, "Clicked Studio", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Mobile()).build());
    }


  public void verifyBrandIsDisplayed(String brand) {
        String xpath="//*[contains(@text,"+brand+")]";
      Assert.assertTrue(getAppiumDriverThreadLocal().findElement(By.xpath(xpath)).isDisplayed());
      extentTestThreadLocalTestStep.get().log(Status.INFO, "Clicked Studio", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64Mobile()).build());
  }
}
