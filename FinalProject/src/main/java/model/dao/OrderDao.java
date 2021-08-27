package model.dao;

import exception.DaoException;
import model.entity.audio.Song;
import model.entity.user.Order;
import model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDao {

    void insertOrder(BigDecimal totalPrice, Long orderStatusId, Long songId) throws DaoException;

    List<Order> findAllByClientId(Long clientId) throws DaoException;

    void deleteByClient(Long clientId) throws DaoException;

    void deleteBySong(Long songId) throws DaoException;

    boolean findCanceledOrders(Long clientId,Long songId) throws DaoException;

    void updateOrder(Long clientId,Long songId) throws DaoException;

//    List<User> findUsersWantingToBuy(Long songId) throws DaoException;// TODO: 25.08.2021 after all job is done or remove
}
