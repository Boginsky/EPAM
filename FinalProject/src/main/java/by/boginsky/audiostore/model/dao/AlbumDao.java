package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Album;
import by.boginsky.audiostore.model.entity.audio.Author;
import by.boginsky.audiostore.model.entity.user.User;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface AlbumDao {

    List<Album> findAll(Long startPosition) throws DaoException;

    List<Album> findAll() throws DaoException;

    Optional<Album> findById(Long albumId) throws DaoException;

    List<Album> findByAuthor(Long authorId) throws DaoException;

    Long insertAlbum(String name, String informationAboutAlbum) throws DaoException;

    void updateAlbumPhoto(String albumImageUrl, Long albumId) throws DaoException;

    void removeAlbum(Long albumId) throws DaoException;

    void updateAlbum(Long albumId,String albumName, String albumInfo) throws DaoException;

}
