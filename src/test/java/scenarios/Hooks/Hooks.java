package scenarios.Hooks;

import org.testng.annotations.AfterGroups;
import org.testng.annotations.BeforeGroups;
import setup.Driver;

import java.io.IOException;


/**
 * web test for setting up and tera down driver
 */

public class Hooks extends Driver {
    /**
     * Required variables will be initialized by inherited constructor
     *
     * @throws IOException
     */
    public Hooks(String type) throws IOException {
        super(type);
    }

    @BeforeGroups(groups = {"web"}, description = "Prepare webdriver to run test(s)")
    public void setUpWeb() throws Exception {
        System.out.println("start preparing web");
        prepareDriver();
        System.out.println("Driver prepared");
    }

    @BeforeGroups(groups = {"native"}, description = "Prepare nativedriver to run test(s)")
    public void setUpNative() throws Exception {
        System.out.println("start preparing native");
        prepareDriver();
        System.out.println("Driver prepared");
    }

    @AfterGroups(groups = {"web", "native"}, description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver().closeApp();
        driverTearDown();
        System.out.println("Driver closed");
    }


}

