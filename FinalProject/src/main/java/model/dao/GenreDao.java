package model.dao;

import exception.DaoException;

import java.util.Optional;

public interface GenreDao {

    Optional<Long> findByName(String nameOfGenre) throws DaoException;

    void insertGenre(String name, String informationAboutGenre) throws DaoException;
}
