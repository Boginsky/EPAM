package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.pool.ProxyConnection;
import by.boginsky.audiostore.model.entity.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity> {


    // TODO: 13.09.2021 threads. будут ли все потоки использовать один коннекшен. если создать синглтон
    protected ProxyConnection connection;

    protected static final Logger logger = LogManager.getLogger();

    public abstract List<T> findAll() throws DaoException;

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

    public void close(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException e) {
            logger.error("Error in statement closing");
        }
}

    public void close(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            logger.error("Error in connection closing");
        }
    }
}
