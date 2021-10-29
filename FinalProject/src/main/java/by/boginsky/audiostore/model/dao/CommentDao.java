package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Comment;

import java.util.List;

/**
 * The interface Comment dao.
 */
public interface CommentDao {

    /**
     * Find comments by album id list.
     *
     * @param albumId the album id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Comment> findCommentsByAlbumId(Long albumId) throws DaoException;

    /**
     * Find comments by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Comment> findCommentsByUserId(Long userId) throws DaoException;

    /**
     * Insert new comment.
     *
     * @param albumId        the album id
     * @param userId         the user id
     * @param commentMessage the comment message
     * @throws DaoException the dao exception
     */
    void insertNewComment(Long albumId, Long userId, String commentMessage) throws DaoException;

    /**
     * Updated comment.
     *
     * @param commentMessage the comment message
     * @param commentId      the comment id
     * @throws DaoException the dao exception
     */
    void updatedComment(String commentMessage, Long commentId) throws DaoException;

    /**
     * Remove comment.
     *
     * @param commentId the comment id
     * @throws DaoException the dao exception
     */
    void removeComment(Long commentId) throws DaoException;

}
