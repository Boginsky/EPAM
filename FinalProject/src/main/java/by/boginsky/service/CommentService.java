package by.boginsky.service;

import by.boginsky.exception.DaoException;
import by.boginsky.exception.ServiceException;
import by.boginsky.model.dao.TransactionManager;
import by.boginsky.model.dao.impl.CommentDaoImpl;
import by.boginsky.model.entity.audio.Comment;

import java.util.List;
import java.util.Optional;

public class CommentService {

    private CommentService() {
    }

    public static List<Comment> findAllComments() throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            return commentDaoImpl.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding all comments", e);
        } finally {
            transactionManager.endTransaction();
        }

    }

    public static List<Comment> findCommentsByUserName(String userFirstName, String userLastName) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            return commentDaoImpl.findByUserName(userFirstName, userLastName);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding comment by user's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static List<Comment> findCommentBySongName(String songName) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            return commentDaoImpl.findBySongName(songName);
        } catch (DaoException e) {
            throw new ServiceException("Exception in method finding comment by song's name", e);
        } finally {
            transactionManager.endTransaction();
        }
    }

    public static void addNewComment(String comment, Long songId, Long userId) throws ServiceException {
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            commentDaoImpl.insertComment(comment, songId, userId);
            transactionManager.commit();
        } catch (DaoException e) {
            try {
                transactionManager.rollback();
            } catch (DaoException daoException) {
                throw new ServiceException("Exception in method adding new comment(rollback)", daoException);
            }
            throw new ServiceException("Exception in method adding new comment", e);
        }finally {
            transactionManager.endTransaction();
        }
    }

    public static Optional<Comment> findCommentById(Long commentId) throws ServiceException{
        TransactionManager transactionManager = new TransactionManager();
        CommentDaoImpl commentDaoImpl = new CommentDaoImpl();
        try {
            transactionManager.startTransaction(commentDaoImpl);
            return commentDaoImpl.findById(commentId);
        }catch (DaoException e){
            throw new ServiceException("Exception in method finding comment by id",e);
        }finally {
            transactionManager.endTransaction();
        }
    }
}
