package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SongService {

    Optional<Song> findSongById(Long songId) throws ServiceException;

    List<Song> findAllSongs() throws ServiceException;

    List<Song> findSongByName(String nameOfSong) throws ServiceException;

    List<Song> findSongByAuthor(String authorFirstName, String authorLasName) throws ServiceException;

    List<Song> findSongByGenre(String nameOfGenre) throws ServiceException;

    List<Song> findSongByAlbum(String nameOfAlbum) throws ServiceException;

    void updateSongName(String songPreviousName, String songNewName) throws ServiceException;

    void updateSongPrice(BigDecimal songPrice, String songName) throws ServiceException;

    void insertSong(String songName, String imageUrl, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws ServiceException;

    void insertSongWithoutAlbum(String songName, String imageUrl, BigDecimal songPrice, Long authorId, Long genreId) throws ServiceException;

}
