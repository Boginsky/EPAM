package by.boginsky.audiostore.model.dao.impl;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.dao.AuthorDao;
import by.boginsky.audiostore.model.dao.BaseDao;
import by.boginsky.audiostore.model.entity.audio.Author;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.audiostore.model.dao.ColumnName.*;

public class AuthorDaoImpl extends BaseDao<Author> implements AuthorDao {

    private static final String INSERT_INTO_AUTHORS = "INSERT INTO authors (author_name,author_info) VALUES (?,?)";
    private static final String FIND_ALL_AUTHORS_FOR_PAGE = "SELECT author_id,author_name,author_info,author_img FROM authors LIMIT ?,5";
    private static final String FIND_ALL_AUTHORS = "SELECT author_id,author_name FROM authors";
    private static final String FIND_AUTHOR_BY_ID = "SELECT author_id,author_name,author_info,author_img FROM authors WHERE author_id = ?";
    private static final String FIND_GENRE_FOR_AUTHOR = "SELECT DISTINCT genre_name FROM authors JOIN songs ON author_id = authors_author_id JOIN genres ON genres_genre_id = genre_id WHERE author_id = ?";
    private static final String UPDATE_AUTHOR_PHOTO = "UPDATE authors SET author_img = ? WHERE author_id = ?";
    private static final String REMOVE_AUTHOR = "DELETE FROM authors WHERE author_id = ?";
    private static final String UPDATE_AUTHOR = "UPDATE authors SET author_name = ?,author_info = ? WHERE author_id = ?";

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
                author = Optional.ofNullable(Author.builder()
                        .setId(authorId)
                        .setName(authorName)
                        .setInformationAboutAuthor(informationAboutAuthor)
                        .setImageUrl(imageUrl)
                        .setListOfGenres(findGenresForAuthor(authorId))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding author by id", e);
        }
        return author;
    }


    @Override
    public Long insertAuthor(String authorName, String informationAboutAuthor) throws DaoException {
        Long id = null; // FIXME: 15.10.2021 
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_AUTHORS, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, authorName);
            preparedStatement.setString(2, informationAboutAuthor);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, inserting new author", e);
        }
        return id;
    }

    @Override
    public void updateAuthorPhoto(String authorImageUrl, Long authorId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR_PHOTO)) {
            preparedStatement.setString(1,authorImageUrl);
            preparedStatement.setLong(2,authorId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, updating author's photo",e);
        }
    }

    @Override
    public void removeAuthor(Long authorId) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(REMOVE_AUTHOR)) {
            preparedStatement.setLong(1,authorId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, removing author",e);
        }
    }

    @Override
    public void updateAuthor(Long authorId,String authorName, String authorInfo) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_AUTHOR)) {
            preparedStatement.setString(1,authorName);
            preparedStatement.setString(2,authorInfo);
            preparedStatement.setLong(3,authorId);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException,updating author",e);
        }
    }

    @Override
    public List<Author> findAll(Long startPosition) throws DaoException {
        List<Author> listOfAuthors = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_AUTHORS_FOR_PAGE)) {
            preparedStatement.setLong(1,startPosition);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long authorId = resultSet.getLong(AUTHOR_ID);
                String authorName = resultSet.getString(AUTHOR_NAME);
                String informationAboutAuthor = resultSet.getString(AUTHOR_INFO);
                String imageUrl = resultSet.getString(AUTHOR_IMG);
                listOfAuthors.add(Author.builder()
                        .setId(authorId)
                        .setName(authorName)
                        .setInformationAboutAuthor(informationAboutAuthor)
                        .setListOfGenres(findGenresForAuthor(authorId))
                        .setImageUrl(imageUrl)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all authors for page", e);
        }
        return listOfAuthors;
    }

    @Override
    public List<Author> findAll() throws DaoException {
        List<Author> listOfAuthors = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_AUTHORS)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Long authorId = resultSet.getLong(AUTHOR_ID);
                String authorName = resultSet.getString(AUTHOR_NAME);
                listOfAuthors.add(Author.builder()
                        .setId(authorId)
                        .setName(authorName)
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("SQLException, finding all authors", e);
        }
        return listOfAuthors;
    }

    private List<String> findGenresForAuthor(Long authorId) throws DaoException {
        List<String> listOfGenres = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_GENRE_FOR_AUTHOR)) {
            preparedStatement.setLong(1,authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listOfGenres.add(resultSet.getString(GENRE_NAME));
            }
        }catch (SQLException e){
            throw new DaoException("SQLException, finding genres for author",e);
        }
        return listOfGenres;
    }
}
