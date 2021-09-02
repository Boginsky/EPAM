package by.boginsky.model.dao.impl;

import by.boginsky.exception.DaoException;
import by.boginsky.model.dao.AuthorDao;
import by.boginsky.model.dao.BaseDao;
import by.boginsky.model.entity.audio.Author;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.boginsky.model.dao.ColumnName.*;

public class AuthorDaoImpl extends BaseDao implements AuthorDao {

    private static final String FIND_AUTHOR_BY_AUTHOR_NAME = "SELECT (author_first_name,author_last_name,author_info,author_dob) FROM authors WHERE author_first_name = ? AND author_last_name = ?";
    private static final String INSERT_INTO_AUTHORS = "INSERT INTO authors (author_first_name,author_last_name,author_info_author_dob) VALUES (?,?,?,?)";
    private static final String FIND_ALL_AUTHORS = "SELECT author_first_name,author_last_name,author_info,author_dob FROM authors";
    private static final String FIND_AUTHOR_BY_ID = "SELECT author_first_name,author_last_name,author_info,author_dob FROM authors WHERE author_id = ?";


    @Override
    public Optional<Author> findAuthorByName(String firstNameOfAuthor, String lastNameOfAuthor) throws DaoException {
        Optional<Author> author = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_AUTHOR_NAME)) {
            preparedStatement.setString(1, firstNameOfAuthor);
            preparedStatement.setString(2,lastNameOfAuthor);
            ResultSet resultSet = preparedStatement.executeQuery();
            String authorFirstName = resultSet.getString(AUTHOR_FIRST_NAME);
            String authorLastName = resultSet.getString(AUTHOR_LAST_NAME);
            String informationAboutAuthor = resultSet.getString(AUTHOR_INFO);
            Timestamp dateOfBirth = resultSet.getTimestamp(AUTHOR_DATE_OF_BIRTH);
            author = Optional.of(Author.builder()
                    .setFirstName(authorFirstName)
                    .setLastName(authorLastName)
                    .setInformationAboutAuthor(informationAboutAuthor)
                    .setDateOfBirth(dateOfBirth)
                    .build());
        } catch (SQLException e) {
            throw new DaoException("SQLException, searching author by name", e);
        }
        return author;
    }

    @Override
    public void insertAuthor(String firstName, String lastName, String informationAboutAuthor, Timestamp dateOfBirth) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_INTO_AUTHORS)) {
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,informationAboutAuthor);
            preparedStatement.setTimestamp(4,dateOfBirth);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            throw new DaoException("SQLException, inserting new author",e);
        }
    }

    @Override
    public List<Author> findAll() throws DaoException {
        List<Author> listOfAuthors = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_AUTHORS)){
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String authorFirstName = resultSet.getString(AUTHOR_FIRST_NAME);
                String authorLastName = resultSet.getString(AUTHOR_LAST_NAME);
                String informationAboutAuthor = resultSet.getString(AUTHOR_INFO);
                Timestamp dateOfBirth = resultSet.getTimestamp(AUTHOR_DATE_OF_BIRTH);
                listOfAuthors.add(Author.builder()
                        .setFirstName(authorFirstName)
                        .setLastName(authorLastName)
                        .setInformationAboutAuthor(informationAboutAuthor)
                        .setDateOfBirth(dateOfBirth)
                        .build());
            }
        }catch (SQLException e){
            throw new DaoException("SQLException, finding all authors",e);
        }
        return listOfAuthors;
    }

    @Override
    public Optional<Author> findById(Long authorId) throws DaoException {
        Optional<Author> author = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_AUTHOR_BY_ID)) {
            preparedStatement.setLong(1,authorId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                String authorFirstName = resultSet.getString(AUTHOR_FIRST_NAME);
                String authorLastName = resultSet.getString(AUTHOR_LAST_NAME);
                String informationAboutAuthor = resultSet.getString(AUTHOR_INFO);
                Timestamp dateOfBirth = resultSet.getTimestamp(AUTHOR_DATE_OF_BIRTH);
                author = Optional.of(Author.builder()
                        .setFirstName(authorFirstName)
                        .setLastName(authorLastName)
                        .setInformationAboutAuthor(informationAboutAuthor)
                        .setDateOfBirth(dateOfBirth)
                        .build());
            }
        }catch (SQLException e){
            throw new DaoException("SQLException, finding author by id");
        }
        return author;
    }
}
