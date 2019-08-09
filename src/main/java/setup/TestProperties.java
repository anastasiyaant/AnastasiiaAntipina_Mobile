package setup;

import enums.PropertyFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
    String propPath;
    Properties currentProps = new Properties();

    protected static String deviceName = "42004cf9f2772300";

    Properties getCurrentProps(String type) throws IOException {
        if (type.equals("web")){
            propPath = PropertyFile.WEB.getPath();
        }else if (type.equals("native")){
            propPath = PropertyFile.NATIVE.getPath();
        }else System.out.println("Wrong app type!");
        FileInputStream in = new FileInputStream(System.getProperty("user.dir") + propPath);
        currentProps.load(in);
        in.close();
        return currentProps;
    }

    protected String getProp(String type, String propKey) throws IOException {
        if (!currentProps.containsKey(propKey))
            currentProps = getCurrentProps(type);        // "default" form used to handle the absence of parameter
        return currentProps.getProperty(propKey, null);
    }
}
