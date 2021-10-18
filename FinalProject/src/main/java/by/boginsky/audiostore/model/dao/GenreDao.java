package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> findAll() throws DaoException;
}
