package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Author;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    Optional<Author> findAuthorByName(String firstNameOfAuthor, String lastNameOfAuthor) throws DaoException;

    void insertAuthor(String firstName, String lastName,String informationAboutAuthor, Timestamp dateOfBirth) throws DaoException;

}
