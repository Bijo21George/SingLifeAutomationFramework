package cucumber.testRunners;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;
import org.testng.annotations.*;
import utilities.ConfigReader;
import utilities.DesiredCapabilitiesUtil;
import utilities.ThreadLocalDriver;

import java.io.IOException;
import java.net.URL;

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
public class TestNGSequentialRunnerMobile {

    private TestNGCucumberRunner testNGCucumberRunner;
    private final DesiredCapabilitiesUtil desiredCapabilitiesUtil = new DesiredCapabilitiesUtil();

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
        String mobileDevice = System.getProperty("MobileDevice", configReader.config().getProperty("MobileDevice"));
        String mobilePlatform = System.getProperty("MobilePlatform", configReader.config().getProperty("MobilePlatform"));
        String mobilePlatformVersion = System.getProperty("MobilePlatformVersion", configReader.config().getProperty("MobilePlatformVersion"));
        DesiredCapabilities caps = desiredCapabilitiesUtil.getDesiredCapabilitiesMobile(mobileDevice, mobilePlatform, mobilePlatformVersion);
        if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("Cloud").equalsIgnoreCase("true")) {
            if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("platform").equalsIgnoreCase("android"))
                ThreadLocalDriver.setAppiumDriverThreadLocal(new AndroidDriver<>(new URL("http://" + browserStackUsername + ":" + browserStackAccessKey + "@" + browserStackServer + "/wd/hub"), caps));
            else
                ThreadLocalDriver.setAppiumDriverThreadLocal(new IOSDriver<>(new URL("http://" + browserStackUsername + ":" + browserStackAccessKey + "@" + browserStackServer + "/wd/hub"), caps));
        } else {
            ThreadLocalDriver.setAppiumDriverThreadLocal(new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), caps));
        }
    }

    @Test(groups = "cucumber", description = "Run Cucumber Features.", dataProvider = "scenarios")
    public void scenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        testNGCucumberRunner.runScenario(pickleWrapper.getPickle());
    }

    /**
     * Returns two dimensional array of PickleEventWrapper scenarios
     * with their associated CucumberFeatureWrapper feature.
     *
     * @return a two dimensional array of scenarios features.
     */
    @DataProvider
    public Object[][] scenarios() {
        return testNGCucumberRunner.provideScenarios();
    }

    @AfterMethod
    public synchronized void teardown() {
        ThreadLocalDriver.getAppiumDriverThreadLocal().quit();
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        testNGCucumberRunner.finish();
    }
}