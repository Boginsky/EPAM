package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Song;

import java.math.BigDecimal;
import java.util.List;

public interface SongDao {

    List<Song> findSongByName(String nameOfSong) throws DaoException;

    List<Song> findSongByAuthor(String nameOfAuthor, String lastNameOfAuthor) throws DaoException;

    List<Song> findSongByGenre(String nameOfGenre) throws DaoException;

    List<Song> findSongByAlbum(String nameOfAlbum) throws DaoException;

    void updateSongName(String songPreviousName, String songNewName) throws DaoException;

    void updateSongPrice(BigDecimal songPrice, String songName) throws DaoException;

    void insertSong(String songName, String imageUrl, BigDecimal songPrice,Long authorId, Long genreId,Long albumId) throws DaoException;

    void insertSongWithoutAlbum(String songName, String imageUrl, BigDecimal songPrice,Long authorId, Long genreId) throws DaoException;
}
