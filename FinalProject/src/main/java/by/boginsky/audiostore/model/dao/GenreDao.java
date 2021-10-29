package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Genre;

import java.util.List;

/**
 * The interface Genre dao.
 */
public interface GenreDao {

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Genre> findAll() throws DaoException;
}
