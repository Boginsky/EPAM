package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.AbstractEntity;
import by.boginsky.audiostore.model.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public abstract class BaseDao<T extends AbstractEntity> {

    // TODO: 13.09.2021 threads. будут ли все потоки использовать один коннекшен. если создать синглтон
    protected ProxyConnection connection;

    public void setConnection(ProxyConnection connection) {
        this.connection = connection;
    }

}
