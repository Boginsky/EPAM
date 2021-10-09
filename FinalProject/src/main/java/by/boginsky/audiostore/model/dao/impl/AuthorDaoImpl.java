package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.AuthorDao;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.entity.audio.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

public class AuthorDaoImpl extends BaseDao implements AuthorDao {

    private static final String INSERT_INTO_AUTHORS = "INSERT INTO authors (author_name,author_info_author_dob) VALUES (?,?,?)"; // FIXME: 29.09.2021
    private static final String FIND_ALL_AUTHORS = "SELECT DISTINCT author_id,author_name,author_info,author_img,genre_name FROM authors JOIN songs ON author_id = authors_author_id JOIN genres ON genres_genre_id = genre_id";
    private static final String FIND_AUTHOR_BY_ID = "SELECT DISTINCT author_id,author_name,author_info,author_img,genre_name FROM authors JOIN songs ON author_id = authors_author_id JOIN genres ON genres_genre_id = genre_id WHERE author_id = ?";
    private static final String FIND_AUTHOR_BY_ALBUM_NAME = "SELECT author_name FROM authors JOIN songs ON author_id = authors_author_id JOIN albums on albums_album_id = album_id WHERE album_name = ?";


    public Optional<String> findAuthorByAlbumName(String albumName) throws DaoException {
        Optional<String> nameOfAuthor = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_ALBUM_NAME)) {
            preparedStatement.setString(1, albumName);
            ResultSet resultSet = preparedStatement.executeQuery();
            String authorName = resultSet.getString(AUTHOR_NAME);
            nameOfAuthor = Optional.ofNullable(authorName);
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching author's name by album's name", e);
        }
        return nameOfAuthor;
    }


    @Override
    public Optional<Author> findById(Long authorId) throws DaoException {
        Optional<Author> author = Optional.empty();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_ID)) {
            preparedStatement.setLong(1, authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String authorName = resultSet.getString(AUTHOR_NAME);
                String informationAboutAuthor = resultSet.getString(AUTHOR_INFO);
                String imageUrl = resultSet.getString(AUTHOR_IMG);
                String genreName = resultSet.getString(GENRE_NAME);
                author = Optional.ofNullable(Author.builder()
                        .setId(authorId)
                        .setName(authorName)
                        .setInformationAboutAuthor(informationAboutAuthor)
                        .setImageUrl(imageUrl)
                        .setGenreName(genreName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding author by id", e);
        }
        return author;
    }


    @Override
    public void insertAuthor(String authorName, String informationAboutAuthor, Timestamp dateOfBirth) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_AUTHORS)) {
            preparedStatement.setString(1, authorName);
            preparedStatement.setString(2, informationAboutAuthor);
            preparedStatement.setTimestamp(3, dateOfBirth);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new author", e);
        }
    }

    @Override
    public List<Author> findAll() throws DaoException {
        List<Author> listOfAuthors = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_AUTHORS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long authorId = resultSet.getLong(AUTHOR_ID);
                String authorName = resultSet.getString(AUTHOR_NAME);
                String informationAboutAuthor = resultSet.getString(AUTHOR_INFO);
                String genreName = resultSet.getString(GENRE_NAME);
                String imageUrl = resultSet.getString(AUTHOR_IMG);
                listOfAuthors.add(Author.builder()
                        .setId(authorId)
                        .setName(authorName)
                        .setInformationAboutAuthor(informationAboutAuthor)
                        .setGenreName(genreName)
                        .setImageUrl(imageUrl)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all authors", e);
        }
        return listOfAuthors;
    }
}
