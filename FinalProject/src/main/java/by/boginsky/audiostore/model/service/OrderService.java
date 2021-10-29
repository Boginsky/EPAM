package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.entity.user.Order;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Add new order.
     *
     * @param user            the user
     * @param moneyForPayment the money for payment
     * @param bonusForPayment the bonus for payment
     * @param totalPrice      the total price
     * @param songs           the songs
     * @throws ServiceException the service exception
     */
    void addNewOrder(User user, BigDecimal moneyForPayment, BigDecimal bonusForPayment, BigDecimal totalPrice, Set<Song> songs) throws ServiceException;

    /**
     * Find all by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Order> findAllByUserId(Long userId) throws ServiceException;

}
