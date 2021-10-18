package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.CommentDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Comment;
import by.boginsky.audiostore.model.service.CommentService;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommentServiceImpl implements CommentService {

    private static CommentService instance;
    private static final AtomicBoolean isCommentService = new AtomicBoolean(false);

    private CommentServiceImpl() {
    }

    public static CommentService getInstance() {
        while (instance == null) {
            if (isCommentService.compareAndSet(false, true)) {
                instance = new CommentServiceImpl();
            }
        }
        return instance;
    }

    @Override
    public List<Comment> findCommentsByAlbumId(Long albumId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            return commentDaoImpl.findCommentsByAlbumId(albumId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all comments by album id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void insertNewComment(Long albumId, Long userId, String commentMessage) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            commentDaoImpl.insertNewComment(albumId, userId, commentMessage);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method adding new comment (rollback)", daoException);
            }
            throw new ServiceException("Exception in method adding new comment", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void updatedComment(String commentMessage, Long commentId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            commentDaoImpl.updatedComment(commentMessage, commentId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method updating comment (rollback)", daoException);
            }
            throw new ServiceException("Exception in method updating comment", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public void removeComment(Long commentId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            commentDaoImpl.removeComment(commentId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method deleting comment (rollback)", daoException);
            }
            throw new ServiceException("Exception in method deleting comment", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    @Override
    public List<Comment> findCommentsByUserId(Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            return commentDaoImpl.findCommentsByUserId(userId);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding comment by user id", e);
        } finally {
            transactionManager.endTransaction();
        }
    }
}
