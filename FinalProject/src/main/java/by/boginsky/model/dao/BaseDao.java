package by.boginsky.model.dao;

import by.boginsky.exception.DaoException;
import by.boginsky.model.pool.ProxyConnection;
import by.boginsky.model.entity.AbstractEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public abstract class BaseDao<T extends AbstractEntity> {

    protected ProxyConnection connection;

    protected static final Logger logger = LogManager.getLogger();

    public abstract List<T> findAll() throws DaoException;

    public abstract Optional<T> findById(Long id) throws DaoException;

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
