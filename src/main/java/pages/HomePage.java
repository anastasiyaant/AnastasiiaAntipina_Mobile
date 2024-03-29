package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    protected AppiumDriver driver;

    private static final String app_package_name = "com.example.android.contactmanager:id/";

    @AndroidFindBy(id = app_package_name + "addContactButton")
    private AndroidElement contactButton;

    public HomePage(AppiumDriver driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    public AndroidElement getContactButton() {
        return contactButton;
    }
}