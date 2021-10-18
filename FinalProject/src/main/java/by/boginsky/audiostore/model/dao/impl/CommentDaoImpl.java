package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.dao.CommentDao;
import by.boginsky.audiostore.model.entity.audio.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static by.boginsky.audiostore.model.dao.ColumnName.*;
import static java.lang.String.format;

public class CommentDaoImpl extends BaseDao<Comment> implements CommentDao {

    private static final String FIND_ALL_COMMENTS = "SELECT users_user_id,first_name,last_name,albums_album_id,comment,user_img,comment_id,album_name FROM comments JOIN users ON users_user_id = user_id JOIN albums ON albums_album_id = album_id";
    private static final String FIND_COMMENTS_BY_ALBUM_ID = "SELECT users_user_id,first_name,last_name,comment,albums_album_id,user_img,comment_id,album_name FROM comments JOIN users ON users_user_id = user_id JOIN albums ON albums_album_id = album_id WHERE albums_album_id = ?";
    private static final String FIND_COMMENTS_BY_USER_ID = "SELECT first_name,last_name,comment,user_img,albums_album_id,comment_id,album_name FROM comments JOIN users ON users_user_id = user_id JOIN albums ON albums_album_id = album_id WHERE user_id = ?";
    private static final String INSERT_NEW_COMMENT = "INSERT INTO  comments (users_user_id,albums_album_id,comment) VALUES(?,?,?)";
    private static final String UPDATE_COMMENT = "UPDATE comments SET comment = ? WHERE comment_id = ?";
    private static final String REMOVE_COMMENT = "DELETE FROM comments WHERE comment_id = ?";

    @Override
    public List<Comment> findCommentsByAlbumId(Long albumId) throws DaoException {
        List<Comment> listOfComments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENTS_BY_ALBUM_ID)) {
            preparedStatement.setLong(1, albumId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String userFirstName = resultSet.getString(USER_FIRST_NAME);
                String userLastName = resultSet.getString(USER_LAST_NAME);
                String commentMessage = resultSet.getString(COMMENT_MESSAGE);
                String albumName = resultSet.getString(ALBUM_NAME);
                Long commentId = resultSet.getLong(COMMENT_ID);
                Long userId = resultSet.getLong(COMMENT_USER_ID);
                String userImageUrl = resultSet.getString(USER_IMG);
                listOfComments.add(Comment.builder()
                        .setCommentMessage(commentMessage)
                        .setAlbumName(albumName)
                        .setAlbumId(albumId)
                        .setUserId(userId)
                        .setId(commentId)
                        .setUserName(format("%s %s",userFirstName,userLastName))
                        .setUserImageUrl(userImageUrl)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all comments by album id", e);
        }
        return listOfComments;
    }

    @Override
    public List<Comment> findCommentsByUserId(Long userId) throws DaoException {
        List<Comment> listOfComments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENTS_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String userFirstName = resultSet.getString(USER_FIRST_NAME);
                String userLastName = resultSet.getString(USER_LAST_NAME);
                String commentMessage = resultSet.getString(COMMENT_MESSAGE);
                String albumName = resultSet.getString(ALBUM_NAME);
                Long albumId = resultSet.getLong(COMMENT_ALBUM_ID);
                Long commentId = resultSet.getLong(COMMENT_ID);
                String userImageUrl = resultSet.getString(USER_IMG);
                listOfComments.add(Comment.builder()
                        .setCommentMessage(commentMessage)
                        .setAlbumName(albumName)
                        .setId(commentId)
                        .setUserId(userId)
                        .setAlbumId(albumId)
                        .setUserName(format("%s %s",userFirstName,userLastName))
                        .setUserImageUrl(userImageUrl)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all comments by user id", e);
        }
        return listOfComments;
    }

    @Override
    public void insertNewComment(Long albumId, Long userId, String commentMessage) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NEW_COMMENT)) {
            preparedStatement.setLong(1, userId);
            preparedStatement.setLong(2, albumId);
            preparedStatement.setString(3, commentMessage);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new comment", e);
        }
    }

    @Override
    public void updatedComment(String commentMessage, Long commentId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_COMMENT)) {
            preparedStatement.setString(1, commentMessage);
            preparedStatement.setLong(2, commentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, updating comment", e);
        }
    }

    @Override
    public void removeComment(Long commentId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_COMMENT)) {
            preparedStatement.setLong(1, commentId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException,removing comment", e);
        }
    }
}
