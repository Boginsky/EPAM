package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.dao.UserDao;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

/**
 * The type User dao.
 */
public class UserDaoImpl extends BaseDao<User> implements UserDao {

    private static final String FIND_ALL_USERS = "SELECT user_id,user_img,email,first_name,last_name,user_created,balance,bonus,user_role,user_status FROM users JOIN user_roles ON user_roles_user_role_id = user_role_id JOIN user_statuses ON user_statuses_user_status_id = user_status_id WHERE user_roles_user_role_id = 2";
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id,user_img,email,first_name,last_name,user_created,balance,bonus,user_role,user_status FROM users JOIN user_roles ON user_roles_user_role_id = user_role_id JOIN user_statuses ON user_statuses_user_status_id = user_status_id WHERE email = ?";
    private static final String UPDATE_USER_EMAIL = "UPDATE users SET email = ? WHERE email = ?";
    private static final String UPDATE_USER_MONEY = "UPDATE users SET balance = ? WHERE user_id = ?";
    private static final String UPDATE_USER_NAME = "UPDATE users SET first_name = ?,last_name = ? WHERE user_id = ?";
    private static final String CREATE_USER = "INSERT INTO users (email,password,first_name,last_name,user_created,balance,user_roles_user_role_id,user_statuses_user_status_id) VALUES(?,?,?,?,?,?,?,?)";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT user_id,user_img,first_name,last_name,user_created,balance,bonus,user_role,user_status FROM users JOIN user_roles ON user_roles_user_role_id = user_role_id JOIN user_statuses ON user_statuses_user_status_id = user_status_id WHERE email = ? AND password = ?";
    private static final String FIND_PASSWORD_BY_USER_ID = "SELECT password FROM users WHERE user_id = ?";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password = ? WHERE user_id = ?";
    private static final String UPDATE_USER_PHOTO = "UPDATE users SET user_img = ? WHERE user_id = ? ";
    private static final String UPDATE_USER_BONUS = "UPDATE users SET bonus = ? WHERE user_id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";
    private static final String BLOCK_USER = "UPDATE users SET user_statuses_user_status_id = 2 WHERE user_id = ?";
    private static final String UNBLOCK_USER = "UPDATE users SET user_statuses_user_status_id = 1 WHERE user_id = ?";
    private static final String FIND_USER_BY_ID = "SELECT user_id,user_img,email,first_name,last_name,user_created,balance,bonus,user_status FROM users JOIN user_roles ON user_roles_user_role_id = user_role_id JOIN user_statuses ON user_statuses_user_status_id = user_status_id WHERE user_id = ?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> listOfUsers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(USER_ID);
                String email = resultSet.getString(USER_EMAIL);
                String firstName = resultSet.getString(USER_FIRST_NAME);
                String lastName = resultSet.getString(USER_LAST_NAME);
                String imageUrl = resultSet.getString(USER_IMG);
                Timestamp dateOfCreation = resultSet.getTimestamp(USER_DATE_OF_CREATION);
                BigDecimal balance = resultSet.getBigDecimal(USER_BALANCE);
                BigDecimal bonus = resultSet.getBigDecimal(USER_BONUS);
                User.UserRole userRole = User.UserRole.valueOf(resultSet.getString(USER_ROLE));
                User.UserStatus userStatus = User.UserStatus.valueOf(resultSet.getString(USER_STATUS));
                listOfUsers.add(User.builder()
                        .setId(id)
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setImageUrl(imageUrl)
                        .setUserCreated(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setBalance(balance)
                        .setBonus(bonus)
                        .setUserRole(userRole)
                        .setUserStatus(userStatus)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all users", e);
        }
        return listOfUsers;
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(USER_ID);
                String firstName = resultSet.getString(USER_FIRST_NAME);
                String lastName = resultSet.getString(USER_LAST_NAME);
                String imageUrl = resultSet.getString(USER_IMG);
                Timestamp dateOfCreation = resultSet.getTimestamp(USER_DATE_OF_CREATION);
                BigDecimal balance = resultSet.getBigDecimal(USER_BALANCE);
                BigDecimal bonus = resultSet.getBigDecimal(USER_BONUS);
                User.UserRole userRole = User.UserRole.valueOf(resultSet.getString(USER_ROLE));
                User.UserStatus userStatus = User.UserStatus.valueOf(resultSet.getString(USER_STATUS));
                user = Optional.ofNullable(User.builder()
                        .setId(id)
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setImageUrl(imageUrl)
                        .setUserCreated(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setBalance(balance)
                        .setBonus(bonus)
                        .setUserRole(userRole)
                        .setUserStatus(userStatus)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding user by email and password", e);
        }
        return user;
    }

    @Override
    public Optional<String> findPasswordByUserId(Long userId) throws DaoException {
        Optional<String> password = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_PASSWORD_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                password = Optional.ofNullable(resultSet.getString(USER_PASSWORD));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding password by user id", e);
        }
        return password;
    }

