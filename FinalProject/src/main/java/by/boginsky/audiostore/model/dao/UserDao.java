package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface User dao.
 */
public interface UserDao {

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<User> findAll() throws DaoException;

    /**
     * Find user by email optional.
     *
     * @param email the email
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmail(String email) throws DaoException;

    /**
     * Find user by id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserById(Long userId) throws DaoException;

    /**
     * Update user email.
     *
     * @param email    the email
     * @param newEmail the new email
     * @throws DaoException the dao exception
     */
    void updateUserEmail(String email, String newEmail) throws DaoException;

    /**
     * Update user money.
     *
     * @param userId    the user id
     * @param userMoney the user money
     * @throws DaoException the dao exception
     */
    void updateUserMoney(Long userId, BigDecimal userMoney) throws DaoException;

    /**
     * Create user.
     *
     * @param user     the user
     * @param password the password
     * @throws DaoException the dao exception
     */
    void createUser(User user, String password) throws DaoException;

    /**
     * Create admin.
     *
     * @param user     the user
     * @param password the password
     * @throws DaoException the dao exception
     */
    void createAdmin(User user, String password) throws DaoException;

    /**
     * Update user name.
     *
     * @param firstName the first name
     * @param lastName  the last name
     * @param userId    the user id
     * @throws DaoException the dao exception
     */
    void updateUserName(String firstName, String lastName, Long userId) throws DaoException;

    /**
     * Update user bonus.
     *
     * @param bonus  the bonus
     * @param userId the user id
     * @throws DaoException the dao exception
     */
    void updateUserBonus(BigDecimal bonus, Long userId) throws DaoException;

    /**
     * Find user by email and password optional.
     *
     * @param email             the email
     * @param encryptedPassword the encrypted password
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<User> findUserByEmailAndPassword(String email, String encryptedPassword) throws DaoException;

    /**
     * Find password by user id optional.
     *
     * @param userId the user id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<String> findPasswordByUserId(Long userId) throws DaoException;

    /**
     * Update user password.
     *
     * @param password the password
     * @param userId   the user id
     * @throws DaoException the dao exception
     */
    void updateUserPassword(String password, Long userId) throws DaoException;

    /**
     * Update user photo.
     *
     * @param userImageUrl the user image url
     * @param userId       the user id
     * @throws DaoException the dao exception
     */
    void updateUserPhoto(String userImageUrl, Long userId) throws DaoException;

    /**
     * Remove user.
     *
     * @param userId the user id
     * @throws DaoException the dao exception
     */
    void removeUser(Long userId) throws DaoException;

    /**
     * Block user.
     *
     * @param userId the user id
     * @throws DaoException the dao exception
     */
    void blockUser(Long userId) throws DaoException;

    /**
     * Unblock user.
     *
     * @param userId the user id
     * @throws DaoException the dao exception
     */
    void unblockUser(Long userId) throws DaoException;

}
