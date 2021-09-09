package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.AuthorDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Author;
import by.boginsky.audiostore.model.service.AuthorService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class AuthorServiceImpl implements AuthorService {

    private static AuthorService instance;
    private static final AtomicBoolean isAuthorService = new AtomicBoolean(false);

    private AuthorServiceImpl() {
    }

    public static AuthorService getInstance() {
        while (instance == null) {
            if (isAuthorService.compareAndSet(false, true)) {
                instance = new AuthorServiceImpl();
            }
        }
        return instance;
    }


    public List<Author> findAllAuthors() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            return authorDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all authors", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public Optional<Author> findAuthorByName(String firstNameOfAuthor, String lastNameOfAuthor) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            return authorDaoImpl.findAuthorByName(firstNameOfAuthor, lastNameOfAuthor);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding author by name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public void addNewAuthor(String firstNameOfAuthor, String lastNameOfAuthor, String informationAboutAuthor, LocalDateTime dateOfBirth) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            authorDaoImpl.insertAuthor(firstNameOfAuthor, lastNameOfAuthor, informationAboutAuthor, Timestamp.valueOf(dateOfBirth));
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method adding new author(rollback)", daoException);
            }
            throw new ServiceException("Exception in method adding new author", e);
        } finally {
            transactionManager.endTransaction();
        }
    }
}
