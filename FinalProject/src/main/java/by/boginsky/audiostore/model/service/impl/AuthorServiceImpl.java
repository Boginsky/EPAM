package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.AuthorDao;
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
    private static final Long MULTIPLIER = 2L;
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


    @Override
    public Optional<Author> findById(Long authorId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            return authorDaoImpl.findById(authorId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding author by id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public List<Author> findAllAuthors(Long pageId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        Long startPosition = --pageId * MULTIPLIER;
        try {
            transactionManager.startTransaction(authorDaoImpl);
            return authorDaoImpl.findAll(startPosition);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all authors for page", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
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

    public Long addNewAuthor(String nameOfAuthor, String informationAboutAuthor) throws ServiceException {
        Long id;
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            id = authorDaoImpl.insertAuthor(nameOfAuthor, informationAboutAuthor);
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
        return id;
    }

    @Override
    public void removeAuthor(Long authorId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            authorDaoImpl.removeAuthor(authorId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method removing author(rollback)", daoException);
            }
            throw new ServiceException("Exception in method removing author", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void updateAuthor(Long authorId, String authorName, String authorInfo) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        AuthorDaoImpl authorDaoImpl = new AuthorDaoImpl();
        try {
            transactionManager.startTransaction(authorDaoImpl);
            authorDaoImpl.updateAuthor(authorId,authorName,authorInfo);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method changing author(rollback)", daoException);
            }
            throw new ServiceException("Exception in method changing author", e);
        } finally {
            transactionManager.endTransaction();
        }
    }
}
