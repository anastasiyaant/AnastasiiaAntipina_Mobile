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

    @BeforeGroups(groups = {"native", "web"}, description = "Prepare driver to run test(s)")
    public void setUp() throws Exception {
        prepareDriver();
        System.out.println("Driver prepared");

    }

    @AfterGroups(groups = {"native", "web"}, description = "Close driver on all tests completion")
    public void tearDown() throws Exception {
        driver().quit();
        System.out.println("Driver closed");
    }


}