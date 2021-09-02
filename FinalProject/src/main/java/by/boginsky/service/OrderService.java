package by.boginsky.service;

import by.boginsky.exception.DaoException;
import by.boginsky.exception.ServiceException;
import by.boginsky.model.dao.TransactionManager;
import by.boginsky.model.dao.impl.OrderDaoImpl;
import by.boginsky.model.entity.user.Order;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private OrderService() {
    }

    public static List<Order> findAllOrders() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            return orderDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all orders", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Order> findOrderById(Long orderId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            return orderDaoImpl.findById(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding order by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static void addNewOrder(Long orderStatusId, Long userId, Long songId, Long orderId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            orderDaoImpl.insertOrder(orderStatusId, userId, songId, orderId);
            transactionManager.commit();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method adding new order", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static List<Order> findAllByUserName(String userFirstName, String userLastName) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            return orderDaoImpl.findAllOrdersByUserName(userFirstName, userLastName);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding order by user's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static void cancelOrderByUserId(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            orderDaoImpl.cancelByUserId(userId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method canceling order by user id(rollback)", daoException);
            }
            throw new ServiceException("Exception in method canceling order by user id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static List<Order> findCanceledOrdersByUserId(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            return orderDaoImpl.findCanceledOrdersByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding canceled orders", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static BigDecimal getOrderTotalPrice(Long orderId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
        try {
            transactionManager.startTransaction(orderDaoImpl);
            return orderDaoImpl.getTotalOrderPriceByOrderID(orderId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding total price of order", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static boolean isEnoughMoney(BigDecimal userMoney, Long songId) throws ServiceException {
        return false;
    }

    public static void buySong(BigDecimal userMoney, BigDecimal songPrice) throws ServiceException {

    }
}
