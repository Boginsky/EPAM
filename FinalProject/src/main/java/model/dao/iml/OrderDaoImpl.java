package model.dao.iml;

import exception.DaoException;
import model.dao.BaseDao;
import model.dao.OrderDao;
import model.entity.user.Order;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    private static final String FIND_ALL_ORDERS = "SELECT total_price,order_date,order_status FROM orders JOIN orders_statuses on order_statuses_order_statuses_id = order_statuses_id";
    private static final String FIND_ORDER_BY_ID = "SELECT total_price,order_date,order_status FROM orders JOIN orders_statuses on order_statuses_order_statuses_id = order_statuses_id WHERE order_id = ?";
    private static final String INSERT_INTO_ORDERS = "INSERT INTO orders (total_price,order_date,order_statuses_order_statuses_id,users_user_id) VALUES(?,?,?,?)";
    private static final String FIND_ALL_ORDERS_BY_CLIENT_ID = "SELECT total_price,order_date,order_status FROM orders JOIN orders_statuses on order_statuses_order_statuses_id = order_statuses_id WHERE users_user_id = ?";
    private static final String DELETE_ORDER_BY_CLIENT_ID = "DELETE FROM orders WHERE users_user_id = ?";

    @Override
    public List<Order> findAll() throws DaoException {
        PreparedStatement preparedStatement = null;
        List<Order> listOfOrders = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BigDecimal price = resultSet.getBigDecimal(1);
                Timestamp dateOfCreation = resultSet.getTimestamp(2);
                String orderStatus = resultSet.getString(3);
                listOfOrders.add(new Order(price, dateOfCreation, Order.OrderStatus.valueOf(orderStatus)));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all orders");
        }
        return listOfOrders;
    }

    @Override
    public Optional<Order> findById(Long id) throws DaoException {
        PreparedStatement preparedStatement = null;
        Optional<Order> order = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BigDecimal price = resultSet.getBigDecimal(1);
                Timestamp dateOfCreation = resultSet.getTimestamp(2);
                String orderStatus = resultSet.getString(3);
                order = Optional.of(new Order(price, dateOfCreation, Order.OrderStatus.valueOf(orderStatus)));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding order by id");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
        return order;
    }

    @Override
    public void insertOrder(BigDecimal totalPrice, Long orderStatusId, Long songId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS);
            preparedStatement.setBigDecimal(1, totalPrice);
            preparedStatement.setTimestamp(2, Timestamp.from(Instant.now()));
            preparedStatement.setLong(3, orderStatusId);
            preparedStatement.setLong(4, songId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new order");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }

    }

    @Override
    public List<Order> findAllByClientId(Long clientId) throws DaoException {
        PreparedStatement preparedStatement = null;
        List<Order> listOfOrders = new ArrayList<>();
        Order order = null;
        try {
            preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS_BY_CLIENT_ID);
            preparedStatement.setLong(1, clientId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BigDecimal totalPrice = resultSet.getBigDecimal(1);
                Timestamp dateOfCreation = resultSet.getTimestamp(2);
                String orderStatus = resultSet.getString(3);
                order = new Order(totalPrice, dateOfCreation, Order.OrderStatus.valueOf(orderStatus));
                listOfOrders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding order by client id");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
        return listOfOrders;
    }

    @Override
    public void deleteByClient(Long clientId) throws DaoException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(DELETE_ORDER_BY_CLIENT_ID);
            preparedStatement.setLong(1,clientId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, deleting order by client id");
        } finally {
            if (connection != null) {
                close(preparedStatement);
            }
        }
    }

    @Override
    public void deleteBySong(Long songId) throws DaoException {

    }

    @Override
    public boolean findCanceledOrders(Long clientId, Long songId) throws DaoException {
        return false;
    }

    @Override
    public void updateOrder(Long clientId, Long songId) throws DaoException {

    }
}
