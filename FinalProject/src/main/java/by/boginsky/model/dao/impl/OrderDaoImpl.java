package by.boginsky.model.dao.impl;

import by.boginsky.exception.DaoException;
import by.boginsky.model.dao.BaseDao;
import by.boginsky.model.dao.OrderDao;
import by.boginsky.model.entity.audio.Song;
import by.boginsky.model.entity.user.Order;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.model.dao.ColumnName.*;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    private static final String FIND_ALL_ORDERS = "SELECT order_id,order_date,order_status,user_id,song_id FROM orders JOIN orders_statuses ON order_statuses_order_statuses_id = order_statuses_id JOIN users ON users_user_id = user_id JOIN songs_has_orders on order_id = orders_order_id JOIN songs on songs_song_id = song_id";
    private static final String FIND_ORDER_BY_ID = "SELECT order_date,order_status,user_id,song_id FROM orders JOIN orders_statuses ON order_statuses_order_statuses_id = order_statuses_id JOIN users ON users_user_id = user_id JOIN songs_has_orders on order_id = orders_order_id JOIN songs on songs_song_id = song_id WHERE order_id = ?";
    private static final String INSERT_INTO_ORDERS = "INSERT INTO orders (order_date,order_statuses_order_statuses_id,users_user_id) VALUES(?,?,?,?)";
    private static final String INSERT_INTO_SONGS_HAS_ORDERS = "INSERT INTO songs_has_orders (songs_song_id,orders_order_id) VALUES(?,?)";
    private static final String FIND_ALL_ORDERS_BY_USER_NAME = "SELECT order_id,order_date,order_status,user_id,song_id FROM orders JOIN orders_statuses ON order_statuses_order_statuses_id = order_statuses_id JOIN users ON users_user_id = user_id JOIN songs_has_orders on order_id = orders_order_id JOIN songs on songs_song_id = song_id WHERE first_name = ? AND last_name = ?";
    private static final String CANCEL_ORDER_BY_USER_ID = "UPDATE orders SET order_statuses_order_statuses_id = 3 WHERE users_user_id = ?";
    private static final String FIND_ALL_CANCELED_ORDERS_BY_USER_ID = "SELECT order_id,order_date,order_status,song_id FROM orders JOIN orders_statuses ON order_statuses_order_statuses_id = order_statuses_id JOIN users ON users_user_id = user_id JOIN songs_has_orders on order_id = orders_order_id JOIN songs on songs_song_id = song_id WHERE users_user_id = ? AND order_statuses_order_statuses_id = 3";
    private static final String FIND_TOTAL_PRICE_BY_ORDER_ID = "SELECT SUM(song_price) as total_price FROM orders JOIN songs_has_orders ON order_id = orders_order_id JOIN songs ON songs_song_id = song_id WHERE order_id = ?";

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> listOfOrders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BigDecimal totalPrice = getTotalOrderPriceByOrderID(resultSet.getLong(ORDER_ID));
                Timestamp dateOfCreation = resultSet.getTimestamp(ORDER_DATE_OF_CREATION);
                String orderStatus = resultSet.getString(ORDER_STATUS);
                Long userId = resultSet.getLong(USER_ID);
                Long songId = resultSet.getLong(SONG_ID);
                listOfOrders.add(Order.builder()
                        .setTotalPrice(totalPrice)
                        .setDateOfCreation(dateOfCreation)
                        .setOrderStatus(Order.OrderStatus.valueOf(orderStatus))
                        .setUserId(userId)
                        .setSongId(songId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all orders", e);
        }
        return listOfOrders;
    }

    @Override
    public Optional<Order> findById(Long orderId) throws DaoException {
        Optional<Order> order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BigDecimal totalPrice = getTotalOrderPriceByOrderID(orderId);
                Timestamp dateOfCreation = resultSet.getTimestamp(ORDER_DATE_OF_CREATION);
                String orderStatus = resultSet.getString(ORDER_STATUS);
                Long userId = resultSet.getLong(USER_ID);
                Long songId = resultSet.getLong(SONG_ID);
                order = Optional.of(Order.builder()
                        .setTotalPrice(totalPrice)
                        .setDateOfCreation(dateOfCreation)
                        .setOrderStatus(Order.OrderStatus.valueOf(orderStatus))
                        .setUserId(userId)
                        .setSongId(songId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding order by id", e);
        }
        return order;
    }

    @Override
    public void insertOrder(Long orderStatusId, Long userId, Long songId, Long orderId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS)) {
            preparedStatement.setTimestamp(1, Timestamp.from(Instant.now()));
            preparedStatement.setLong(2, orderStatusId);
            preparedStatement.setLong(3, userId);
            preparedStatement.executeUpdate();
            insertSongsHasOrders(songId,orderId);
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new order", e);
        }
    }

    @Override
    public List<Order> findAllOrdersByUserName(String userFirstName,String userLastName) throws DaoException {
        List<Order> listOfOrders = new ArrayList<>();
        Order order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS_BY_USER_NAME)) {
            preparedStatement.setString(1, userFirstName);
            preparedStatement.setString(2,userLastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BigDecimal bigDecimal = getTotalOrderPriceByOrderID(resultSet.getLong(ORDER_ID));
                Timestamp dateOfCreation = resultSet.getTimestamp(ORDER_DATE_OF_CREATION);
                String orderStatus = resultSet.getString(ORDER_STATUS);
                Long userId = resultSet.getLong(USER_ID);
                Long songId = resultSet.getLong(SONG_ID);
                order = Order.builder()
                        .setTotalPrice(bigDecimal)
                        .setDateOfCreation(dateOfCreation)
                        .setOrderStatus(Order.OrderStatus.valueOf(orderStatus))
                        .setUserId(userId)
                        .setSongId(songId)
                        .build();
                listOfOrders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding order by user name", e);
        }
        return listOfOrders;
    }

    @Override
    public void cancelByUserId(Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CANCEL_ORDER_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, canceling order by user id", e);
        }
    }

    @Override
    public List<Order> findCanceledOrdersByUserId(Long userId) throws DaoException {
        List<Order> listOfOrders = new ArrayList<>();
        Order order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_CANCELED_ORDERS_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                BigDecimal bigDecimal = getTotalOrderPriceByOrderID(resultSet.getLong(ORDER_ID));
                Timestamp dateOfCreation = resultSet.getTimestamp(ORDER_DATE_OF_CREATION);
                String orderStatus = resultSet.getString(ORDER_STATUS);
                Long songId = resultSet.getLong(SONG_ID);
                order = Order.builder()
                        .setDateOfCreation(dateOfCreation)
                        .setOrderStatus(Order.OrderStatus.valueOf(orderStatus))
                        .setUserId(userId)
                        .setSongId(songId)
                        .build();
                listOfOrders.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding canceled orders", e);
        }
        return listOfOrders;
    }

    public BigDecimal getTotalOrderPriceByOrderID(Long orderId) throws DaoException{
        BigDecimal bigDecimal = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOTAL_PRICE_BY_ORDER_ID)) {
            preparedStatement.setLong(1,orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                bigDecimal = resultSet.getBigDecimal(ORDER_TOTAL_PRICE);
            }
        }catch (SQLException e){
            throw new DaoException("SQLException, getting total order's price",e);
        }
        return bigDecimal;
    }

    private void insertSongsHasOrders(Long songId, Long orderId) throws DaoException{
        try(PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SONGS_HAS_ORDERS)) {
            preparedStatement.setLong(1,songId);
            preparedStatement.setLong(2,orderId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, inserting into table songs_has_orders");
        }
    }

}
