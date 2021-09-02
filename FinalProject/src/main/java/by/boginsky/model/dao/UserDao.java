package by.boginsky.model.dao;

import by.boginsky.exception.DaoException;
import by.boginsky.model.entity.user.User;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

public interface UserDao {

    Optional<Long> findUserByEmail(String email) throws DaoException;

    void updateUserPasswordByEmail(String email,String newPassword) throws DaoException;

    void updateUserEmail(String email, String newEmail) throws DaoException;

    void addUserMoney(String email, BigDecimal additionSum, BigDecimal userMoney) throws DaoException;

    void createUser(String email, String password, String firstName, String lastName, Timestamp dateOfCreation) throws DaoException;

    public Optional<User> findUserByEmailAndPassword(String email,String password) throws DaoException;
}
