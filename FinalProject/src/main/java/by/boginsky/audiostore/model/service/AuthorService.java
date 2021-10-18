package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Author;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    Optional<Author> findById(Long authorId) throws ServiceException;

    List<Author> findAllAuthors(Long pageId) throws ServiceException;

    List<Author> findAllAuthors() throws ServiceException;

    Long addNewAuthor(String nameOfAuthor, String informationAboutAuthor) throws ServiceException;

    void removeAuthor(Long authorId) throws ServiceException;

    void updateAuthor(Long authorId,String authorName, String authorInfo) throws ServiceException;

}
