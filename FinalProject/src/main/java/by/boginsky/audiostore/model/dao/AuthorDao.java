package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Author;
import by.boginsky.audiostore.model.entity.user.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> findAll() throws DaoException;

    Optional<Author> findById(Long id) throws DaoException;

    void insertAuthor(String authorName, String informationAboutAuthor, Timestamp dateOfBirth) throws DaoException;

}
