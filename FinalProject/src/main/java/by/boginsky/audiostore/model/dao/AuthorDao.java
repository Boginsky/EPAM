package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Author;

import java.util.List;
import java.util.Optional;

/**
 * The interface Author dao.
 */
public interface AuthorDao {

    /**
     * Find all list.
     *
     * @param startPosition the start position
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Author> findAll(Long startPosition) throws DaoException;

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Author> findAll() throws DaoException;

    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Author> findById(Long id) throws DaoException;

    /**
     * Insert author long.
     *
     * @param authorName             the author name
     * @param informationAboutAuthor the information about author
     * @return the long
     * @throws DaoException the dao exception
     */
    Long insertAuthor(String authorName, String informationAboutAuthor) throws DaoException;

    /**
     * Update author photo.
     *
     * @param authorImageUrl the author image url
     * @param authorId       the author id
     * @throws DaoException the dao exception
     */
    void updateAuthorPhoto(String authorImageUrl, Long authorId) throws DaoException;

    /**
     * Remove author.
     *
     * @param authorId the author id
     * @throws DaoException the dao exception
     */
    void removeAuthor(Long authorId) throws DaoException;

    /**
     * Update author.
     *
     * @param authorId   the author id
     * @param authorName the author name
     * @param authorInfo the author info
     * @throws DaoException the dao exception
     */
    void updateAuthor(Long authorId, String authorName, String authorInfo) throws DaoException;

}
