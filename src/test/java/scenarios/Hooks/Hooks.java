package scenarios.Hooks;

import org.testng.annotations.*;
import setup.Driver;
import java.io.IOException;


/**
 * web test for setting up and tera down driver
 */
//@Test(groups = {"native", "web"})
public class Hooks extends Driver {
    /**
     * Required variables will be initialized by inherited constructor
     *
     * @throws IOException
     */
    public Hooks(String type) throws IOException {
        super(type);
    }

    @BeforeGroups(groups = {"web"}, description = "Prepare web driver to run test(s)")
    public void setUpWeb() throws Exception {
        System.out.println("start preparing web");
        prepareDriver();
        System.out.println("Driver prepared");
    }

    @BeforeGroups(groups = {"native"}, description = "Prepare native driver to run test(s)")
    public void setUpNative() throws Exception {
        System.out.println("start preparing native");
        prepareDriver();
        System.out.println("Driver prepared");
    }



    @AfterGroups(groups = {"native", "web"}, description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver().closeApp();
        driverTearDown();
        System.out.println("Driver closed");
    }


}