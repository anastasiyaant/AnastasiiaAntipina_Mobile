package scenarios.webTests;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import scenarios.Hooks.Hooks;

import java.io.IOException;

@Test(groups = "web")
public class SimpleWebTest extends Hooks {
    protected SimpleWebTest() throws IOException {
        super("web");
    }

    @Test(description = "Open website")
    public void webTest() throws InterruptedException {
        driverSingle.get(SUT);
// this always ends OK; it's a drawback.
        waitSingle.until(ExpectedConditions.urlToBe(SUT + "/"));
        System.out.println("Site opening done");
    }

}

