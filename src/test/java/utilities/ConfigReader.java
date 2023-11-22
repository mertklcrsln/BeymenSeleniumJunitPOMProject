package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties properties;

    static {
        String dosyaYolu = "src/configuration.properties";

        try {
            FileInputStream fis = new FileInputStream(dosyaYolu); //fis, reads properties file whose path is defined
            properties = new Properties();
            properties.load(fis); //Loads the information that fis reads into properties

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
        //It takes the value of the string key sent from the test and returns the value of this key by using the getProperty() method from the Properties Class.
    }
}
