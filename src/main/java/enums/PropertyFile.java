package enums;

/**
 * Enum with paths to properties files
 * with type of application
 */
public enum PropertyFile {

    NATIVE("/nativetest.properties"),
    WEB("/webtest.properties");

    final String path;

    PropertyFile(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}