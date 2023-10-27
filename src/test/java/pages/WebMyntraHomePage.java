package pages;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.ConfigReader;

import static listener.CucumberListener.extentTestThreadLocalTestStep;
import static listener.CucumberListener.takeScreenshotAsBase64WebLocal;
import static utilities.ThreadLocalDriver.getWebDriverThreadLocal;

public class WebMyntraHomePage {
    public WebMyntraHomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    /**
     * Mobile Elements
     */
    @FindBy(xpath = "//input[@class='desktop-searchBar']")
    WebElement searchbox;

    public WebElement getSearchbox() {
        return searchbox;
    }

    /**
     * Actions
     */
    public void userOnHomePage() {
        ConfigReader configReader = new ConfigReader();
        String url = System.getProperty("url", configReader.config().getProperty("WebUrl"));
        getWebDriverThreadLocal().get(url);
        extentTestThreadLocalTestStep.get().log(Status.INFO, "Home Page", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64WebLocal()).build());
    }

    public void enterBrandNameInSearchbox(String brand) {
        getSearchbox().sendKeys(brand, Keys.ENTER);
        extentTestThreadLocalTestStep.get().log(Status.INFO, "Brand details entered", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64WebLocal()).build());
    }

    public void verifyBrandIsDisplayed(String brand) throws InterruptedException {
        String xpath = "//h3[contains(text()," + brand + ")][1]";
        Thread.sleep(3000);
        Assert.assertTrue(getWebDriverThreadLocal().findElement(By.xpath(xpath)).isDisplayed());
        extentTestThreadLocalTestStep.get().log(Status.INFO, "Brand is displayed", MediaEntityBuilder.createScreenCaptureFromBase64String(takeScreenshotAsBase64WebLocal()).build());
    }
}
