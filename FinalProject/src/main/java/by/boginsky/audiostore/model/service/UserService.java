package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface User service.
 */
public interface UserService {

    /**
     * Find all users list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<User> findAllUsers() throws ServiceException;

    /**
     * Find user by email and password optional.
     *
     * @param email    the email
     * @param password the password
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException;

    /**
     * Find user by id user.
     *
     * @param userId the user id
     * @return the user
     * @throws ServiceException the service exception
     */
    User findUserById(Long userId) throws ServiceException;

    /**
     * Find user by email user.
     *
     * @param email the email
     * @return the user
     * @throws ServiceException the service exception
     */
    User findUserByEmail(String email) throws ServiceException;

    /**
     * Update user email.
     *
     * @param email    the email
     * @param newEmail the new email
     * @throws ServiceException the service exception
     */
    void updateUserEmail(String email, String newEmail) throws ServiceException;

    /**
     * Update user money.
     *
     * @param userId    the user id
     * @param userMoney the user money
     * @throws ServiceException the service exception
     */
    void updateUserMoney(Long userId, BigDecimal userMoney) throws ServiceException;

    /**
     * Update user name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param userId    the user id
     * @throws ServiceException the service exception
     */
    void updateUserName(String firstName, String lastName, Long userId) throws ServiceException;

    /**
     * Create user.
     *
     * @param user              the user
     * @param encryptedPassword the encrypted password
     * @throws ServiceException the service exception
     */
    void createUser(User user, String encryptedPassword) throws ServiceException;

    /**
     * Create admin.
     *
     * @param user     the user
     * @param password the password
     * @throws ServiceException the service exception
     */
    void createAdmin(User user, String password) throws ServiceException;

    /**
     * Update user password.
     *
     * @param password the password
     * @param userId   the user id
     * @throws ServiceException the service exception
     */
    void updateUserPassword(String password, Long userId) throws ServiceException;

    /**
     * Find password by user id string.
     *
     * @param userId the user id
     * @return the string
     * @throws ServiceException the service exception
     */
    String findPasswordByUserId(Long userId) throws ServiceException;

    /**
     * Update user bonus.
     *
     * @param userId the user id
     * @param bonus  the bonus
     * @throws ServiceException the service exception
     */
    void updateUserBonus(Long userId, BigDecimal bonus) throws ServiceException;

    /**
     * Remove user.
     *
     * @param userId the user id
     * @throws ServiceException the service exception
     */
    void removeUser(Long userId) throws ServiceException;

    /**
     * Change user status.
     *
     * @param userId     the user id
     * @param userStatus the user status
     * @throws ServiceException the service exception
     */
    void changeUserStatus(Long userId, String userStatus) throws ServiceException;

}
