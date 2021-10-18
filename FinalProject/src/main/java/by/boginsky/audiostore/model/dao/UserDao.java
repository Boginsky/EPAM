package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserDao {

    List<User> findAll() throws DaoException;

    Optional<User> findUserByEmail(String email) throws DaoException;

    User findUserById(Long userId) throws DaoException;

    void updateUserEmail(String email, String newEmail) throws DaoException;

    void updateUserMoney(Long userId, BigDecimal userMoney) throws DaoException;

    void createUser(User user, String password) throws DaoException;

    void createAdmin(User user, String password) throws DaoException;

    void updateUserName(String firstName, String lastName, Long userId) throws DaoException;

    void updateUserBonus(BigDecimal bonus, Long userId) throws DaoException;

    Optional<User> findUserByEmailAndPassword(String email, String encryptedPassword) throws DaoException;

    String findPasswordByUserId(Long userId) throws DaoException;

    void updateUserPassword(String password, Long userId) throws DaoException;

    void updateUserPhoto(String userImageUrl, Long userId) throws DaoException;

    void removeUser(Long userId) throws DaoException;

    void blockUser(Long userId) throws DaoException;

    void unblockUser(Long userId) throws DaoException;

}
