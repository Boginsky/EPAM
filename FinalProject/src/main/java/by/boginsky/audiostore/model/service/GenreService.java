package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {

    List<Genre> findAllGenres() throws ServiceException;

    Optional<Genre> findByName(String genreName) throws ServiceException;

    void addNewGenre(String genreName, String informationAboutGenre) throws ServiceException;

    Optional<Genre> findGenreById(Long genreId) throws ServiceException;
}
