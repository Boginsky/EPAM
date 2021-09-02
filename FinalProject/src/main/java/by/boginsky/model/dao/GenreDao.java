package by.boginsky.model.dao;

import by.boginsky.exception.DaoException;
import by.boginsky.model.entity.audio.Genre;

import java.util.Optional;

public interface GenreDao {

    Optional<Genre> findByName(String nameOfGenre) throws DaoException;

    void insertGenre(String name, String informationAboutGenre) throws DaoException;
}
