package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.user.Order;

import java.util.List;

/**
 * The interface Order dao.
 */
public interface OrderDao {

    /**
     * Insert order.
     *
     * @param userId  the user id
     * @param songsId the songs id
     * @throws DaoException the dao exception
     */
    void insertOrder(Long userId, List<Long> songsId) throws DaoException;

    /**
     * Find all orders by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Order> findAllOrdersByUserId(Long userId) throws DaoException;

}
