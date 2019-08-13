package setup;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Preparing driver for testing
 */
public class Driver extends TestProperties {
    protected static AppiumDriver driverSingle;
    protected DesiredCapabilities capabilities;
    protected static WebDriverWait waitSingle;

    // Properties to be read
    protected static String AUT; // (mobile) app under testing
    protected static String SUT; // site under testing
    protected static String TEST_PLATFORM;
    protected static String DRIVER;
    protected static String DEVICE_NAME;
    protected static String UDID;
    protected static String APP_PACKAGE;
    protected static String APP_ACTIVITY;

    // Constructor initializes properties on driver creation
    protected Driver(String type) throws IOException {
        AUT = getProp(type, "aut");
        String t_sut = getProp(type, "sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp(type, "platform");
        DRIVER = getProp(type, "driver");
        DEVICE_NAME = getProp(type, "deviceName");
        UDID = getProp(type, "udid");
        APP_PACKAGE = getProp(type, "apppackage");
        APP_ACTIVITY = getProp(type, "appactivity");
    }

    /*
     * Initialize driver with appropriate capabilities depending on platform and application
     * @throws Exception     */
    protected void prepareDriver() throws Exception {
        capabilities = new DesiredCapabilities();

        String browserName;
        // Setup test platform: Android or iOS. Browser also depends on a platform.
        switch (TEST_PLATFORM) {
            case "Android":
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        //capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME);
        capabilities.setCapability(MobileCapabilityType.UDID, UDID);
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            capabilities.setCapability("appPackage",APP_PACKAGE);
            capabilities.setCapability("appActivity", APP_ACTIVITY);
            //capabilities.setCapability("autoLaunch", "true");

        } else if (SUT != null && AUT == null) {
            // Web
            System.out.println("WEB");
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
            // to avoid device disconnection and reboot
            //capabilities.setCapability("noReset", "true");
        } else {
            throw new Exception("Unclear type of mobile app");
        }

        // Init driverSingle for local Appium server with set capabilities
        if (driverSingle == null) {
            driverSingle = new AppiumDriver(new URL(DRIVER), capabilities);
        }
        // Set an object to handle timeouts
        if (waitSingle == null) {
            waitSingle = new WebDriverWait(driver(), 10);
        }

    }

    /**
     * Makes driverSingle Singletone pattern
     *
     * @return driverSingle
     * @throws Exception
     */
    protected AppiumDriver driver() throws Exception {
        if (driverSingle == null) {
            prepareDriver();
        }
        return driverSingle;
    }

    protected WebDriverWait driverWait() throws Exception {
        return waitSingle;
    }


}
