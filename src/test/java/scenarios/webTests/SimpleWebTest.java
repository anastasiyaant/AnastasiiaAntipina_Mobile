package scenarios.webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.Hooks.Hooks;

import java.io.IOException;

import static org.testng.Assert.assertEquals;


/**
 * web test for checking website functions
 */
@Test(groups = "web")
public class SimpleWebTest extends Hooks {
    protected SimpleWebTest() throws IOException {
        super("web");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException {
        driverSingle.get(SUT);
        System.out.println(SUT);
        //waitSingle.until(ExpectedConditions.urlToBe(SUT + "/"));
        assertEquals(driverSingle.getTitle(), "Internet Assigned Numbers Authority");
        System.out.println("Site title check done");
    }

}

