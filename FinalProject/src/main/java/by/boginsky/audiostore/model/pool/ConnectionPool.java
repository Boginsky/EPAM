package by.boginsky.audiostore.model.pool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {

    private static final Logger logger = LogManager.getLogger();
    private static final ReentrantLock locker = new ReentrantLock();
    private static final AtomicBoolean create = new AtomicBoolean(false);

    private static final int CONNECTION_POOL_SIZE = 8;

    private static ConnectionPool instance;
    private BlockingDeque<ProxyConnection> freeConnections;
    private BlockingDeque<ProxyConnection> busyConnections;

    private ConnectionPool() {
        try {
            freeConnections = new LinkedBlockingDeque<>(CONNECTION_POOL_SIZE);
            busyConnections = new LinkedBlockingDeque<>(CONNECTION_POOL_SIZE);
            for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
                ProxyConnection connection = ConnectionFactory.getConnection();
                freeConnections.add(connection);
            }
        } catch (SQLException e) {
            logger.error("Error with properties file", e);
        }
    }

    public static ConnectionPool getInstance() {
        if (!create.get()) {
            try {
                locker.lock();
                if (instance == null) {
                    instance = new ConnectionPool();
                    create.set(true);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    // FIXME: 03.09.2021 change on connection
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            busyConnections.put(connection);
        } catch (InterruptedException e) {
            logger.error("Error while getting connection.", e);
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public boolean releaseConnection(Connection connection) {
        boolean result = true;
        if (connection instanceof ProxyConnection && busyConnections.remove(connection)) {
            try {
                freeConnections.put((ProxyConnection) connection);
            } catch (InterruptedException e) {
                logger.error("Wrong connection to put.", e);
                Thread.currentThread().interrupt();
            }
        } else {
            logger.error("Wrong connection to release");
            result = false;
        }
        return result;
    }

    public void destroyPool() {
        try {
            for (ProxyConnection freeConnection : freeConnections) {
                freeConnection.reallyClose();
            }
            deregisterDrivers();
        } catch (SQLException e) {
            logger.error("Error during pool destruction", e);
        }
    }

    private void deregisterDrivers() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}
