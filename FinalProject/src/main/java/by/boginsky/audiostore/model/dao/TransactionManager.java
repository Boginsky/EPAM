package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.pool.ConnectionPool;
import by.boginsky.audiostore.model.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TransactionManager {
    private static final Logger logger = LogManager.getLogger();
    // FIXME: 13.09.2021 on connection
    private ProxyConnection connection;
    private List<BaseDao> baseDaos;

    public TransactionManager() {
        baseDaos = new ArrayList<>();
    }

    public void startTransaction(BaseDao ... baseDaos) throws DaoException {
        if(connection == null){
            connection = ConnectionPool.getInstance().getConnection();
        }
        try{
            connection.setAutoCommit(false);
        }catch (SQLException e){
            throw new DaoException("SQLException",e);
        }
        for (BaseDao currentDao:baseDaos){
            currentDao.setConnection(connection);
            this.baseDaos.add(currentDao);
        }
    }

    public void endTransaction(){
        for(BaseDao currentDao : baseDaos){
            currentDao.setConnection(null);
        }
        baseDaos.clear();
        if(connection != null){
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    public void commit() throws DaoException {
        if(connection != null){
            try {
                connection.commit();
            }catch (SQLException e){
                logger.error("SQLException", e);
                throw new DaoException("SQLException" , e);
            }
        }
    }

    public void rollback() throws DaoException {
        if(connection != null){
            try {
                connection.rollback();
            }catch (SQLException e){
                logger.error("SQLException", e);
                throw new DaoException("SQLException", e);
            }
        }
    }
}