    @Override
    public Optional<User> findUserByEmail(String email) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(USER_ID);
                String firstName = resultSet.getString(USER_FIRST_NAME);
                String lastName = resultSet.getString(USER_LAST_NAME);
                String imageUrl = resultSet.getString(USER_IMG);
                Timestamp dateOfCreation = resultSet.getTimestamp(USER_DATE_OF_CREATION);
                BigDecimal balance = resultSet.getBigDecimal(USER_BALANCE);
                BigDecimal bonus = resultSet.getBigDecimal(USER_BONUS);
                User.UserRole userRole = User.UserRole.valueOf(resultSet.getString(USER_ROLE));
                User.UserStatus userStatus = User.UserStatus.valueOf(resultSet.getString(USER_STATUS));
                user = Optional.ofNullable(User.builder()
                        .setId(id)
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setImageUrl(imageUrl)
                        .setUserCreated(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setBalance(balance)
                        .setBonus(bonus)
                        .setUserRole(userRole)
                        .setUserStatus(userStatus)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding user by email", e);
        }
        return user;
    }

    @Override
    public Optional<User> findUserById(Long userId) throws DaoException {
        Optional<User> user = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String firstName = resultSet.getString(USER_FIRST_NAME);
                String lastName = resultSet.getString(USER_LAST_NAME);
                String imageUrl = resultSet.getString(USER_IMG);
                String email = resultSet.getString(USER_EMAIL);
                Timestamp dateOfCreation = resultSet.getTimestamp(USER_DATE_OF_CREATION);
                BigDecimal balance = resultSet.getBigDecimal(USER_BALANCE);
                BigDecimal bonus = resultSet.getBigDecimal(USER_BONUS);
                User.UserStatus userStatus = User.UserStatus.valueOf(resultSet.getString(USER_STATUS));
                user = Optional.ofNullable(User.builder()
                        .setId(userId)
                        .setImageUrl(imageUrl)
                        .setEmail(email)
                        .setBalance(balance)
                        .setBonus(bonus)
                        .setUserCreated(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserStatus(userStatus)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding user name by id", e);
        }
        return user;
    }

    @Override
    public void removeUser(Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, deleting user by user id");
        }
    }

    @Override
    public void blockUser(Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(BLOCK_USER)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, blocking user by user id", e);
        }
    }

    @Override
    public void unblockUser(Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UNBLOCK_USER)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, unblocking user by user id", e);
        }
    }

    @Override
    public void updateUserPassword(String password, Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, password);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating password by user id", e);
        }
    }

    @Override
    public void updateUserPhoto(String userImageUrl, Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PHOTO)) {
            preparedStatement.setString(1, userImageUrl);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating photo by user id", e);
        }
    }

    @Override
    public void updateUserEmail(String newEmail, String email) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL)) {
            preparedStatement.setString(1, newEmail);
            preparedStatement.setString(2, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating email");
        }
    }

    @Override
    public void updateUserMoney(Long userId, BigDecimal userMoney) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_MONEY)) {
            preparedStatement.setBigDecimal(1, userMoney);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating user's balance", e);
        }
    }

    @Override
    public void updateUserName(String firstName, String lastName, Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_NAME)) {
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setLong(3, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating user's name");
        }
    }

    @Override
    public void updateUserBonus(BigDecimal bonus, Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BONUS)) {
            preparedStatement.setBigDecimal(1, bonus);
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating user's bonus");
        }
    }

    @Override
    public void createUser(User user, String encryptedPassword) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setTimestamp(5, Timestamp.from(ZonedDateTime.now().toInstant()));
            preparedStatement.setBigDecimal(6, BigDecimal.valueOf(0));
            preparedStatement.setLong(7, 2);
            preparedStatement.setLong(8, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, creating new user", e);
        }
    }

    @Override
    public void createAdmin(User user, String encryptedPassword) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, encryptedPassword);
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setTimestamp(5, Timestamp.from(ZonedDateTime.now().toInstant()));
            preparedStatement.setBigDecimal(6, BigDecimal.valueOf(0));
            preparedStatement.setLong(7, 1);
            preparedStatement.setLong(8, 1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, creating new user", e);
        }
    }
}
