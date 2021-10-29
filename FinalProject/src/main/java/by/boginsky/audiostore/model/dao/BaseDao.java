package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.model.entity.AbstractEntity;

import java.sql.Connection;

/**
 * The type Base dao.
 *
 * @param <T> the type parameter
 */
public abstract class BaseDao<T extends AbstractEntity> {

    /**
     * The Connection.
     */
    protected Connection connection;

    /**
     * Sets connection.
     *
     * @param connection the connection
     */
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

}
