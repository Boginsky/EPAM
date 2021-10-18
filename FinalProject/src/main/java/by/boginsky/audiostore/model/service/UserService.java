package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAllUsers() throws ServiceException;

    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    User findUserById(Long userId) throws ServiceException;

    User findUserByEmail(String email) throws ServiceException;

    void updateUserEmail(String email, String newEmail) throws ServiceException;

    void updateUserMoney(Long userId, BigDecimal userMoney) throws ServiceException;

    void updateUserName(String firstName, String lastName, Long userId) throws ServiceException;

    void createUser(User user, String encryptedPassword) throws ServiceException;

    void createAdmin(User user, String password) throws ServiceException;

    void updateUserPassword(String password, Long userId) throws ServiceException;

    String findPasswordByUserId(Long userId) throws ServiceException;

    void updateUserBonus(Long userId, BigDecimal bonus) throws ServiceException;

    void removeUser(Long userId) throws ServiceException;

    void changeUserStatus(Long userId, String userStatus) throws ServiceException;

}
