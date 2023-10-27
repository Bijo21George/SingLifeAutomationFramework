package utilities;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

public class DesiredCapabilitiesUtil {
    public DesiredCapabilities getDesiredCapabilitiesMobile(String deviceName, String platform, String platformVersion) {
        ConfigReader configReader = new ConfigReader();
        String browserStackAppURLAndroid = configReader.config().getProperty("BrowserStackAppURLAndroid");
        String browserStackAppURLIos = configReader.config().getProperty("BrowserStackAppURLIos");
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
                .getParameter("Cloud").equalsIgnoreCase("true")) {
            desiredCapabilities.setCapability("deviceName", deviceName);
            desiredCapabilities.setCapability("appPackage", "org.wikipedia.alpha");
            desiredCapabilities.setCapability("appActivity", "org.wikipedia.main.MainActivity");
            if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
                    .getParameter("platform").equalsIgnoreCase("android"))
                desiredCapabilities.setCapability("app", browserStackAppURLAndroid);
            else
                desiredCapabilities.setCapability("app", browserStackAppURLIos);
            desiredCapabilities.setCapability("browserstack.video", "true");
            desiredCapabilities.setCapability("project", "Mobile Automation Project");
            desiredCapabilities.setCapability("build", "Mobile Automation Build");
            desiredCapabilities.setCapability("name", "Mobile Automation Name");
        } else {
            desiredCapabilities.setCapability("udid", deviceName);
            desiredCapabilities.setCapability("appPackage", "com.myntra.android");
            desiredCapabilities.setCapability("appActivity", "com.myntra.android.activities.react.ReactActivity");
        }
        desiredCapabilities.setCapability("platformVersion", platformVersion);
        desiredCapabilities.setCapability("platformName", platform);
        desiredCapabilities.setCapability("skipUnlock", "true");
        desiredCapabilities.setCapability("noReset", "false");
        return desiredCapabilities;
    }

    public DesiredCapabilities getDesiredCapabilitiesWeb(String platform, String platformVersion, String browser) {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        if (Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest()
                .getParameter("Cloud").equalsIgnoreCase("true")) {
            desiredCapabilities.setCapability("os", platform);
            desiredCapabilities.setCapability("os_version", platformVersion);
            desiredCapabilities.setCapability("browser", browser);
            if (platform.toLowerCase().contains("windows")) {
                desiredCapabilities.setCapability("resolution", "1366x768");
            }
            if (platform.toLowerCase().contains("os x")) {
                desiredCapabilities.setCapability("resolution", "1280x960");
            }
            if (browser.equalsIgnoreCase("safari")) {
                desiredCapabilities.setCapability("browser_version", "15.1");
            } else {
                desiredCapabilities.setCapability("browser_version", "latest");
            }
            desiredCapabilities.setCapability("browserstack.video", "true");
            desiredCapabilities.setCapability("project", "ProjectName");
            desiredCapabilities.setCapability("build", "BuildName");
            desiredCapabilities.setCapability("name", "TestName");
        } else {
            desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        }
        return desiredCapabilities;
    }
}
