package setup;

import io.appium.java_client.AppiumDriver;
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

    // Constructor initializes properties on driver creation
    protected Driver(String type) throws IOException {
        AUT = getProp(type, "aut");
        String t_sut = getProp(type, "sut");
        SUT = t_sut == null ? null : "http://" + t_sut;
        TEST_PLATFORM = getProp(type, "platform");
        DRIVER = getProp(type, "driver");
        DEVICE_NAME = getProp(type, "devicename");
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
                capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, DEVICE_NAME); // default Android emulator
                browserName = "Chrome";
                break;
            case "iOS":
                browserName = "Safari";
                break;
            default:
                throw new Exception("Unknown mobile platform");
        }
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, TEST_PLATFORM);

        // Setup type of application: mobile, web (or hybrid)
        if (AUT != null && SUT == null) {
            // Native
            File app = new File(AUT);
            capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        } else if (SUT != null && AUT == null) {
            // Web
            capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, browserName);
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