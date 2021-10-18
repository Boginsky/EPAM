package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.user.Order;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {

    void insertOrder(Long userId, List<Long> songsId) throws DaoException;

    List<Order> findAllOrdersByUserId(Long userId) throws DaoException;

}
