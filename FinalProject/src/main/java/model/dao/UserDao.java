package model.dao;

import exception.DaoException;
import model.entity.user.User;

import java.math.BigDecimal;

public interface UserDao {

    User findUserByEmailAndPassword(String email, String password) throws DaoException;

    boolean findUserByEmail(String email) throws DaoException;

    boolean updateUserPassword(String email,String newPassword) throws DaoException;

    boolean updateUserEmail(User user, String newEmail) throws DaoException;

    boolean updateUserMoney(Long clientId, BigDecimal price, BigDecimal clientMoney) throws DaoException;

    boolean isEnoughMoney(BigDecimal clientMoney,Long orderId) throws DaoException;
}
