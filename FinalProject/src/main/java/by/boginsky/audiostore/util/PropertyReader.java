package by.boginsky.audiostore.util;

import by.boginsky.audiostore.exception.PropertyReaderException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * The type Property reader.
 */
public class PropertyReader {

    private static final Logger logger = LogManager.getLogger();

    /**
     * Read properties.
     *
     * @param path the path
     * @return the properties
     * @throws PropertyReaderException the property reader exception
     */
    public Properties read(String path) throws PropertyReaderException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(path)) {
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            logger.error("Error in reading properties", e);
            throw new PropertyReaderException("Error in reading properties", e);
        }
    }
}
