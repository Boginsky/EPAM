package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> findCommentsByAlbumId(Long albumId) throws ServiceException;

    void insertNewComment(Long albumId,Long userId,String commentMessage) throws ServiceException;

    void updatedComment(String commentMessage,Long commentId) throws ServiceException;

    void removeComment(Long commentId) throws ServiceException;

    List<Comment> findCommentsByUserId(Long userId) throws ServiceException;
}
