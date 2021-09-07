package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Author;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAllAuthors() throws ServiceException;

    Optional<Author> findAuthorByName(String firstNameOfAuthor, String lastNameOfAuthor) throws ServiceException;

    void addNewAuthor(String firstNameOfAuthor, String lastNameOfAuthor, String informationAboutAuthor, LocalDateTime dateOfBirth) throws ServiceException;

    Optional<Author> findAuthorById(Long authorId) throws ServiceException;
}
