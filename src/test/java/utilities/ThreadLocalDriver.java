package utilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ThreadLocalDriver {

  private static final ThreadLocal<AppiumDriver<MobileElement>> appiumDriverThreadLocal = new ThreadLocal<>();
  private static final ThreadLocal<RemoteWebDriver> remoteWebDriverThreadLocal = new ThreadLocal<>();
  private static final ThreadLocal<WebDriver> webDriverThreadLocal = new ThreadLocal<>();

  public static synchronized void setAppiumDriverThreadLocal(AppiumDriver<MobileElement> driver) {
    appiumDriverThreadLocal.set(driver);
  }

  public static synchronized AppiumDriver<MobileElement> getAppiumDriverThreadLocal() {
    return appiumDriverThreadLocal.get();
  }

  public static synchronized void setRemoteWebDriverThreadLocal(RemoteWebDriver driver) {
    remoteWebDriverThreadLocal.set(driver);
  }

  public static synchronized RemoteWebDriver getRemoteWebDriverThreadLocal() {
    return remoteWebDriverThreadLocal.get();
  }

  public static synchronized void setWebDriverThreadLocal(WebDriver driver) {
    webDriverThreadLocal.set(driver);
  }

  public static synchronized WebDriver getWebDriverThreadLocal() {
    return webDriverThreadLocal.get();
  }
}