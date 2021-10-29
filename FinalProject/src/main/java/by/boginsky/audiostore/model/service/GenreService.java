package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Genre;

import java.util.List;

/**
 * The interface Genre service.
 */
public interface GenreService {

    /**
     * Find all genres list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Genre> findAllGenres() throws ServiceException;
}
