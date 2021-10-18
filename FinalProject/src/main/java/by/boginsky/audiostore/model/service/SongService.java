package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface SongService {

    Optional<Song> findSongById(Long songId) throws ServiceException;

    List<Song> findAllSongs() throws ServiceException;

    List<Song> findSongByAlbumId(Long albumId) throws ServiceException;

    List<Song> findSongByOrderId(Long orderId) throws ServiceException;

    List<String> findSongImg() throws ServiceException;

    Long addNewSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws ServiceException;

    void removeSong(Long songId) throws ServiceException;

    void updateSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId, Long songId) throws ServiceException;

}
