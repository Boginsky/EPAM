package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Song;
import by.boginsky.audiostore.model.entity.user.User;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SongDao {

    List<Song> findAll() throws DaoException;

    Optional<Song> findSongById(Long songId) throws DaoException;

    List<Song> findSongByOrderId(Long songId) throws DaoException;

    List<Song> findSongByAlbumId(Long albumId) throws DaoException;

    List<String> findSongImg() throws DaoException;

    void updateSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId, Long songId) throws DaoException;

    void updateSongPhoto(String songImageUrl, Long songId) throws DaoException;

    Long insertSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws DaoException;

    void removeSong(Long songId) throws DaoException;
}
