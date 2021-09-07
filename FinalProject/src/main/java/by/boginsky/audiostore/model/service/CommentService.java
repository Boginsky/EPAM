package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {
    List<Comment> findAllComments() throws ServiceException;

    List<Comment> findCommentsByUserName(String userFirstName, String userLastName) throws ServiceException;

    List<Comment> findCommentBySongName(String songName) throws ServiceException;

    void addNewComment(String comment, Long songId, Long userId) throws ServiceException;

    Optional<Comment> findCommentById(Long commentId) throws ServiceException;
}
