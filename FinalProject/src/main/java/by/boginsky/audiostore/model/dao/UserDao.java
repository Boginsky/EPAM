package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public interface UserDao {

    Optional<Long> findUserByEmail(String email) throws DaoException;

    void updateUserPasswordByEmail(String email,String newPassword) throws DaoException;

    void updateUserEmail(String email, String newEmail) throws DaoException;

    void addUserMoney(String email, BigDecimal additionSum, BigDecimal userMoney) throws DaoException;

    void createUser(User user, String password) throws DaoException;

    public Optional<User> findUserByEmailAndPassword(String email,String encryptedPassword) throws DaoException;
}
