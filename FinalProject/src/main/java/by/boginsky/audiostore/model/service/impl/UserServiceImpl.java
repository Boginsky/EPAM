package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.UserDaoImpl;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.util.PasswordEncryption;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserServiceImpl implements UserService {

    private static UserService instance;
    private static final AtomicBoolean isUserService = new AtomicBoolean(false);

    private UserServiceImpl() {
    }

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
            Optional<User> optionalUser = userDaoImpl.findUserByEmailAndPassword(email, password);
            return userDaoImpl.findUserByEmailAndPassword(email, password);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding user by email and password", e);
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
            return userDaoImpl.findPasswordByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding user's password by user id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public String findUserPhotoImageUrl(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findUserPhotoImageUrl(userId);
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding user's imageUrl by user id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void updateUserBonus(Long userId,BigDecimal bonus) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserBonus(bonus,userId);
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

    public User findUserByEmail(String email) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        Optional<User> user = Optional.empty();
        try {
            transactionManager.startTransaction(userDaoImpl);
            user = userDaoImpl.findUserByEmail(email);
            if (user.isPresent()) {
                return user.get();
            } else {
                return user.get();
            }
        } catch (DaoException e) {
            throw new ServiceException("Service exception in method finding user by email", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public void updateUserPasswordByEmail(String email, String newPassword) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        newPassword = PasswordEncryption.encryptsPassword(newPassword);
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserPasswordByEmail(email, newPassword);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating user's password by email(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating user's password by email", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public void updateUserEmail(String email, String newEmail) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserEmail(newEmail, email);
            transactionManager.commit();
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

    @Override
    public boolean updateUserPhoto(InputStream inputStream, String path, Long userId) throws ServiceException {
        boolean flag = false;
        try {
            byte[] bytesForFile = new byte[inputStream.available()];
            inputStream.read(bytesForFile);
            FileOutputStream fileOutputStream = new FileOutputStream(path);
            fileOutputStream.write(bytesForFile);
            fileOutputStream.flush();
            fileOutputStream.close();
            flag = true;
            updateUserPhoto(path, userId);
        }catch (IOException e) {
            throw new ServiceException("Exception in method update user photo",e);
        }
        return flag;
    }


    private void updateUserPhoto(String path, Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            path = "." + path.replace("\\", "/").substring(53);
            userDaoImpl.updateUserPhoto(path, userId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating user's photo(rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating user's photo", e);
        } finally {
            transactionManager.endTransaction();
        }
    }
}
