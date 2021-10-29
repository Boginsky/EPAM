package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.UserDaoImpl;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.util.PasswordEncryption;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The type User service.
 */
public class UserServiceImpl implements UserService {

    private static UserService instance;
    private static final AtomicBoolean isUserService = new AtomicBoolean(false);

    private UserServiceImpl() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static UserService getInstance() {
        while (instance == null) {
            if (isUserService.compareAndSet(false, true)) {
                instance = new UserServiceImpl();
            }
        }
        return instance;
    }

    public List<User> findAllUsers() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding users", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        password = PasswordEncryption.encryptsPassword(password);
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findUserByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding user by email and password", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public User findUserById(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findUserById(userId).orElseThrow(ServiceException::new);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding user name by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public String findPasswordByUserId(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findPasswordByUserId(userId).orElseThrow(ServiceException::new);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding user's password by user id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void updateUserBonus(Long userId, BigDecimal bonus) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserBonus(bonus, userId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating user's bonus by id(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating user's bonus by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void removeUser(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.removeUser(userId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method deleting user by id(rollback)", daoException);
            }
            throw new ServiceException("Exception in method deleting user bonus by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void changeUserStatus(Long userId, String userStatus) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            if (userStatus.equalsIgnoreCase(String.valueOf(User.UserStatus.ACTIVE))) {
                userDaoImpl.blockUser(userId);
            } else {
                userDaoImpl.unblockUser(userId);
            }
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method changing user status by user id(rollback)", daoException);
            }
            throw new ServiceException("Exception in method changing user status by user id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public User findUserByEmail(String email) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        Optional<User> user;
        try {
            transactionManager.startTransaction(userDaoImpl);
            user = userDaoImpl.findUserByEmail(email);
            if (user.isPresent()) {
                return user.get();
            } else {
                throw new ServiceException("Exception in method changing user status by user id");
            }
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding user by email", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public void updateUserEmail(String email, String updatedEmail) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            if (isUnique(updatedEmail)) {
                transactionManager.startTransaction(userDaoImpl);
                userDaoImpl.updateUserEmail(updatedEmail, email);
                transactionManager.commit();
            }
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating user's email(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating user's email", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public void updateUserMoney(Long userId, BigDecimal userMoney) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserMoney(userId, userMoney);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating user's money(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating user's money", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void updateUserName(String firstName, String lastName, Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserName(firstName, lastName, userId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating user's name(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating user's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public void createUser(User user, String encryptedPassword) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.createUser(user, encryptedPassword);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method creation new user(rollback)", daoException);
            }
            throw new ServiceException("Exception in method creation new user money", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void createAdmin(User user, String encryptedPassword) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.createAdmin(user, encryptedPassword);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method creation new user(rollback)", daoException);
            }
            throw new ServiceException("Exception in method creation new user money", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void updateUserPassword(String password, Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserPassword(password, userId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating password(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating password", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    private boolean isUnique(String updatedEmail) throws ServiceException {
        boolean flag = true;
        List<User> listOfUsersEmail;
        try {
            listOfUsersEmail = findAllUsers();
            for (User user : listOfUsersEmail) {
                if (user.getEmail().equals(updatedEmail)) {
                    flag = false;
                }
            }
        } catch (ServiceException e) {
            throw new ServiceException("Exception in method is unique email", e);
        }
        return flag;
    }

}
