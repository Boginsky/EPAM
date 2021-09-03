package by.boginsky.model.pool;

import by.boginsky.exception.PropertyReaderException;
import by.boginsky.util.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

class ConnectionFactory {

    public static final Logger logger = LogManager.getLogger();

    public static final String DATABASE_PROPERTY_PATH = "database.properties";
    public static final String DATABASE_URL = "url";
    public static final String DATABASE_DRIVER = "driverClassName";

    public static ProxyConnection getConnection(){
        try {
            PropertyReader reader = new PropertyReader();
            Properties properties = reader.read(DATABASE_PROPERTY_PATH);
            String url = properties.getProperty(DATABASE_URL);
            String driver = properties.getProperty(DATABASE_DRIVER);
            Class.forName(driver);
            return new ProxyConnection(DriverManager.getConnection(url,properties));
        } catch (SQLException | ClassNotFoundException  | PropertyReaderException e) {
            logger.fatal("Fatal error during connection pool creation ", e);
            throw new RuntimeException("Fatal error during connection pool creation ", e);
        }
    }
}
