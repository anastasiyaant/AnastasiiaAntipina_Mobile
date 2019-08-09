package scenarios.nativeTests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import scenarios.Hooks.Hooks;

import java.io.IOException;

@Test(groups = "native")
public class SimpleNativeTest extends Hooks {
    protected SimpleNativeTest() throws IOException {
        super("native");
    }

    @Test(description = "Just click on button 'Add contact'")
    public void simplestTest() {
        String app_package_name = "com.example.android.contactmanager:id/";
        By add_btn = By.id(app_package_name + "addContactButton");
        driverSingle.findElement(add_btn).click();
        // The result of clicking doesn't checked.
        System.out.println("Simplest Appium test done");
    }
}

