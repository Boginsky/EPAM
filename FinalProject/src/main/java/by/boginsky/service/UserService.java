package by.boginsky.service;

import by.boginsky.exception.DaoException;
import by.boginsky.exception.ServiceException;
import by.boginsky.model.dao.TransactionManager;
import by.boginsky.model.dao.impl.UserDaoImpl;
import by.boginsky.model.entity.user.User;
import by.boginsky.util.PasswordEncryption;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class UserService {

    private UserService(){}

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

    public Optional<User> findById(Long userId) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findById(userId);
        }catch (DaoException e){
            throw new ServiceException("Service exception in method finding user by id",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<User> findUserByEmailAndPassword(String email, String password) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        password = PasswordEncryption.encryptsPassword(password);
        try {
            transactionManager.startTransaction(userDaoImpl);
            return userDaoImpl.findUserByEmailAndPassword(email, password);
        }catch (DaoException e){
            throw new ServiceException("Service exception in method finding user by email and password",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Long> findUserByEmail(String email) throws ServiceException{
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

    public static void updateUserPasswordByEmail(String email,String newPassword) throws ServiceException{
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

    public static void updateUserEmail(String email, String newEmail) throws ServiceException {
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

    public static void addUserMoney(String email, BigDecimal additionSum, BigDecimal userMoney) throws ServiceException{
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

    public static void createUser(String email, String password, String firstName, String lastName, Timestamp dateOfCreation) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        UserDaoImpl userDaoImpl = new UserDaoImpl();
        try {
            transactionManager.startTransaction(userDaoImpl);
            userDaoImpl.createUser(email, password, firstName, lastName, dateOfCreation);
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
