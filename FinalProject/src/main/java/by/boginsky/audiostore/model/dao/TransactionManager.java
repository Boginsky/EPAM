package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Transaction manager.
 */
public class TransactionManager {
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;
    private final List<BaseDao> baseDaos;

    /**
     * Instantiates a new Transaction manager.
     */
    public TransactionManager() {
        baseDaos = new ArrayList<>();
    }

    /**
     * Start transaction.
     *
     * @param baseDaos the base daos
     * @throws DaoException the dao exception
     */
    public void startTransaction(BaseDao... baseDaos) throws DaoException {
        if (connection == null) {
            connection = ConnectionPool.getInstance().getConnection();
        }
        try {
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
        } catch (SQLException e) {
            throw new DaoException("SQLException", e);
        }
        for (BaseDao currentDao : baseDaos) {
            currentDao.setConnection(connection);
            this.baseDaos.add(currentDao);
        }
    }

    /**
     * End transaction.
     */
    public void endTransaction() {
        for (BaseDao currentDao : baseDaos) {
            currentDao.setConnection(null);
        }
        baseDaos.clear();
        if (connection != null) {
            ConnectionPool.getInstance().releaseConnection(connection);
            connection = null;
        }
    }

    /**
     * Commit.
     *
     * @throws DaoException the dao exception
     */
    public void commit() throws DaoException {
        if (connection != null) {
            try {
                connection.commit();
            } catch (SQLException e) {
                logger.error("SQLException", e);
                throw new DaoException("SQLException", e);
            }
        }
    }

    /**
     * Rollback.
     *
     * @throws DaoException the dao exception
     */
    public void rollback() throws DaoException {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                logger.error("SQLException", e);
                throw new DaoException("SQLException", e);
            }
        }
    }
}
