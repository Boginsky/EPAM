package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.UserDaoImpl;
import by.boginsky.audiostore.model.entity.user.User;
import by.boginsky.audiostore.model.service.UserService;
import by.boginsky.audiostore.util.PasswordEncryption;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class UserServiceImpl implements UserService {

    private static UserService instance;
    private static final AtomicBoolean isUserService = new AtomicBoolean(false);

    private UserServiceImpl(){}

    public static UserService getInstance(){
        while(instance == null){
            if(isUserService.compareAndSet(false,true)){
                instance = new UserServiceImpl();
            }
        }
        return instance;
    }

    public List<User> findAllUsers() throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findAll();
        }catch (DaoException e){
            throw new ServiceException("Service exception in method finding users",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        password = PasswordEncryption.encryptsPassword(password);
        try {
            transactionManager.startTransaction(userDaoImpl);
            Optional<User> optionalUser = userDaoImpl.findUserByEmailAndPassword(email,password);
            return userDaoImpl.findUserByEmailAndPassword(email, password);
        }catch (DaoException e){
            throw new ServiceException("Service exception in method finding user by email and password",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public Optional<Long> findUserByEmail(String email) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findUserByEmail(email);
        }catch (DaoException e){
            throw new ServiceException("Service exception in method finding user by email",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public void updateUserPasswordByEmail(String email,String newPassword) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        newPassword = PasswordEncryption.encryptsPassword(newPassword);
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.updateUserPasswordByEmail(email,newPassword);
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
            userDaoImpl.updateUserEmail(newEmail,email);
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

    public void addUserMoney(String email, BigDecimal additionSum, BigDecimal userMoney) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.addUserMoney(email,additionSum,userMoney);
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

    public void createUser(User user,String encryptedPassword) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.createUser(user,encryptedPassword);
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
}
