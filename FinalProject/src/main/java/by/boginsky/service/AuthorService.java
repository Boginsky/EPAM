package by.boginsky.service;

import by.boginsky.exception.DaoException;
import by.boginsky.exception.ServiceException;
import by.boginsky.model.dao.TransactionManager;
import by.boginsky.model.dao.impl.AuthorDaoImpl;
import by.boginsky.model.entity.audio.Author;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public class AuthorService {

    private AuthorService(){}

    public static List<Author> findAllAuthors() throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            return authorDaoImpl.findAll();
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding all authors",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Author> findAuthorByName(String firstNameOfAuthor, String lastNameOfAuthor) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            return authorDaoImpl.findAuthorByName(firstNameOfAuthor,lastNameOfAuthor);
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding author by name",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static void addNewAuthor(String firstNameOfAuthor, String lastNameOfAuthor, String informationAboutAuthor, Timestamp dateOfBirth) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            authorDaoImpl.insertAuthor(firstNameOfAuthor,lastNameOfAuthor,informationAboutAuthor,dateOfBirth);
            transactionManager.commit();
        }catch (DaoException e){
            try {
                transactionManager.rollback();
            }catch (DaoException daoException){
                throw new ServiceException("Exception in method adding new author(rollback)",daoException);
            }
            throw new ServiceException("Exception in method adding new author",e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Author> findAuthorById(Long authorId) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            return authorDaoImpl.findById(authorId);
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding author by id",e);
        }finally {
            transactionManager.endTransaction();
        }
    }


}
