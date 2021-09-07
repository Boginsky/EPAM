package by.boginsky.audiostore.model.service.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.dao.TransactionManager;
import by.boginsky.audiostore.model.dao.impl.CommentDaoImpl;
import by.boginsky.audiostore.model.entity.audio.Comment;
import by.boginsky.audiostore.model.service.CommentService;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

public class CommentServiceImpl implements CommentService {

    private static CommentService instance;
    private static final AtomicBoolean isCommentService = new AtomicBoolean(false);

    private CommentServiceImpl() {
    }

    public static CommentService getInstance(){
        while (instance == null){
            if(isCommentService.compareAndSet(false,true)){
                instance = new CommentServiceImpl();
            }
        }
        return instance;
    }

    public List<Comment> findAllComments() throws ServiceException {
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

    public List<Comment> findCommentsByUserName(String userFirstName, String userLastName) throws ServiceException {
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

    public List<Comment> findCommentBySongName(String songName) throws ServiceException {
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

    public void addNewComment(String comment, Long songId, Long userId) throws ServiceException {
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

    public Optional<Comment> findCommentById(Long commentId) throws ServiceException{
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
