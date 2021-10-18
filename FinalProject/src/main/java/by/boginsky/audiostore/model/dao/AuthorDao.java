package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Author;
import by.boginsky.audiostore.model.entity.user.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> findAll(Long startPosition) throws DaoException;

    List<Author> findAll() throws DaoException;

    Optional<Author> findById(Long id) throws DaoException;

    Long insertAuthor(String authorName, String informationAboutAuthor) throws DaoException;

    void updateAuthorPhoto(String authorImageUrl, Long authorId) throws DaoException;

    void removeAuthor(Long authorId) throws DaoException;

    void updateAuthor(Long authorId,String authorName, String authorInfo) throws DaoException;

}
