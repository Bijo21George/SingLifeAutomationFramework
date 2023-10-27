package cucumber.testRunners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Reporter;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.DesiredCapabilitiesUtil;
import utilities.ThreadLocalDriver;

import java.io.IOException;
import java.net.URL;

/**
 * This class uses multithreading to run testRunners parallel
 */
@CucumberOptions(
        monochrome = true,
        tags = "@ScenarioTag1",
        features = "src/test/java/cucumber/features",
        glue = "cucumber.stepdefinitions",
        publish = false,
        plugin = {"listener.CucumberListener", "pretty",
                "html:target/cucumber-reports/CucumberReport2.html",
                "json:target/cucumber-reports/cucumber-report2.json"}
)
public class TestNGSequentialRunnerWeb {

    private final DesiredCapabilitiesUtil desiredCapabilitiesUtil = new DesiredCapabilitiesUtil();
    private TestNGCucumberRunner testNGCucumberRunner;

    /*TestNGCucumberRunner is used to set up and run Cucumber tests within the TestNGframework */
    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @BeforeMethod
    @Parameters
    public void setup() throws IOException {
        ConfigReader configReader = new ConfigReader();
        String browserStackUsername = configReader.config().getProperty("BrowserStackUsername");
        String browserStackAccessKey = configReader.config().getProperty("BrowserStackAccessKey");
        String browserStackServer = configReader.config().getProperty("BrowserStackServer");
        String webBrowser = System.getProperty("WebBrowser", configReader.config().getProperty("WebBrowser"));
        String webPlatform = System.getProperty("WebPlatform", configReader.config().getProperty("WebPlatform"));
        String webPlatformVersion = System.getProperty("WebPlatformVersion", configReader.config().getProperty("WebPlatformVersion"));
        DesiredCapabilities caps = desiredCapabilitiesUtil.getDesiredCapabilitiesWeb(webPlatform, webPlatformVersion, webBrowser);
        if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Cloud").equalsIgnoreCase("true")) {
            ThreadLocalDriver.setRemoteWebDriverThreadLocal(new RemoteWebDriver(new URL("http://" + browserStackUsername + ":" + browserStackAccessKey + "@" + browserStackServer + "/wd/hub"), caps));
        } else {
            if (webBrowser.equalsIgnoreCase("edge")) {
                WebDriver driver = WebDriverManager.edgedriver().create();
                ThreadLocalDriver.setWebDriverThreadLocal(driver);
            } else if (webBrowser.equalsIgnoreCase("chrome")) {
                WebDriver driver = WebDriverManager.chromedriver().create();
                ThreadLocalDriver.setWebDriverThreadLocal(driver);
            } else if (webBrowser.equalsIgnoreCase("firefox")) {
                WebDriver driver = WebDriverManager.firefoxdriver().create();
                ThreadLocalDriver.setWebDriverThreadLocal(driver);
            }
        }
    }

    /**
    *Wrappers used to access information about scenarios and features
    * This runs the cucumber scenario
    */
    @Test(groups = "cucumber", description = "Run Cucumber Features.", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    /**
     * Returns two dimensional array of PickleEventWrapper scenarios
     * with their associated CucumberFeatureWrapper feature.
     * @return a two dimensional array of scenarios features.
     * Its a method that provides list of Cucumber scenarios to be executed as part of test suite
     */
    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod
    public synchronized void teardown() {
        if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("WebOrMobile").equalsIgnoreCase("WebCloud"))
            ThreadLocalDriver.getRemoteWebDriverThreadLocal().quit();
        else
            ThreadLocalDriver.getWebDriverThreadLocal().quit();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}