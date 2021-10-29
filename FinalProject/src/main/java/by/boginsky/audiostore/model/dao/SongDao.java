package by.boginsky.audiostore.model.dao;

import by.boginsky.audiostore.exception.DaoException;
import by.boginsky.audiostore.model.entity.audio.Song;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * The interface Song dao.
 */
public interface SongDao {

    /**
     * Find all list.
     *
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Song> findAll() throws DaoException;

    /**
     * Find song by id optional.
     *
     * @param songId the song id
     * @return the optional
     * @throws DaoException the dao exception
     */
    Optional<Song> findSongById(Long songId) throws DaoException;

    /**
     * Find song by order id list.
     *
     * @param songId the song id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Song> findSongByOrderId(Long songId) throws DaoException;

    /**
     * Find song by album id list.
     *
     * @param albumId the album id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<Song> findSongByAlbumId(Long albumId) throws DaoException;

    /**
     * Update song.
     *
     * @param songName  the song name
     * @param songPrice the song price
     * @param authorId  the author id
     * @param genreId   the genre id
     * @param albumId   the album id
     * @param songId    the song id
     * @throws DaoException the dao exception
     */
    void updateSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId, Long songId) throws DaoException;

    /**
     * Insert song long.
     *
     * @param songName  the song name
     * @param songPrice the song price
     * @param authorId  the author id
     * @param genreId   the genre id
     * @param albumId   the album id
     * @return the long
     * @throws DaoException the dao exception
     */
    Long insertSong(String songName, BigDecimal songPrice, Long authorId, Long genreId, Long albumId) throws DaoException;

    /**
     * Remove song.
     *
     * @param songId the song id
     * @throws DaoException the dao exception
     */
    void removeSong(Long songId) throws DaoException;
}
