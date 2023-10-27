package cucumber.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Reporter;

import static utilities.ThreadLocalDriver.getAppiumDriverThreadLocal;
import static utilities.ThreadLocalDriver.getWebDriverThreadLocal;

/**
 * this class contains java implementation for steps in feature files
 */
public class MyntraHomePageStepDefinitions extends BaseSteps {

  @Before
  public void setupLoginSteps() {
    if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("Mobile")) {
      setupScreensMobile(getAppiumDriverThreadLocal());
    } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("WebLocal")){
      setupScreensWebLocal(getWebDriverThreadLocal());
    }
  }

  @Given("User is on home page")
  public void userisonhomepageandtapsCategories() {
    if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("Mobile")) {
      mobileMyntraHomePage.userOnHomePage();
    } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("WebLocal")){
      webMyntraHomePage.userOnHomePage();
    }
  }

  @When("User searches {string}")
  public void userSearches(String brand) throws InterruptedException {
    if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("Mobile")){
      mobileMyntraHomePage.enterBrandNameInSearchbox(brand);
    } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("WebLocal")){
      webMyntraHomePage.enterBrandNameInSearchbox(brand);
    }
  }

  @Then("{string} is displayed")
  public void isDisplayed(String brand) throws InterruptedException {
    if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("Mobile")){
      mobileMyntraHomePage.verifyBrandIsDisplayed(brand);
    } else if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("WebLocal")){
      webMyntraHomePage.verifyBrandIsDisplayed(brand);
    }
  }
}
