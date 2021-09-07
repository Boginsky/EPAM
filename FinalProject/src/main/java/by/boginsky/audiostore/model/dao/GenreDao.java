package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Genre;

import java.util.Optional;

public interface GenreDao {

    Optional<Genre> findByName(String nameOfGenre) throws DaoException;

    void insertGenre(String name, String informationAboutGenre) throws DaoException;
}
