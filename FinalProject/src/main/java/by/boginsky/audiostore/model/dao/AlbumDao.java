package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Album;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AlbumDao {

    Optional<Album> findByName(String nameOfAlbum) throws DaoException;

    List<Album> findByGenre(String nameOfGenre) throws DaoException;

    void insertAlbum(String name, Timestamp dateOfCreation,String informationAboutAlbum) throws DaoException;
}
