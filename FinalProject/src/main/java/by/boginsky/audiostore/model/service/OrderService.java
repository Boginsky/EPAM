package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface OrderService {

    List<Order> findAllOrders() throws ServiceException;

    void addNewOrder(Long orderStatusId, Long userId, Long songId, Long orderId) throws ServiceException;

    List<Order> findAllByUserName(String userFirstName, String userLastName) throws ServiceException;

    void cancelOrderByUserId(Long userId) throws ServiceException;

    List<Order> findCanceledOrdersByUserId(Long userId) throws ServiceException;

    BigDecimal getOrderTotalPrice(Long orderId) throws ServiceException;

    boolean isEnoughMoney(BigDecimal userMoney, Long songId) throws ServiceException;

    void buySong(BigDecimal userMoney, BigDecimal songPrice) throws ServiceException;


}
