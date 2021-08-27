package model.dao;

import exception.DaoException;
import model.entity.audio.Album;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AlbumDao {
    Optional<Long> findByName(String nameOfAlbum) throws DaoException;

    List<Long> findByGenre(String nameOfGenre) throws DaoException;

    void insertAlbum(String name, Timestamp dateOfCreation,String informationAboutAlbum) throws DaoException;
}
