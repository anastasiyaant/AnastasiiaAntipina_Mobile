package scenarios.nativeTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import scenarios.Hooks.Hooks;

import java.io.IOException;

import static org.testng.Assert.assertTrue;


/**
 * native test for checking application functions
 */
@Test(groups = "native")
public class SimpleNativeTest extends Hooks {
    protected SimpleNativeTest() throws IOException {
        super("native");
    }

    @Test(description = "Just click on button 'Add contact'")
    public void simplestTest() {
        getElementById("addContactButton").click();
        System.out.println("addContactButton was clicked");

        checkElementIsDisplayed(getElementById("contactNameEditText"));
        System.out.println("contactNameEditText is displayed");
        checkElementIsDisplayed(getElementById("contactPhoneEditText"));
        System.out.println("contactPhoneEditText is displayed");
        checkElementIsDisplayed(getElementById("contactEmailEditText"));
        System.out.println("contactEmailEditText is displayed");
        checkElementIsDisplayed(getElementById("accountSpinner"));
        System.out.println("accountSpinner is displayed");

        System.out.println("Simplest Appium test done");
    }

    private void checkElementIsDisplayed(WebElement element)  {
        assertTrue(element.isDisplayed());
    }

    private WebElement getElementById(String id){
        String app_package_name = "com.example.android.contactmanager:id/";
        By element = By.id(app_package_name + id);
        return driverSingle.findElement(element);
    }
}

