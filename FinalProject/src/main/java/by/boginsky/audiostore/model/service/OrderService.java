package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.entity.user.Order;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public interface OrderService {

    List<Order> findAllOrders() throws ServiceException;

    void addNewOrder(User user,BigDecimal moneyForPayment,BigDecimal bonusForPayment,BigDecimal totalPrice, Set<Song> songs) throws ServiceException;

    List<Order> findAllByUserId(Long userId) throws ServiceException;

    void cancelOrderByUserId(Long userId) throws ServiceException;

    List<Order> findCanceledOrdersByUserId(Long userId) throws ServiceException;

    BigDecimal getOrderTotalPrice(Long orderId) throws ServiceException;

    void buySong(BigDecimal userMoney, BigDecimal songPrice) throws ServiceException;


}
