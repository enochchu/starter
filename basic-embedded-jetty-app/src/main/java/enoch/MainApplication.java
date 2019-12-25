package enoch;

import org.eclipse.jetty.server.Server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MainApplication {
    public static void main(String[] args) throws Exception {
        Properties properties = loadProperties(MainApplication.class, "app.properties");

        int port = Integer.parseInt(properties.getProperty("jetty.port.number"));

        Server server = new Server(port);

        server.start();

        server.join();
    }

    private static Properties loadProperties(Class<?> clazz, String fileName) throws IOException {
        Logger logger = LoggerFactory.getLogger(MainApplication.class);
        Properties properties = null;

        try {
            properties = loadPropertiesFromFile(getFileFromResourcesAsStream(MainApplication.class, fileName));
        }
        catch (IOException e) {
            logger.error(e.getMessage());
        }

        return properties;
    }

    private static InputStream getFileFromResourcesAsStream(Class<?> clazz, String fileName) throws IOException {
        ClassLoader classLoader = clazz.getClassLoader();

        InputStream resourceStream = classLoader.getResourceAsStream(fileName);

        if (resourceStream.available() <= 0) {
            throw new IllegalArgumentException();
        }

        return resourceStream;
    }

    private static Properties loadPropertiesFromFile(InputStream inputStream) throws IOException {
        Properties properties = new Properties();

        properties.load(inputStream);

        return properties;
    }
}