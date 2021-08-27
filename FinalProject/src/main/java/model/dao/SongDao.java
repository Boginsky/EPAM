package model.dao;

import exception.DaoException;
import model.entity.audio.Song;

import java.math.BigDecimal;
import java.util.List;

public interface SongDao {

    Song findById(Long songId) throws DaoException;

    List<Song> findSongByName(String nameOfSong) throws DaoException;

    List<Song> findSongByAuthor(String nameOfAuthor) throws DaoException;

    List<Song> findSongByGenre(String nameOfGenre) throws DaoException;

    void buySong(Long clientId, Long songId) throws DaoException;

    void update(String songName, String imageUrl, BigDecimal songPrice,Long authorId, Long genreId,Long albumId) throws DaoException;

    void deleteById(Long songId) throws DaoException;

    boolean findByName(String name) throws DaoException;

    void insert(String songName, String imageUrl, BigDecimal songPrice,Long authorId, Long genreId,Long albumId) throws DaoException;

    void insertWithoutAlbum(String songName, String imageUrl, BigDecimal songPrice,Long authorId, Long genreId) throws DaoException;
}
