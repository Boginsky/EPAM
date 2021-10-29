package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Comment;

import java.util.List;

/**
 * The interface Comment service.
 */
public interface CommentService {

    /**
     * Find comments by album id list.
     *
     * @param albumId the album id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findCommentsByAlbumId(Long albumId) throws ServiceException;

    /**
     * Insert new comment.
     *
     * @param albumId        the album id
     * @param userId         the user id
     * @param commentMessage the comment message
     * @throws ServiceException the service exception
     */
    void insertNewComment(Long albumId, Long userId, String commentMessage) throws ServiceException;

    /**
     * Updated comment.
     *
     * @param commentMessage the comment message
     * @param commentId      the comment id
     * @throws ServiceException the service exception
     */
    void updatedComment(String commentMessage, Long commentId) throws ServiceException;

    /**
     * Remove comment.
     *
     * @param commentId the comment id
     * @throws ServiceException the service exception
     */
    void removeComment(Long commentId) throws ServiceException;

    /**
     * Find comments by user id list.
     *
     * @param userId the user id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Comment> findCommentsByUserId(Long userId) throws ServiceException;
}
