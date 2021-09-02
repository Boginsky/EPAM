package by.boginsky.model.dao;

import by.boginsky.exception.DaoException;
import by.boginsky.model.entity.audio.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> findByUserName(String userFirstName, String userLastName) throws DaoException;

    List<Comment> findBySongName(String songName) throws DaoException;

    void insertComment(String comment, Long songId,Long userId) throws DaoException;
}
