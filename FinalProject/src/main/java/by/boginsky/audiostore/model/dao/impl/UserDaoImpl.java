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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

public class UserDaoImpl extends BaseDao implements UserDao {

    private static final String FIND_ALL_USERS = "SELECT email,first_name,last_name,user_created,balance,user_role,user_status FROM users JOIN user_roles ON user_roles_user_role_id = user_role_id JOIN user_statuses ON user_statuses_user_status_id = user_status_id";
    private static final String FIND_USER_BY_ID = "SELECT email,first_name,last_name,user_created,balance,user_role,user_status FROM users JOIN user_roles ON user_roles_user_role_id = user_role_id JOIN user_statuses ON user_statuses_user_status_id = user_status_id WHERE user_id = ?";
    private static final String FIND_USER_BY_EMAIL = "SELECT user_id FROM users WHERE email = ?";
    private static final String UPDATE_USER_PASSWORD_BY_EMAIL = "UPDATE users SET password = ? WHERE email = ?";
    private static final String UPDATE_USER_EMAIL = "UPDATE users SET email = ? WHERE email = ?";
    private static final String UPDATE_USER_MONEY = "UPDATE users SET balance = ? WHERE email = ?";
    private static final String INSERT_INTO_USERS_NEW_USER = "INSERT INTO users (email,password,first_name,last_name,user_created,balance,user_roles_user_role_id,user_statuses_user_status_id) VALUES(?,?,?,?,?,?,?,?)";
    private static final String FIND_USER_BY_EMAIL_AND_PASSWORD = "SELECT first_name,last_name,user_created,balance,user_role,user_status FROM users JOIN user_roles ON user_roles_user_role_id = user_role_id JOIN user_statuses ON user_statuses_user_status_id = user_status_id WHERE email = ? AND password = ?";

    @Override
    public List<User> findAll() throws DaoException {
        List<User> listOfUsers = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_USERS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String email = resultSet.getString(USER_EMAIL);
                String firstName = resultSet.getString(USER_FIRST_NAME);
                String lastName = resultSet.getString(USER_LAST_NAME);
                Timestamp dateOfCreation = resultSet.getTimestamp(USER_DATE_OF_CREATION);
                BigDecimal balance = resultSet.getBigDecimal(USER_BALANCE);
                User.UserRole userRole = User.UserRole.valueOf(resultSet.getString(USER_ROLE));
                User.UserStatus userStatus = User.UserStatus.valueOf(resultSet.getString(USER_STATUS));
                listOfUsers.add(User.builder()
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserCreated(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setBalance(balance)
                        .setUserRole(userRole)
                        .setUserStatus(userStatus)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all users", e);
        }
        return listOfUsers;
    }

    public Optional<User> findUserByEmailAndPassword(String email,String password) throws DaoException{
        Optional<User> user = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString(USER_FIRST_NAME);
                String lastName = resultSet.getString(USER_LAST_NAME);
                Timestamp dateOfCreation = resultSet.getTimestamp(USER_DATE_OF_CREATION);
                BigDecimal balance = resultSet.getBigDecimal(USER_BALANCE);
                User.UserRole userRole = User.UserRole.valueOf(resultSet.getString(USER_ROLE));
                User.UserStatus userStatus = User.UserStatus.valueOf(resultSet.getString(USER_STATUS));
                user = Optional.of(User.builder()
                        .setEmail(email)
                        .setFirstName(firstName)
                        .setLastName(lastName)
                        .setUserCreated(dateOfCreation.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime())
                        .setBalance(balance)
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
    public Optional<Long> findUserByEmail(String email) throws DaoException {
        Optional<Long> userId = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                userId = Optional.of(resultSet.getLong(USER_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching user by email", e);
        }
        return userId;
    }

    @Override
    public void updateUserPasswordByEmail(String email, String newPassword) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_PASSWORD_BY_EMAIL)) {
            preparedStatement.setString(1,newPassword);
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, updating password by email");
        }
    }

    @Override
    public void updateUserEmail(String newEmail, String email) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_EMAIL)) {
            preparedStatement.setString(1,newEmail);
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, updating email");
        }
    }

    @Override
    public void addUserMoney(String email, BigDecimal additionSum, BigDecimal userMoney) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_MONEY)) {
            preparedStatement.setBigDecimal(1, additionSum.add(userMoney));
            preparedStatement.setString(2,email);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, updating user's balance",e);
        }
    }

    @Override
    public void createUser(String email, String password, String firstName, String lastName, Timestamp dateOfCreation) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_USERS_NEW_USER)){
            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3,firstName);
            preparedStatement.setString(4,lastName);
            preparedStatement.setTimestamp(5,dateOfCreation);
            preparedStatement.setBigDecimal(6, BigDecimal.valueOf(0));
            preparedStatement.setLong(7,2);
            preparedStatement.setLong(8,2);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, creating new user",e);
        }
    }
}
