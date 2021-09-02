package by.boginsky.model.dao.impl;

import by.boginsky.exception.DaoException;
import by.boginsky.model.dao.BaseDao;
import by.boginsky.model.dao.CommentDao;
import by.boginsky.model.entity.audio.Comment;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.model.dao.ColumnName.*;

public class CommentDaoImpl extends BaseDao implements CommentDao {

    private static final String FIND_ALL_COMMENTS = "SELECT comment,users_user_id,songs_song_id FROM comments";
    private static final String FIND_COMMENT_BY_ID = "SELECT comment,users_user_id,songs_song_id FROM comments WHERE comment_id = ?";
    private static final String FIND_COMMENT_BY_USER_NAME = "SELECT comment,songs_song_id,users_user_id FROM comments JOIN users ON users_user_id = user_id WHERE first_name = ? AND last_name = ?";
    private static final String FIND_COMMENT_BY_SONG_NAME = "SELECT comment,songs_song_id,users_user_id FROM comments JOIN songs ON songs_song_id = song_id WHERE song_name = ?";
    private static final String INSERT_INTO_COMMENTS_NEW_COMMENT = "INSERT INTO comments (comment,songs_song_id,users_user_id) VALUES(?,?,?)";

    @Override
    public List<Comment> findAll() throws DaoException {
        List<Comment> listOfComments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_COMMENTS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String commentMessage = resultSet.getString(COMMENT);
                Long user_id = resultSet.getLong(USERS_USER_ID);
                Long song_id = resultSet.getLong(SONGS_SONG_ID);
                listOfComments.add(Comment.builder()
                        .setCommentMessage(commentMessage)
                        .setUserId(user_id)
                        .setSongId(song_id)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all comments", e);
        }
        return listOfComments;
    }

    @Override
    public Optional<Comment> findById(Long commentId) throws DaoException {
        Optional<Comment> comment = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENT_BY_ID)) {
            preparedStatement.setLong(1, commentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String commentMessage = resultSet.getString(COMMENT);
                Long userId = resultSet.getLong(USERS_USER_ID);
                Long songId = resultSet.getLong(SONGS_SONG_ID);
                comment = Optional.of(Comment.builder()
                        .setCommentMessage(commentMessage)
                        .setUserId(userId)
                        .setSongId(songId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding comment by id", e);
        }
        return comment;
    }


    @Override
    public List<Comment> findByUserName(String userFirstName, String userLastName) throws DaoException {
        List<Comment> listOfComments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENT_BY_USER_NAME)) {
            preparedStatement.setString(1, userFirstName);
            preparedStatement.setString(2, userLastName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String commentMessage = resultSet.getString(COMMENT);
                Long userId = resultSet.getLong(USERS_USER_ID);
                Long songId = resultSet.getLong(SONGS_SONG_ID);
                listOfComments.add(Comment.builder()
                        .setCommentMessage(commentMessage)
                        .setSongId(songId)
                        .setUserId(userId)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException,finding comment by user name",e);
        }
        return listOfComments;
    }

    @Override
    public List<Comment> findBySongName(String songName) throws DaoException {
        List<Comment> listOfComments = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_COMMENT_BY_SONG_NAME)) {
            preparedStatement.setString(1,songName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String commentMessage = resultSet.getString(COMMENT);
                Long songId = resultSet.getLong(USERS_USER_ID);
                Long userId = resultSet.getLong(SONGS_SONG_ID);
                listOfComments.add(Comment.builder()
                        .setCommentMessage(commentMessage)
                        .setSongId(songId)
                        .setUserId(userId)
                        .build());
            }
        }catch (SQLException e){
            throw new DaoException("SQLException, finding comment by song name",e);
        }
        return listOfComments;
    }

    @Override
    public void insertComment(String comment, Long songId, Long userId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_COMMENTS_NEW_COMMENT)) {
            preparedStatement.setString(1,comment);
            preparedStatement.setLong(2,songId);
            preparedStatement.setLong(3,userId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, inserting new comment",e);
        }
    }

}
