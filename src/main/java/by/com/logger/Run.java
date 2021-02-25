package by.com.logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Run {

    public Properties asProperties(String filename) {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(filename)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}