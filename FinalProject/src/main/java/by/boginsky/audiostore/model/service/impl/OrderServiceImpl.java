package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.OrderDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.entity.user.Order;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.OrderService;
import by.boginsky.audiostore.model.service.UserService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type Order service.
 */
public class OrderServiceImpl implements OrderService {

    private static OrderService instance;
    private static final AtomicBoolean isOrderService = new AtomicBoolean(false);

    private OrderServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static OrderService getInstance() {
        while (instance == null) {
            if (isOrderService.compareAndSet(false, true)) {
                instance = new OrderServiceImpl();
            }
        }
        return instance;
    }

    public void addNewOrder(User user, BigDecimal moneyForPayment, BigDecimal bonusForPayment, BigDecimal totalPrice, Set<Song> songs) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserService userService = UserServiceImpl.getInstance();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        List<Long> listOfSongsIds = new ArrayList<>();
        for (Song song : songs) {
            listOfSongsIds.add(song.getId());
        }
        if (bonusForPayment.compareTo(BigDecimal.ZERO) > 0) {
            totalPrice = totalPrice.subtract(bonusForPayment);
            userService.updateUserBonus(user.getId(), user.getBonus().subtract(bonusForPayment));
        }
        if (totalPrice.compareTo(BigDecimal.ZERO) > 0) {
            try {
                transactionManager.startTransaction(orderDaoImpl);
                orderDaoImpl.insertOrder(user.getId(), listOfSongsIds);
                transactionManager.commit();
                BigDecimal userNewMoney = user.getBalance().subtract(totalPrice);
                userService.updateUserMoney(user.getId(), userNewMoney);
            } catch (DaoException e) {
                throw new ServiceException("Exception in method adding new order", e);
            } finally {
                transactionManager.endTransaction();
            }
        }
    }

    public List<Order> findAllByUserId(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            return orderDaoImpl.findAllOrdersByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding order by user's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

}
