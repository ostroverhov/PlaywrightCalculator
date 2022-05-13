package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = Logger.getInstance();
    private static final String pathToConfig = "src/main/resources/config.properties";

    public static String getParameter(String parameter) {
        logger.info("Get parameter [%s]", parameter);
        Properties property = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToConfig);
            property.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property.getProperty(parameter);
    }
}
