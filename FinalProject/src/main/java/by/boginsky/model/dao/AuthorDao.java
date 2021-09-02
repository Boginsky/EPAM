package by.boginsky.model.dao;

import by.boginsky.exception.DaoException;
import by.boginsky.model.entity.audio.Author;

import java.sql.Timestamp;
import java.util.Optional;

public interface AuthorDao {

    Optional<Author> findAuthorByName(String firstNameOfAuthor, String lastNameOfAuthor) throws DaoException;

    void insertAuthor(String firstName, String lastName,String informationAboutAuthor, Timestamp dateOfBirth) throws DaoException;

}
