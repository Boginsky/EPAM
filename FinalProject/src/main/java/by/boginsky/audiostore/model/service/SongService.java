package by.boginsky.audiostore.model.service;

import by.boginsky.audiostore.exception.ServiceException;
import by.boginsky.audiostore.model.entity.audio.Song;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Song service.
 */
public interface SongService {

    /**
     * Find song by id optional.
     *
     * @param songId the song id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Song> findSongById(Long songId) throws ServiceException;

    /**
     * Find all songs list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Song> findAllSongs() throws ServiceException;

    /**
     * Find song by album id list.
     *
     * @param albumId the album id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Song> findSongByAlbumId(Long albumId) throws ServiceException;

    /**
     * Find song by order id list.
     *
     * @param orderId the order id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Song> findSongByOrderId(Long orderId) throws ServiceException;

    /**
     * Add new song long.
     *
     * @param songName  the song name
     * @param songPrice the song price
     * @param authorId  the author id
     * @param genreId   the genre id
     * @param albumId   the album id
     * @return the long
     * @throws ServiceException the service exception
     */
    Long addNewSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws ServiceException;

    /**
     * Remove song.
     *
     * @param songId the song id
     * @throws ServiceException the service exception
     */
    void removeSong(Long songId) throws ServiceException;

    /**
     * Update song.
     *
     * @param songName  the song name
     * @param songPrice the song price
     * @param authorId  the author id
     * @param genreId   the genre id
     * @param albumId   the album id
     * @param songId    the song id
     * @throws ServiceException the service exception
     */
    void updateSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId, Long songId) throws ServiceException;

}
