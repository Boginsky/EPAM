package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Author;

import java.util.List;
import java.util.Optional;

/**
 * The interface Author service.
 */
public interface AuthorService {

    /**
     * Find by id optional.
     *
     * @param authorId the author id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Author> findById(Long authorId) throws ServiceException;

    /**
     * Find all authors list.
     *
     * @param pageId the page id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Author> findAllAuthors(Long pageId) throws ServiceException;

    /**
     * Find all authors list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Author> findAllAuthors() throws ServiceException;

    /**
     * Add new author long.
     *
     * @param nameOfAuthor           the name of author
     * @param informationAboutAuthor the information about author
     * @return the long
     * @throws ServiceException the service exception
     */
    Long addNewAuthor(String nameOfAuthor, String informationAboutAuthor) throws ServiceException;

    /**
     * Remove author.
     *
     * @param authorId the author id
     * @throws ServiceException the service exception
     */
    void removeAuthor(Long authorId) throws ServiceException;

    /**
     * Update author.
     *
     * @param authorId   the author id
     * @param authorName the author name
     * @param authorInfo the author info
     * @throws ServiceException the service exception
     */
    void updateAuthor(Long authorId, String authorName, String authorInfo) throws ServiceException;

}
