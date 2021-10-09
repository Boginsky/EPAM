package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.user.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {

    void insertOrder(Long userId, List<Long> songsId) throws DaoException;

    List<Order> findAllOrdersByUserId(Long userId) throws DaoException;

    void cancelByUserId(Long userId) throws DaoException;

    List<Order> findCanceledOrdersByUserId(Long userId) throws DaoException;

    BigDecimal getTotalOrderPriceByOrderID(Long orderId) throws DaoException;

//    List<User> findUsersWantingToBuy(Long songId) throws DaoException;// TODO: 25.08.2021 after all job is done or remove
}
