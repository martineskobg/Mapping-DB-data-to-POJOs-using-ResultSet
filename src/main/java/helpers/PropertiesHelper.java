package helpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    private static final String CONFIG_FILE_PATH = "src/main/resources/connections/config.properties";
    private FileInputStream config;
    private Properties properties = new Properties();

    {
        try {
            config = new FileInputStream(CONFIG_FILE_PATH);
            properties.load(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getDbUrl() {
        return properties.getProperty("db.url");
    }

    public String getDbUsername() {
        return properties.getProperty("db.username");
    }

    public String getDbPassword() {
        return properties.getProperty("db.password");
    }
}
