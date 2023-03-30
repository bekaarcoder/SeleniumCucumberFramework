package org.autozone.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    public Properties init_prop() {
        Properties properties = new Properties();
        try {
            FileInputStream fis = new FileInputStream("./src/test/resources/config/application.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
