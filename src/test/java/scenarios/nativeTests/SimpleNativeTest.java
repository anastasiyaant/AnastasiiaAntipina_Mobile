package scenarios.nativeTests;

import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.NewContactPage;
import scenarios.Hooks.Hooks;

import static org.testng.Assert.assertTrue;

/**
 * native test for checking application functions
 */
@Test(groups = "native")
public class SimpleNativeTest extends Hooks {

    private NewContactPage newContactPage;
    private HomePage homePage;

    protected SimpleNativeTest() throws Exception {
        super("native");
        newContactPage = new NewContactPage(driver());
        homePage = new HomePage(driver());
    }

    @Test(groups = "native", description = "Just click on button 'Add contact'")
    public void simplestTest() {
        homePage.getContactButton().click();
        System.out.println("addContactButton was clicked");

        checkElementIsDisplayed(newContactPage.getContactName());
        System.out.println("contactNameEditText is displayed");

        checkElementIsDisplayed(newContactPage.getContactPhone());
        System.out.println("contactPhoneEditText is displayed");

        checkElementIsDisplayed(newContactPage.getContactEmail());
        System.out.println("contactEmailEditText is displayed");

        checkElementIsDisplayed(newContactPage.getAccountSpinner());
        System.out.println("accountSpinner is displayed");

        System.out.println("Simplest Appium test done");
    }

    private void checkElementIsDisplayed(WebElement element) {
        assertTrue(element.isDisplayed());
    }

}