package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.dao.OrderDao;
import by.boginsky.audiostore.model.entity.user.Order;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {

    private static final String INSERT_INTO_ORDERS = "INSERT INTO orders (order_date,order_statuses_order_statuses_id,users_user_id) VALUES(?,?,?)";
    private static final String INSERT_INTO_SONGS_HAS_ORDERS = "INSERT INTO songs_has_orders (songs_song_id,orders_order_id) VALUES(?,?)";
    private static final String FIND_ALL_ORDERS_BY_USER_ID = "SELECT order_id,order_date,order_status FROM orders JOIN orders_statuses ON order_statuses_order_statuses_id = order_statuses_id JOIN users ON users_user_id = user_id WHERE user_id = ?";
    private static final String FIND_TOTAL_PRICE_BY_ORDER_ID = "SELECT SUM(song_price) as total_price FROM orders JOIN songs_has_orders ON order_id = orders_order_id JOIN songs ON songs_song_id = song_id WHERE order_id = ?";
    private static final String FIND_SONG_ID_BY_ORDER_ID = "SELECT song_id FROM songs JOIN songs_has_orders on song_id = songs_song_id JOIN orders on orders_order_id = order_id WHERE order_id = ?";

    @Override
    public void insertOrder(Long userId, List<Long> songsId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_ORDERS, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setTimestamp(1, Timestamp.from(Instant.now()));
            preparedStatement.setLong(2, 1);
            preparedStatement.setLong(3, userId);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                Long orderId = resultSet.getLong(1);
                insertSongsHasOrders(songsId, orderId);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new order", e);
        }
    }

    @Override
    public List<Order> findAllOrdersByUserId(Long userId) throws DaoException {
        List<Order> listOfOrders = new ArrayList<>();
        Order order = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ORDERS_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long orderId = resultSet.getLong(ORDER_ID);
                BigDecimal totalPrice = getTotalOrderPriceByOrderID(orderId);
                Timestamp dateOfCreation = resultSet.getTimestamp(ORDER_DATE_OF_CREATION);
                String orderStatus = resultSet.getString(ORDER_STATUS);
                List<Long> songId = getSongsIdsForOrder(resultSet.getLong(ORDER_ID));
                listOfOrders.add(Order.builder()
                        .setId(orderId)
                        .setTotalPrice(totalPrice)
                        .setDateOfCreation(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setOrderStatus(Order.OrderStatus.valueOf(orderStatus))
                        .setUserId(userId)
                        .setSongs(songId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding order by user id", e);
        }
        return listOfOrders;
    }

    private BigDecimal getTotalOrderPriceByOrderID(Long orderId) throws DaoException {
        BigDecimal bigDecimal = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_TOTAL_PRICE_BY_ORDER_ID)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bigDecimal = resultSet.getBigDecimal(ORDER_TOTAL_PRICE);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, getting total order's price", e);
        }
        return bigDecimal;
    }

    private void insertSongsHasOrders(List<Long> songsId, Long orderId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_SONGS_HAS_ORDERS)) {
            for (Long songId : songsId) {
                preparedStatement.setLong(1, songId);
                preparedStatement.setLong(2, orderId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting into table songs_has_orders");
        }
    }

    private List<Long> getSongsIdsForOrder(Long orderId) throws DaoException {
        List<Long> listOfSongsId = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_SONG_ID_BY_ORDER_ID)) {
            preparedStatement.setLong(1, orderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long song_id = resultSet.getLong(SONG_ID);
                listOfSongsId.add(song_id);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException in method getting song's ids", e);
        }
        return listOfSongsId;
    }
}
