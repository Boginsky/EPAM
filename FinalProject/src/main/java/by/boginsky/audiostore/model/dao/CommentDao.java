package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Comment;
import by.boginsky.audiostore.model.entity.user.User;

import java.util.List;

public interface CommentDao {

    List<Comment> findAll() throws DaoException;

    List<Comment> findCommentsByAlbumId(Long albumId) throws DaoException;

    List<Comment> findCommentsByUserId(Long userId) throws DaoException;

    void insertNewComment(Long albumId,Long userId,String commentMessage) throws DaoException;

    void updatedComment(String commentMessage,Long commentId) throws DaoException;

    void deleteComment(Long commentId) throws DaoException;

}
