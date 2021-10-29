package by.boginsky.audiostore.model.pool;

import by.boginsky.audiostore.exception.PropertyReaderException;
import by.boginsky.audiostore.util.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * The type Connection factory.
 */
class ConnectionFactory {

    private static final String url;
    private static final Properties properties;
    private static final Logger logger = LogManager.getLogger();
    private static final String DATABASE_PROPERTY_PATH = "database.properties";
    private static final String DATABASE_URL = "url";
    private static final String DATABASE_DRIVER = "driverClassName";

    private ConnectionFactory() {
    }

    static {
        try {
            PropertyReader reader = new PropertyReader();
            properties = reader.read(DATABASE_PROPERTY_PATH);
            url = properties.getProperty(DATABASE_URL);
            String driver = properties.getProperty(DATABASE_DRIVER);
            Class.forName(driver);
        } catch (ClassNotFoundException | PropertyReaderException e) {
            logger.fatal("Fatal error during connection pool creation ", e);
            throw new RuntimeException("Fatal error during connection pool creation ", e);
        }
    }

    /**
     * Gets connection.
     *
     * @return the connection
     * @throws SQLException the sql exception
     */
    static ProxyConnection getConnection() throws SQLException {
        return new ProxyConnection(DriverManager.getConnection(url, properties));
    }
}
